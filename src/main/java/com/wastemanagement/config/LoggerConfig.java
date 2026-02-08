package com.wastemanagement.config;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class LoggerConfig {

    private static LoggerConfig instance;
    private PrintWriter writer;

    private LoggerConfig() {
        try {
            writer = new PrintWriter(new FileWriter("waste-management.log", true));
            System.out.println("Logger initialized (Singleton)");
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static synchronized LoggerConfig getInstance() {
        if (instance == null) {
            instance = new LoggerConfig();
        }
        return instance;
    }

    public void log(String message) {
        if (writer != null) {
            writer.println(LocalDateTime.now() + " - " + message);
            writer.flush();
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}