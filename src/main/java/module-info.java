module com.cybersafe {
    // --- MODULES JAVAFX ---
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // --- MODULES JACKSON ---
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    // --- MODULES ICÔNES ---
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    //--- API Grok ---
    requires java.net.http;
    // --- ACCÈS RÉFLEXION (Indispensable pour JavaFX et Jackson) ---
    opens com.cybersafe.controller to javafx.fxml;
    opens com.cybersafe.view to javafx.fxml;
    opens com.cybersafe.model to com.fasterxml.jackson.databind;

    // --- EXPOSITION ---
    exports com.cybersafe;
    exports com.cybersafe.controller;
    exports com.cybersafe.model;
}