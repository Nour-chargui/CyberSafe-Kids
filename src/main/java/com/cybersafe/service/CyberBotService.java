package com.cybersafe.service;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CyberBotService {
    public static void typeMessage(Label label, String message) {
        label.setText("");
        StringBuilder sb = new StringBuilder();
        Timeline timeline = new Timeline();
        for (int i = 0; i < message.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(30 * i), e -> {
                sb.append(message.charAt(index));
                label.setText(sb.toString());
            }));
        }
        timeline.play();
    }
}
