package com.mycompany.tilgungsrechner.service;

import javafx.scene.Parent;

public interface ISceneManager {

    String INPUT_FORM = "input.form";
    String RESULT_VIEWER = "result.viewer";

    Parent get(String key);
}