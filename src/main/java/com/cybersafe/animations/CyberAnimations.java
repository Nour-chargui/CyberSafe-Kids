package com.cybersafe.animations;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class CyberAnimations {
    public static void floatAnimation(Node node) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(2), node);
        tt.setByY(-10);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);
        tt.play();
    }
    public static void fadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0); ft.setToValue(1); ft.play();
    }
}