package com.wastemanagement.config;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConfig {

    private static DatabaseConfig instance;
    private Connection connection;

    private DatabaseConfig() {
        try {
            String url = "jdbc:postgresql://localhost:5432/waste_management";
            String username = "postgres";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
