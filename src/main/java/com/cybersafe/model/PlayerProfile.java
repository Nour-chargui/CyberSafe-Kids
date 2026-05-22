package com.cybersafe.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfile {
    private String username;
    private int xp = 0;
    private int level = 1;
    private List<String> badges = new ArrayList<>();

    // LE CONSTRUCTEUR MANQUANT EST ICI :
    public PlayerProfile(String name) {
        this.username = name;
    }

    // Ajoutez aussi un constructeur vide (No-Args)
    // C'est une sécurité indispensable pour Jackson JSON
    public PlayerProfile() {
    }

    // --- LOGIQUE DE JEU ---
    public void addXp(int amount) {
        this.xp += amount;
        if (this.xp >= 100) {
            level++;
            xp -= 100;
        }
    }

    // --- GETTERS ---
    public String getUsername() { return username; }
    public int getXp() { return xp; }
    public int getLevel() { return level; }
}