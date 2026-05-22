package com.cybersafe.service;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundService {
    private static AudioClip clickSound;
    private static AudioClip alertSound;

    static {
        try {
            // Chargement sécurisé des ressources audio
            URL clickURL = SoundService.class.getResource("/com/cybersafe/sounds/click.wav");
            URL alertURL = SoundService.class.getResource("/com/cybersafe/sounds/alert.wav");

            if (clickURL != null) clickSound = new AudioClip(clickURL.toExternalForm());
            if (alertURL != null) alertSound = new AudioClip(alertURL.toExternalForm());
        } catch (Exception e) {
            System.err.println("🔊 [Sons] Fichiers audio non trouvés. L'app restera muette.");
        }
    }

    public static void playClick() { if (clickSound != null) clickSound.play(); }
    public static void playAlert() { if (alertSound != null) alertSound.play(); }
}