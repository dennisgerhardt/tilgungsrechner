package com.mycompany.tilgungsrechner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Main extends Application {

    private double width, height;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        determineScreenResolution();

        // running into problems if using scenemanager here
        // because it will load the controller class via the fxml which uses this service again
        final InputStream inputStream = getClass().getResource("/input_form_view_de.properties").openStream();
        final ResourceBundle bundle = new PropertyResourceBundle(inputStream);
        final Parent root = FXMLLoader.load(getClass().getResource("/input_form_view.fxml"), bundle);

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle(bundle.getString("title.text"));
        primaryStage.show();

        if (inputStream != null) {
            inputStream.close();
        }
    }

    private void determineScreenResolution() {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        this.width = bounds.getWidth() / 2 + 80;
        this.height = bounds.getHeight() / 2;
    }
}