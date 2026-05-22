package com.cybersafe.model;

/**
 * Record Java 21 représentant une question.
 * Le format record génère automatiquement les méthodes query(), options(), etc.
 */
public record QuizQuestion(
        String query,
        String[] options,
        int correctIdx,
        String explanation
) {}