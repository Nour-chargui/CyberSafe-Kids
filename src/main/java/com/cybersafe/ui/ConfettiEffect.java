package com.cybersafe.ui;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Random;

public class ConfettiEffect {
    public static void start(Pane parent) {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            Rectangle c = new Rectangle(10, 10, Color.hsb(rand.nextInt(360), 0.8, 0.9));
            c.setLayoutX(rand.nextInt((int) parent.getWidth()));
            c.setLayoutY(-20);
            parent.getChildren().add(c);

            Timeline fall = new Timeline(new KeyFrame(Duration.seconds(2 + rand.nextDouble()),
                    new KeyValue(c.layoutYProperty(), parent.getHeight() + 20),
                    new KeyValue(c.rotateProperty(), 360)));
            fall.setOnFinished(e -> parent.getChildren().remove(c));
            fall.play();
        }
    }
}