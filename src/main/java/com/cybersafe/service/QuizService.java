package com.cybersafe.service;

import com.cybersafe.model.QuizQuestion;
import java.util.ArrayList;
import java.util.List;

public class QuizService {

    /**
     * Use Case : Charge la banque de questions pour le niveau débutant.
     */
    public static List<QuizQuestion> getBeginnerQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();

        questions.add(new QuizQuestion(
                "C'est quoi le 'Phishing' ?",
                new String[]{"Un sport de pêche", "Un email menteur pour voler tes infos", "Un nouveau jeu vidéo"},
                1,
                "Le Phishing utilise des messages truqués pour te piéger ! Ne clique jamais sur des liens bizarres."
        ));

        questions.add(new QuizQuestion(
                "Lequel est un mot de passe solide ?",
                new String[]{"123456", "MonPrénom", "Cyb3r!#2025"},
                2,
                "Un bon mot de passe mélange des lettres, des chiffres et des symboles spéciaux."
        ));

        questions.add(new QuizQuestion(
                "À quoi sert le 'Pare-feu' ?",
                new String[]{"À éteindre les incendies", "À bloquer les attaques réseau", "À chauffer l'ordinateur"},
                1,
                "Le Pare-feu est ton premier bouclier. Il filtre les données pour arrêter les virus."
        ));

        return questions;
    }
}