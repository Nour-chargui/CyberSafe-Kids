package com.cybersafe.model;

import java.time.LocalDateTime;

/**
 * Représente un événement réseau capturé par le simulateur.
 */
public record NetworkLog(
        LocalDateTime timestamp,
        String source,
        String destination,
        int packetSize,
        String protocol,
        boolean isSuspicious,
        String integrityHash
) {}