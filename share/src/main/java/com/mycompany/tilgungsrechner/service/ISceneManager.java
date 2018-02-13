package com.mycompany.tilgungsrechner.service;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public interface ISceneManager {

    String INPUT_FORM = "input.form";
    String RESULT_VIEWER = "result.viewer";

    /**
     * for navigation between different stages/scenes
     * @param pageName targetpage-key
     * @param event needed to obtain access to javafx
     */
    void navigateTo(String pageName, ActionEvent event);

    /**
     * for navigation between different stages/scenes
     * @param pageName targetpage-key
     * @param stage if stage already exists and can be set
     */
    void navigateTo(String pageName, Stage stage);
}