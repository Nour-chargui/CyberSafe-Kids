package com.cybersafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Point d'entrée réel de l'application JavaFX.
 * Hérite de 'Application' pour gérer le cycle de vie de la fenêtre.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 1. Chargement sécurisé du fichier FXML (Vue)
        URL fxmlLocation = getClass().getResource("/com/cybersafe/view/main-view.fxml");

        if (fxmlLocation == null) {
            throw new RuntimeException("ERREUR CRITIQUE : Le fichier main-view.fxml est introuvable dans resources.");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(loader.load(), 1200, 800);

        // 2. Chargement sécurisé du CSS (Style)
        URL cssLocation = getClass().getResource("/com/cybersafe/style/main.css");
        if (cssLocation != null) {
            scene.getStylesheets().add(cssLocation.toExternalForm());
        } else {
            System.err.println("AVERTISSEMENT : main.css introuvable, l'app tournera sans style.");
        }

        // 3. Configuration de la fenêtre "NEON DEFENDER"
        stage.setTitle("🛡️ CYBERSAFE KIDS - NEON DEFENDER");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();
    }

    /**
     * LA MÉTHODE MANQUANTE ÉTAIT ICI :
     * C'est le pont indispensable pour que le Launcher puisse démarrer JavaFX.
     */
    public static void main(String[] args) {
        launch(args);
    }
}