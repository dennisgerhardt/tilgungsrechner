package com.mycompany.tilgungsrechner.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SceneManagerImpl implements ISceneManager {

    private final Map<String, Parent> scenes;

    SceneManagerImpl() {
        scenes = new HashMap<>();
        initialize();
    }

    private void initialize() {
        try {
            scenes.put(INPUT_FORM, loadController("/input_form_view_de.properties", "/input_form_view.fxml"));
            scenes.put(RESULT_VIEWER, loadController("/result_viewer_view_de.properties", "/result_viewer_view.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Parent loadController(final String resourcePath, final String viewPath) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getResource(resourcePath).openStream();
            final ResourceBundle bundle = new PropertyResourceBundle(inputStream);
            final Parent parent = FXMLLoader.load(getClass().getResource(viewPath), bundle);

            return parent;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    public Parent get(final String key) {
        return scenes.get(key);
    }
}