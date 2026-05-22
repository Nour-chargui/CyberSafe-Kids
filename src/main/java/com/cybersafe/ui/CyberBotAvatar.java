package com.cybersafe.ui;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class CyberBotAvatar extends StackPane {
    private Circle head;
    private Ellipse leftEye, rightEye;

    public CyberBotAvatar() {
        // 1. Dessin de la tête
        head = new Circle(40, Color.web("#1e293b"));
        head.setStroke(Color.web("#7df9ff"));
        head.setStrokeWidth(3);

        // 2. Dessin des yeux (Néon)
        leftEye = new Ellipse(10, 10);
        leftEye.setFill(Color.web("#7df9ff"));
        leftEye.setTranslateX(-15);

        rightEye = new Ellipse(10, 10);
        rightEye.setFill(Color.web("#7df9ff"));
        rightEye.setTranslateX(15);

        this.getChildren().addAll(head, leftEye, rightEye);

        // 3. Animation de clignement des yeux (Blink)
        Timeline blink = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftEye.radiusYProperty(), 10), new KeyValue(rightEye.radiusYProperty(), 10)),
                new KeyFrame(Duration.millis(100), new KeyValue(leftEye.radiusYProperty(), 1), new KeyValue(rightEye.radiusYProperty(), 1)),
                new KeyFrame(Duration.millis(200), new KeyValue(leftEye.radiusYProperty(), 10), new KeyValue(rightEye.radiusYProperty(), 10))
        );
        blink.setDelay(Duration.seconds(3));
        blink.setCycleCount(Animation.INDEFINITE);
        blink.play();
    }

    /**
     * Change l'expression du robot
     * @param mood : "NORMAL", "ALERT", "SUCCESS"
     */
    public void setMood(String mood) {
        switch (mood) {
            case "ALERT" -> {
                head.setStroke(Color.web("#ff003c"));
                leftEye.setFill(Color.web("#ff003c"));
                rightEye.setFill(Color.web("#ff003c"));
            }
            case "SUCCESS" -> {
                head.setStroke(Color.web("#39ff14"));
                leftEye.setFill(Color.web("#39ff14"));
                rightEye.setFill(Color.web("#39ff14"));
            }
            default -> {
                head.setStroke(Color.web("#7df9ff"));
                leftEye.setFill(Color.web("#7df9ff"));
                rightEye.setFill(Color.web("#7df9ff"));
            }
        }
    }
}