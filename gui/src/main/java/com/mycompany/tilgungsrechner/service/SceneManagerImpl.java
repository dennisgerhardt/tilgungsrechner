package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.util.ScreenUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SceneManagerImpl implements ISceneManager {

    private final Map<String, String[]> scenes;

    SceneManagerImpl() {
        scenes = new HashMap<>();
        scenes.put(INPUT_FORM, new String[] {"/input_form_view_de.properties", "/input_form_view.fxml"});
        scenes.put(RESULT_VIEWER, new String[] {"/result_viewer_view_de.properties", "/result_viewer_view.fxml"});
    }

    @Override
    public void navigateTo(final String pageName, final ActionEvent event) {
        final Scene scene = scene(pageName);
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void navigateTo(final String pageName, final Stage stage) {
        final Scene scene = scene(pageName);
        stage.setScene(scene);
        stage.show();
    }

    private Scene scene(final String pageName) {
        final String[] paths = scenes.get(pageName);

        try {
            final Parent parent = load(paths[0], paths[1]);
            final Scene scene = new Scene(parent, ScreenUtil.appWidth(), ScreenUtil.appHeight());

            return scene;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            throw new RuntimeException("System.exit");
        }
    }

    private Parent load(final String resourcePath, final String viewPath) throws Exception {
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
}