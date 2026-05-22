package com.cybersafe.controller;

import com.cybersafe.model.QuizQuestion;
import com.cybersafe.service.QuizService;
import com.cybersafe.service.SoundService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * MOTEUR DE QUIZ INTERACTIF
 * Gère la progression, le score et les récompenses XP.
 */
public class QuizArenaController extends BaseGameController {

    @FXML private Label lblQuestion;
    @FXML private Label lblCounter;
    @FXML private ProgressBar progressQuiz;
    @FXML private Button btnOpt1, btnOpt2, btnOpt3, btnNext;
    @FXML private VBox optionsContainer;

    private List<QuizQuestion> questions;
    private int currentIndex = 0;
    private int correctAnswers = 0;

    @FXML
    public void initialize() {
        // Le démarrage réel se fait via startQuiz() appelé par le MainController
    }

    /**
     * Initialise et lance une nouvelle session de Quiz.
     */
    public void startQuiz() {
        this.questions = QuizService.getBeginnerQuestions();
        this.currentIndex = 0;
        this.correctAnswers = 0;

        if (btnNext != null) btnNext.setVisible(false);
        showQuestion();
    }

    /**
     * Met à jour l'interface avec la question actuelle.
     */
    private void showQuestion() {
        if (currentIndex < questions.size()) {
            QuizQuestion q = questions.get(currentIndex);

            // 1. Mise à jour des textes
            lblQuestion.setText(q.query());
            lblCounter.setText("Question " + (currentIndex + 1) + " / " + questions.size());

            // 2. Mise à jour de la barre de progression
            double progress = (double) (currentIndex + 1) / questions.size();
            progressQuiz.setProgress(progress);

            // 3. Attribution des textes aux boutons
            btnOpt1.setText(q.options()[0]);
            btnOpt2.setText(q.options()[1]);
            btnOpt3.setText(q.options()[2]);

            // 4. Reset visuel
            resetButtonStyles();
            optionsContainer.setDisable(false);
            if (btnNext != null) btnNext.setVisible(false);

        } else {
            // Fin du quiz atteinte
            handleVictory();
        }
    }

    /**
     * Gère le clic sur une réponse.
     */
    @FXML
    private void handleAnswer(ActionEvent event) {
        Button clickedBtn = (Button) event.getSource();
        QuizQuestion currentQ = questions.get(currentIndex);
        String correctAnswer = currentQ.options()[currentQ.correctIdx()];

        // On bloque les boutons pour éviter le double-clic
        optionsContainer.setDisable(true);

        if (clickedBtn.getText().equals(correctAnswer)) {
            // --- BONNE RÉPONSE ---
            clickedBtn.setStyle("-fx-background-color: #39ff14; -fx-text-fill: black; -fx-font-weight: bold;");
            correctAnswers++;
            mainController.addXP(25); // Récompense XP
            mainController.updateBotMessage("🎉 GÉNIAL ! " + currentQ.explanation());
            SoundService.playClick();
        } else {
            // --- MAUVAISE RÉPONSE ---
            clickedBtn.setStyle("-fx-background-color: #ff003c; -fx-text-fill: white; -fx-font-weight: bold;");
            mainController.updateBotMessage("⚠️ OUPS ! " + currentQ.explanation());
            SoundService.playAlert();
        }

        // On affiche le bouton "Suivant"
        if (btnNext != null) btnNext.setVisible(true);
    }

    /**
     * Passe à la question suivante.
     */
    @FXML
    private void nextQuestion() {
        currentIndex++;
        showQuestion();
    }

    /**
     * Affiche l'écran de réussite finale.
     */
    private void handleVictory() {
        lblQuestion.setText("🏆 ENTRAÎNEMENT TERMINÉ !\nTon score : " + correctAnswers + " / " + questions.size());
        optionsContainer.setVisible(false);
        if (btnNext != null) btnNext.setVisible(false);

        // Déclenche le badge de victoire dans le MainController
        mainController.showVictory();
    }

    private void resetButtonStyles() {
        String baseStyle = "-fx-background-color: #1e293b; -fx-text-fill: white; -fx-border-color: #7df9ff; -fx-border-radius: 5;";
        btnOpt1.setStyle(baseStyle);
        btnOpt2.setStyle(baseStyle);
        btnOpt3.setStyle(baseStyle);
        optionsContainer.setVisible(true);
    }
}