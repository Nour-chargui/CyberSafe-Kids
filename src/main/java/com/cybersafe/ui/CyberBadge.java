package com.cybersafe.ui;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

public class CyberBadge extends StackPane {

    public CyberBadge(String label) {
        // 1. Fond du badge (Cercle Bronze avec Dégradé)
        Circle base = new Circle(50);
        Stop[] stops = new Stop[] { new Stop(0, Color.web("#CD7F32")), new Stop(1, Color.web("#8B4513")) };
        base.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops));
        base.setStroke(Color.web("#ffd700"));
        base.setStrokeWidth(3);

        // 2. Icône de bouclier au centre
        FontIcon icon = new FontIcon("fas-shield-alt");
        icon.setIconSize(40);
        icon.setIconColor(Color.WHITE);

        // 3. Effet de lueur (Glow)
        javafx.scene.effect.DropShadow glow = new javafx.scene.effect.DropShadow();
        glow.setColor(Color.web("#CD7F32"));
        glow.setRadius(20);
        this.setEffect(glow);

        this.getChildren().addAll(base, icon);

        // 4. Animation de "Pop-in" et Rotation
        this.setScaleX(0); this.setScaleY(0);
        ScaleTransition pop = new ScaleTransition(Duration.millis(800), this);
        pop.setToX(1); pop.setToY(1);
        pop.setInterpolator(Interpolator.EASE_OUT);

        RotateTransition rotate = new RotateTransition(Duration.seconds(4), this);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);

        pop.play();
        rotate.play();
    }
}