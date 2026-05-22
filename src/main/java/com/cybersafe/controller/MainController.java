package com.cybersafe.controller;

import com.cybersafe.animations.CyberAnimations;
import com.cybersafe.model.PlayerProfile;
import com.cybersafe.service.CyberBotService;
import com.cybersafe.service.SoundService;
import com.cybersafe.ui.CyberBotAvatar;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.io.IOException;

public class MainController {

    // --- Composants FXML ---
    @FXML private StackPane gameArea;
    @FXML private Label lblBotChat;
    @FXML private Label lblXP;
    @FXML private Label lblLevel;
    @FXML private ProgressBar xpBar;
    @FXML private StackPane botContainer;
    @FXML private TextField txtChatInput;

    // --- Logique & UI Personnalisée ---
    private CyberBotAvatar cyberBot;
    private final PlayerProfile player = new PlayerProfile("Junior Defender");

    @FXML
    public void initialize() {
        // Sécurité : On vérifie que le conteneur existe dans le FXML
        if (botContainer != null) {
            cyberBot = new CyberBotAvatar();
            botContainer.getChildren().add(cyberBot);
            CyberAnimations.floatAnimation(botContainer);
        } else {
            System.err.println("❌ ERREUR : botContainer est introuvable dans le FXML !");
        }

        updateBotMessage("Prêt pour ta mission, Junior Defender ? 🚀");
        updateUI();
    }

    // --- NAVIGATION ---

    @FXML
    private void showExplorer() {
        SoundService.playClick();
        loadModule("/com/cybersafe/view/network-explorer.fxml");
        updateBotMessage("Explorons le réseau ! Clique sur les icônes. 🌐");
    }

    @FXML
    private void showQuiz() {
        SoundService.playClick();
        loadModule("/com/cybersafe/view/quiz-arena.fxml");
        updateBotMessage("Prêt pour le défi ? Réponds aux questions ! 🏆");
    }

    @FXML
    public void startGame() {
        SoundService.playClick();
        loadModule("/com/cybersafe/view/defender-game.fxml");
        updateBotMessage("⚠️ ALERTE : Des virus attaquent ! Bloque les ronds rouges ! ⚡");
    }

    // --- INTELLIGENCE ARTIFICIELLE (CHAT) ---

    @FXML
    private void handleAIChat() {
        String input = txtChatInput.getText().toLowerCase().trim();
        if (input.isEmpty()) return;

        txtChatInput.clear();
        SoundService.playClick();

        String response = switch (input) {
            case String s when s.contains("ssh") -> "🚀 Le SSH est un tunnel secret pour commander un ordinateur à distance en toute sécurité !";
            case String s when s.contains("http") -> "🌍 Le HTTP est le langage du web. Le HTTPS est la version protégée !";
            case String s when s.contains("virus") -> "👾 Un virus est un programme méchant. L'Antivirus est ton bouclier !";
            case String s when s.contains("hacker") -> "🕵️ Un hacker cherche des failles. Toi, tu es un Defender !";
            case String s when s.contains("bonjour") || s.contains("salut") -> "Salut ! Pose-moi une question sur la cybersécurité ! 😊";
            default -> "🤔 Je ne connais pas encore ce mot. Demande-moi des infos sur les 'virus' ou les 'mots de passe' !";
        };

        updateBotMessage(response);
    }

    // --- GESTION DU BOT (FUSIONNÉE) ---

    /**
     * Met à jour le message, change la couleur de la bulle et l'humeur du robot.
     */
    public void updateBotMessage(String message) {
        // 1. Gestion du Mood et du Style CSS selon le contenu
        if (message.contains("⚠️") || message.contains("❌")) {
            cyberBot.setMood("ALERT");
            lblBotChat.setStyle("-fx-background-color: #ff003c; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 10;");
        } else if (message.contains("🎉") || message.contains("🌟") || message.contains("GÉNIAL")) {
            cyberBot.setMood("SUCCESS");
            lblBotChat.setStyle("-fx-background-color: #39ff14; -fx-text-fill: black; -fx-padding: 10; -fx-background-radius: 10;");
        } else {
            cyberBot.setMood("NORMAL");
            lblBotChat.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-padding: 10; -fx-background-radius: 10;");
        }

        // 2. Animation de machine à écrire
        CyberBotService.typeMessage(lblBotChat, message);
    }

    // --- SYSTÈME DE JEU ---

    public void addXP(int amount) {
        player.addXp(amount);
        updateUI();
    }

    private void updateUI() {
        if (lblXP != null) lblXP.setText("XP: " + player.getXp());
        if (lblLevel != null) lblLevel.setText("Lvl: " + player.getLevel());
        if (xpBar != null) xpBar.setProgress(player.getXp() / 100.0);
    }

    public void showVictory() {
        loadModule("/com/cybersafe/view/victory-dialog.fxml");
        updateBotMessage("🎉 INCROYABLE ! Regarde ton nouveau badge ! Tu es un héros !");
    }

    private void loadModule(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node node = loader.load();

            Object controller = loader.getController();
            if (controller instanceof BaseGameController bgc) {
                bgc.setMainController(this);

                // Déclenchement des logiques spécifiques
                if (bgc instanceof QuizArenaController qac) qac.startQuiz();
                if (bgc instanceof DefenderGameController dgc) dgc.startGameLogic();
            }

            gameArea.getChildren().setAll(node);

            FadeTransition ft = new FadeTransition(Duration.millis(500), node);
            ft.setFromValue(0); ft.setToValue(1); ft.play();

        } catch (IOException e) {
            System.err.println("Erreur chargement module : " + fxmlPath);
            e.printStackTrace();
        }
    }

    public Label getBotLabel() { return lblBotChat; }
}