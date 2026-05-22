package com.cybersafe.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class GrokApiClient {
    private static final String API_KEY = "VOTRE_CLE_API_GROK"; // À remplacer
    private static final String API_URL = "https://api.x.ai/v1/chat/completions";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String askGrok(String userQuestion) throws Exception {
        // Construction du prompt spécialisé pour enfants (Cyber Security Trainer)
        String systemPrompt = "Tu es CyberBot, un expert en cybersécurité pour enfants de 8 à 14 ans. " +
                "Explique les concepts de façon simple, fun, avec des emojis. " +
                "Si la question n'est pas liée à l'informatique ou au réseau, réponds poliment " +
                "que tu ne parles que de sécurité numérique.";

        Map<String, Object> body = Map.of(
                "model", "grok-beta",
                "messages", new Object[]{
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userQuestion)
                }
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Simple extraction du texte (à améliorer avec AIResponseParser)
        return mapper.readTree(response.body()).get("choices").get(0).get("message").get("content").asText();
    }
}