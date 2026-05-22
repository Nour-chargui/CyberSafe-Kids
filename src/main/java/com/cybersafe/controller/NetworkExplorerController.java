package com.cybersafe.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class NetworkExplorerController extends BaseGameController {

    @FXML private Label lblInfoTitle;
    @FXML private Label lblInfoDesc;
    @FXML private StackPane animContainer;

    @FXML
    private void selectPC() {
        updatePanel("Ordinateur",
                "L'ordinateur est le cerveau de tes activités. Il permet de naviguer, jouer et travailler. " +
                        "\n⚡ Risque : Les logiciels espions.\n🛡️ Protection : Un antivirus à jour !");
    }

    @FXML
    private void selectRouter() {
        updatePanel("Routeur",
                "Le routeur est comme un aiguilleur du ciel pour tes données. Il les dirige vers la bonne destination." +
                        "\n⚡ Risque : Accès WiFi non sécurisé.\n🛡️ Protection : Un mot de passe WiFi complexe !");
    }

    @FXML
    private void selectFirewall() {
        updatePanel("Pare-feu",
                "C'est ton bouclier magique ! Il analyse tout ce qui entre et sort pour bloquer les attaques." +
                        "\n⚡ Risque : Désactivation par accident.\n🛡️ Protection : Toujours le laisser activé !");
    }

    private void updatePanel(String title, String desc) {
        if (lblInfoTitle == null || lblInfoDesc == null) return;

        lblInfoTitle.setText(title.toUpperCase());
        lblInfoDesc.setText(desc);

        // Animation visuelle de changement
        FadeTransition ft = new FadeTransition(Duration.millis(400), lblInfoDesc);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        if (mainController != null) {
            mainController.updateBotMessage("Consultation des données du " + title + " terminée. ✅");
        }
    }
}