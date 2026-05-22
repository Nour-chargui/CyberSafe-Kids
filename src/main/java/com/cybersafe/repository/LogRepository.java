package com.cybersafe.repository;

import com.cybersafe.model.NetworkLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Maintenant l'import va marcher
import java.io.File;
import java.util.List;

public class LogRepository {
    private final ObjectMapper mapper = new ObjectMapper();

    public LogRepository() {
        // Enregistre le module pour gérer les dates LocalDateTime dans le JSON
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * Use Case : Sauvegarde l'historique des ronds bloqués/manqués.
     */
    public void saveLogs(List<NetworkLog> logs) {
        try {
            File file = new File("cyber_history.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, logs);
            System.out.println("💾 Historique sauvegardé avec succès.");
        } catch (Exception e) {
            System.err.println("❌ Erreur de sauvegarde : " + e.getMessage());
        }
    }
}