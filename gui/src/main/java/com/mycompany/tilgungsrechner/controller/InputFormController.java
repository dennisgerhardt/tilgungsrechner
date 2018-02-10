package com.mycompany.tilgungsrechner.controller;

import com.mycompany.tilgungsrechner.data.CalculatorInput;
import com.mycompany.tilgungsrechner.data.CalculatorResult;
import com.mycompany.tilgungsrechner.exception.ValidationException;
import com.mycompany.tilgungsrechner.service.ICalculator;
import com.mycompany.tilgungsrechner.service.ISceneManager;
import com.mycompany.tilgungsrechner.service.IValidation;
import com.mycompany.tilgungsrechner.service.ServiceResolver;
import com.mycompany.tilgungsrechner.util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFormController implements Initializable {

    @FXML
    private TextField loanAmountField, durationOfDebitField, fixedDebitRateField, repaymentRateField;

    @FXML
    private DatePicker payoutDateField;

    @FXML
    private Label labelErrorLoanAmount, labelErrorFixedDebitRate, labelErrorRepaymentRate, labelErrorDurationOfDebit;

    @FXML
    private Button calculateButton;

    private final IValidation validation;
    private final ICalculator calculator;
    private final ISceneManager sceneManager;

    private Map<TextField, Boolean> canCalculationExecuteMap;

    public InputFormController() {
        validation = ServiceResolver.resolve(IValidation.class);
        calculator = ServiceResolver.resolve(ICalculator.class);
        sceneManager = ServiceResolver.resolve(ISceneManager.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payoutDateField.setValue(DateUtil.lastDayOfMonth(0));
        calculateButton.setOnAction(this::startCalculationExecuted);
        calculateButton.setDisable(true);
        payoutDateField.setDisable(true);

        canCalculationExecuteMap = new HashMap<>(Stream.of(
                new AbstractMap.SimpleEntry<>(loanAmountField, false),
                new AbstractMap.SimpleEntry<>(fixedDebitRateField, false),
                new AbstractMap.SimpleEntry<>(repaymentRateField, false),
                new AbstractMap.SimpleEntry<>(durationOfDebitField, false)
        ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

        loanAmountField.focusedProperty().addListener((arg, oldVal, newVal) -> {
            final boolean validLoanAmount = validation.validateLoanAmount(loanAmountField.getText());
            syncGuiWithValidationResult(labelErrorLoanAmount, loanAmountField, validLoanAmount);
        });
        fixedDebitRateField.focusedProperty().addListener((arg, oldVal, newVal) -> {
            final boolean validFixedDebitRate = validation.validatePercentage(fixedDebitRateField.getText());
            syncGuiWithValidationResult(labelErrorFixedDebitRate, fixedDebitRateField, validFixedDebitRate);
        });
        repaymentRateField.focusedProperty().addListener((arg, oldVal, newVal) -> {
            final boolean validRepaymentRate = validation.validatePercentage(repaymentRateField.getText());
            syncGuiWithValidationResult(labelErrorRepaymentRate, repaymentRateField, validRepaymentRate);
        });
        durationOfDebitField.focusedProperty().addListener((arg, oldVal, newVal) -> {
            final boolean validDurationOfDebit = validation.validateDuration(durationOfDebitField.getText());
            syncGuiWithValidationResult(labelErrorDurationOfDebit, durationOfDebitField, validDurationOfDebit);
        });

        Arrays.stream(
            new Label[]{labelErrorLoanAmount, labelErrorFixedDebitRate, labelErrorRepaymentRate, labelErrorDurationOfDebit}
        ).forEach(label -> label.setVisible(false));
    }

    protected void startCalculationExecuted(ActionEvent actionEvent) {
        CalculatorInput calculatorInput = new CalculatorInput(loanAmountField.getText(),
                durationOfDebitField.getText(),
                fixedDebitRateField.getText(),
                repaymentRateField.getText(),
                payoutDateField.getValue().toString());

        try {
            CalculatorResult calculatorResult = calculator.calculatePlan(calculatorInput);

            final Parent resultViewer = sceneManager.get(ISceneManager.RESULT_VIEWER);

            final Scene scene = new Scene(resultViewer);
            final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (ValidationException e) {
            e.getValidationErrorCode();
        }
    }

    private void syncGuiWithValidationResult(final Label label, final TextField field, final boolean result) {
        label.setVisible(!result);
        canCalculationExecuteMap.put(field, result);
        final long count = canCalculationExecuteMap.entrySet().stream().filter(v -> v.getValue() == false).count();
        calculateButton.setDisable(count != 0);
    }
}