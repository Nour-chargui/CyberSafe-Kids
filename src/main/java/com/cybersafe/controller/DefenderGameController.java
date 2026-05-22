package com.cybersafe.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.Random;

public class DefenderGameController extends BaseGameController {

    @FXML private Pane playZone; // Doit correspondre à l'ID dans le FXML

    private Random random = new Random();
    private int threatsBlocked = 0;
    private final int GOAL = 10;

    @FXML
    public void initialize() {
    }
    public void startGameLogic() {
        Timeline spawnTimer = new Timeline(new KeyFrame(Duration.seconds(1.2), e -> spawnPacket()));
        spawnTimer.setCycleCount(Timeline.INDEFINITE);
        spawnTimer.play();
        mainController.updateBotMessage("DÉFENSE ACTIVE ! Bloque les virus rouges !");
    }
    private void spawnPacket() {
        if (playZone == null) return;

        boolean isThreat = random.nextBoolean();
        Circle p = new Circle(20, isThreat ? Color.RED : Color.LIME);
        if (isThreat) {
            String type = random.nextBoolean() ? "MALWARE 👾" : "PHISHING 🎣";
            mainController.updateBotMessage("⚠️ ALERTE : Un paquet " + type + " approche !");
        }
        // Position aléatoire sécurisée
        p.setLayoutX(random.nextInt((int) Math.max(1, playZone.getWidth() - 40)) + 20);
        p.setLayoutY(random.nextInt((int) Math.max(1, playZone.getHeight() - 40)) + 20);

        // Interaction au clic
        p.setOnMouseClicked(e -> {
                if (isThreat) {
                    threatsBlocked++;
                    mainController.addXP(15);
                    if (threatsBlocked >= 10) showVictoryPopup();
                } else {
                    mainController.addXP(-10);
                    mainController.updateBotMessage("❌ STOP ! Tu as bloqué un message amical !");
                }
                playZone.getChildren().remove(p);
            });


        playZone.getChildren().add(p);

        // Disparition automatique après 2.5 secondes
        Timeline fade = new Timeline(new KeyFrame(Duration.seconds(2.5), ev -> playZone.getChildren().remove(p)));
        fade.play();
    }

    private void winLevel() {
        mainController.updateBotMessage("🌟 MISSION ACCOMPLIE ! Tu es un véritable Defender !");
        threatsBlocked = 0; // Reset pour le prochain tour
    }
    private void showVictoryPopup() {
        // On utilise l'IA pour annoncer la victoire
        mainController.updateBotMessage("🌟 NIVEAU 1 SÉCURISÉ ! Tu as bloqué 10 menaces. Prêt pour le Niveau 2 ?");
        // Ici on pourrait charger un FXML 'victory-dialog.fxml'
    }
}