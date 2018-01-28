package com.mycompany.tilgungsrechner.controller;

import com.mycompany.tilgungsrechner.CalculatorResult;
import com.mycompany.tilgungsrechner.ICalculator;
import com.mycompany.tilgungsrechner.service.ServiceResolver;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.event.ActionEvent;


public class InputFormController extends Application {

    @FXML
    private TextField nameField;

    private double width, height;

    public void start(final Stage primaryStage) throws Exception {
        determineScreenResolution();

        final Parent root = FXMLLoader.load(getClass().getResource("/input_form_view.fxml"));

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle("Bitte geben Sie Ihre Daten ein");
        primaryStage.show();
    }

    @FXML
    protected void startCalculationExecuted(ActionEvent actionEvent) {
        System.out.println("button pressed!");

        ICalculator calculator = ServiceResolver.resolve(ICalculator.class);
        CalculatorResult calculatorResult = calculator.calculatePlan(null);

        System.out.println(calculatorResult.getValue());
        nameField.setText("" + calculatorResult.getValue());
    }

    private void determineScreenResolution() {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        this.width = bounds.getWidth() / 2;
        this.height = bounds.getHeight() / 2;
    }
}