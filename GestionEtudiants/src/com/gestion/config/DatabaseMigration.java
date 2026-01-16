package com.gestion.config;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseMigration {

    public static void migrate() {

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (conn == null) {
                System.out.println("❌ Connexion MySQL échouée, migration annulée");
                return;
            }

            String sql = "CREATE TABLE IF NOT EXISTS etudiants (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nom VARCHAR(50) NOT NULL," +
                    "prenom VARCHAR(50) NOT NULL," +
                    "matricule VARCHAR(20) NOT NULL UNIQUE," +
                    "filiere VARCHAR(50)," +
                    "telephone VARCHAR(20)" +
                    ")";

            stmt.executeUpdate(sql);

            System.out.println("✅ Migration réussie : table ETUDIANTS créée");

        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la migration");
            e.printStackTrace();
        }
    }
}
