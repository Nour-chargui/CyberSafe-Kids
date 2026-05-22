package com.cerberus.repository;
import com.cerberus.model.NetworkLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.util.List;

public class LogRepository {
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    public void exportLogs(List<NetworkLog> logs) {
        try { mapper.writeValue(new File("cyber_logs.json"), logs); } catch (Exception e) {}
    }
}