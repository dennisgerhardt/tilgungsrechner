package com.mycompany.tilgungsrechner.controller;

import com.mycompany.tilgungsrechner.data.CalculatorResult;
import com.mycompany.tilgungsrechner.service.ICalculator;
import com.mycompany.tilgungsrechner.service.IValidation;
import com.mycompany.tilgungsrechner.service.ServiceResolver;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class InputFormController extends Application {

    @FXML
    private TextField loanAmountField, durationOfDebitField, fixedDebitRateField, desiredRateField;

    @FXML
    private DatePicker payoutDateField;

    private final IValidation validation;
    private final ICalculator calculator;

    private double width, height;

    public InputFormController() {
        validation = ServiceResolver.resolve(IValidation.class);
        calculator = ServiceResolver.resolve(ICalculator.class);
    }

    public void start(final Stage primaryStage) throws Exception {
        determineScreenResolution();

        final InputStream inputStream = getClass().getResource("/input_form_view_de.properties").openStream();
        final ResourceBundle bundle = new PropertyResourceBundle(inputStream);
        final Parent root = FXMLLoader.load(getClass().getResource("/input_form_view.fxml"), bundle);

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle(bundle.getString("title.text"));
        primaryStage.show();
    }

    @FXML
    protected void startCalculationExecuted(ActionEvent actionEvent) {

        System.out.println(validation.validate(durationOfDebitField.getText()));

        CalculatorResult calculatorResult = calculator.calculatePlan(null);

        loanAmountField.setText("" + calculatorResult.getValue());
    }

    private void determineScreenResolution() {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        this.width = bounds.getWidth() / 2;
        this.height = bounds.getHeight() / 2;
    }
}