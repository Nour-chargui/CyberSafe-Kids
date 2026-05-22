package com.cybersafe.controller;

import com.cybersafe.ui.ConfettiEffect;
import com.cybersafe.ui.CyberBadge;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox; // Import indispensable


public class VictoryController extends BaseGameController {
    @FXML private VBox rootVBox;
    @FXML private StackPane badgeContainer;

    @FXML
    public void initialize() {
        badgeContainer.getChildren().add(new CyberBadge("BRONZE"));

        // On injecte le badge animé au centre
        badgeContainer.getChildren().add(new CyberBadge("BRONZE"));
        Platform.runLater(() -> {
            if (rootVBox != null) {
                ConfettiEffect.start(rootVBox);
            }
        });

    }

    @FXML
    private void handleContinue() {
        // Retour au Dashboard ou niveau suivant
        mainController.startGame(); // Exemple : Relance une mission
    }
}