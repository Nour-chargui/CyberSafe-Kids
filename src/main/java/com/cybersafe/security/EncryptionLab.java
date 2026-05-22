package com.cybersafe.security;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class EncryptionLab {
    // Use Case : Expliquer le "Code Secret" (Hash) aux enfants
    public static String generateCyberHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString(); // Retourne l'empreinte néon
        } catch (Exception e) { return "ERROR"; }
    }
}