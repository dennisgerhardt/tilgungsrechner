package com.mycompany.tilgungsrechner.util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public final class ScreenUtil {

    public static double appWidth() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();

        return bounds.getWidth() / 2 + 80;
    }

    public static double appHeight() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();

        return bounds.getHeight() / 2;
    }
}