package com.gestion.dao;

import com.gestion.config.DatabaseConnection;
import com.gestion.model.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EtudiantDAO {

    public boolean ajouterEtudiant(Etudiant e) {
        String sql = "INSERT INTO etudiants(nom, prenom, matricule, filiere, telephone, date_naissance) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getMatricule());
            ps.setString(4, e.getFiliere());
            ps.setString(5, e.getTelephone());
            ps.setDate(6, e.getDateNaissance() != null ? java.sql.Date.valueOf(e.getDateNaissance()) : null);

            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Etudiant trouverParMatricule(String matricule) {
        String sql = "SELECT * FROM etudiants WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricule);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Etudiant e = new Etudiant();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setMatricule(rs.getString("matricule"));
                e.setFiliere(rs.getString("filiere"));
                e.setTelephone(rs.getString("telephone"));
                java.sql.Date dateSQL = rs.getDate("date_naissance");
                if (dateSQL != null) {
                    e.setDateNaissance(dateSQL.toLocalDate());
                }
                return e;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public java.util.List<Etudiant> findAll() {
        String sql = "SELECT * FROM etudiants";
        java.util.List<Etudiant> list = new java.util.ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant e = new Etudiant();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setMatricule(rs.getString("matricule"));
                e.setFiliere(rs.getString("filiere"));
                e.setTelephone(rs.getString("telephone"));
                java.sql.Date dateSQL = rs.getDate("date_naissance");
                if (dateSQL != null) {
                    e.setDateNaissance(dateSQL.toLocalDate());
                }
                list.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean supprimer(String matricule) {
        String sql = "DELETE FROM etudiants WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricule);
            ps.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
