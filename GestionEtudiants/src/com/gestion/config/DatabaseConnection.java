package com.gestion.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_etudiants?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // vide avec Wamp par défaut

    public static Connection getConnection() {
        try {
            // Charge le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            System.out.println("❌ Erreur de connexion à la base de données");
            e.printStackTrace();
            return null;
        }
    }
}
