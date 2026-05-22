package com.cerberus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 1. Chargement sécurisé du fichier FXML
        URL fxmlLocation = getClass().getResource("/com/cerberus/view/main-view.fxml");

        if (fxmlLocation == null) {
            // Si c'est null, cela signifie que le fichier n'est pas au bon endroit dans 'resources'
            throw new RuntimeException("ERREUR CRITIQUE: Le fichier /com/cerberus/view/main-view.fxml est introuvable.");
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(loader.load(), 1100, 700);

        // 2. Chargement sécurisé du fichier CSS
        URL cssLocation = getClass().getResource("/com/cerberus/style/style.css");
        if (cssLocation != null) {
            scene.getStylesheets().add(cssLocation.toExternalForm());
        } else {
            System.err.println("AVERTISSEMENT: Fichier style.css introuvable dans resources.");
        }

        // 3. Configuration de la fenêtre principale
        stage.setTitle("NEON SENTINEL - Cyber Security Academy");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Point d'entrée pour lancer l'application JavaFX.
     * Cette méthode est appelée par la classe Launcher.
     */
    public static void main(String[] args) {
        launch(args);
    }
}