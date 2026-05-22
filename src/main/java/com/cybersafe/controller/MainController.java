package com.cerberus.controller;

import com.cerberus.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    @FXML private Label lblStatus, lblTotal, lblThreats, lblXP;
    @FXML private TableView<NetworkLog> tableLogs;
    @FXML private TableColumn<NetworkLog, String> colTime, colSource, colStatus;
    @FXML private TextArea terminal;
    @FXML private ProgressBar xpBar;
    @FXML private LineChart<Number, Number> chart;

    private final ObservableList<NetworkLog> logs = FXCollections.observableArrayList();
    private final SimulationService sim = new SimulationService();
    private final XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private Timer timer;
    private int tick = 0;

    @FXML
    public void initialize() {
        colTime.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().timestamp().toString().substring(11, 19)));
        colSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        colStatus.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().isSuspicious() ? "🚨 ATTACK" : "OK"));
        tableLogs.setItems(logs);
        chart.getData().add(series);
        runIntro();
    }

    private void runIntro() {
        terminal.appendText(" > NEON-SENTINEL ACADEMY READY...\n > WAITING FOR COMMANDS...");
    }

    @FXML public void startNormal() { startSim("NORMAL", "SYSTEM PROTECTED", "#39ff14"); }
    @FXML public void startBruteForce() { startSim("BRUTE_FORCE", "ATTACK DETECTED: BRUTE FORCE", "#ff003c"); }
    @FXML public void startDDoS() { startSim("DDOS", "CRITICAL: DDOS IN PROGRESS", "#ff8c00"); }

    private void startSim(String mode, String msg, String color) {
        if(timer != null) timer.cancel();
        sim.setScenario(mode);
        lblStatus.setText(msg);
        lblStatus.setStyle("-fx-text-fill: " + color);
        terminal.appendText("\n [MODE] " + mode + " INITIATED.");

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                Platform.runLater(() -> {
                    NetworkLog log = sim.generateData();
                    logs.add(0, log);
                    series.getData().add(new XYChart.Data<>(tick++, log.packetSize()));
                    if(series.getData().size() > 20) series.getData().remove(0);
                    lblTotal.setText(String.valueOf(logs.size()));
                });
            }
        }, 0, 800);
    }

    @FXML public void runQuiz() {
        terminal.appendText("\n [QUIZ] What is SHA-256? (A: Encryption, B: Hash, C: Protocol)");
        // Logique simplifiée pour XP
        xpBar.setProgress(xpBar.getProgress() + 0.1);
        lblXP.setText("XP: " + (int)(xpBar.getProgress()*100));
    }
}