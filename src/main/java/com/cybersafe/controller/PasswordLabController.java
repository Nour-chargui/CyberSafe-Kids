package com.cybersafe.controller;

import com.cybersafe.service.CyberBotService;
import com.cybersafe.service.SoundService;
import com.cybersafe.utils.SecurityUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PasswordLabController extends BaseGameController {

    @FXML private TextField txtPassword;
    @FXML private ProgressBar pgStrength;
    @FXML private Label lblStrengthText, lblHash;

    @FXML
    public void initialize() {
        // Analyse en temps réel à chaque touche pressée
        txtPassword.textProperty().addListener((obs, oldVal, newVal) -> analyzePassword(newVal));
    }

    private void analyzePassword(String password) {
        double strength = 0;
        if (password.length() >= 6) strength += 0.25;
        if (password.matches(".*[A-Z].*")) strength += 0.25;
        if (password.matches(".*[0-9].*")) strength += 0.25;
        if (password.matches(".*[!@#$%^&*].*")) strength += 0.25;

        pgStrength.setProgress(strength);

        // Mise à jour visuelle (Néon Colors)
        updateUI(strength, password);
    }

    private void updateUI(double strength, String password) {
        String hash = SecurityUtils.getSHA256(password);
        lblHash.setText("Code Secret (SHA-256) : " + hash.substring(0, 15) + "...");

        if (strength <= 0.25) {
            lblStrengthText.setText("TRÈS FAIBLE 🔴");
            pgStrength.setStyle("-fx-accent: #ff003c;"); // Rouge Néon
        } else if (strength <= 0.50) {
            lblStrengthText.setText("MOYEN 🟡");
            pgStrength.setStyle("-fx-accent: #ff8c00;"); // Orange
        } else if (strength <= 0.75) {
            lblStrengthText.setText("FORT 🟢");
            pgStrength.setStyle("-fx-accent: #39ff14;"); // Vert Néon
        } else {
            lblStrengthText.setText("INCASSABLE ! 💎");
            pgStrength.setStyle("-fx-accent: #00f2ff;"); // Cyan Néon
            if (password.length() > 8) {
                mainController.addXP(5); // On récompense le joueur
                SoundService.playClick();
            }
        }
    }
}