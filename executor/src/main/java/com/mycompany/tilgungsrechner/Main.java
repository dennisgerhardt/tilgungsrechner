package com.mycompany.tilgungsrechner;

import com.mycompany.tilgungsrechner.service.ISceneManager;
import com.mycompany.tilgungsrechner.service.ServiceResolver;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        ServiceResolver.resolve(ISceneManager.class).navigateTo(ISceneManager.INPUT_FORM, primaryStage);
    }
}