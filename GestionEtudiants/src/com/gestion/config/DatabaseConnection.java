package com.gestion.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:gestion_etudiants.db";

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("❌ Erreur de connexion à la base de données");
            e.printStackTrace();
            return null;
        }
    }
}
