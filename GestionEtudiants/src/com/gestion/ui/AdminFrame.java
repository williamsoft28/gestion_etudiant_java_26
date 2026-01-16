package com.gestion.ui;

import com.gestion.dao.EtudiantDAO;
import com.gestion.model.Etudiant;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField matriculeField;
    private JTextField filiereField;
    private JTextField telephoneField;

    private EtudiantDAO etudiantDAO = new EtudiantDAO();

    public AdminFrame() {
        setTitle("Espace Administrateur");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Matricule :"));
        matriculeField = new JTextField();
        panel.add(matriculeField);

        panel.add(new JLabel("Filière :"));
        filiereField = new JTextField();
        panel.add(filiereField);

        panel.add(new JLabel("Téléphone (10 chiffres) :"));
        telephoneField = new JTextField();
        panel.add(telephoneField);

        JButton btnEnregistrer = new JButton("Enregistrer");
        panel.add(new JLabel());
        panel.add(btnEnregistrer);

        add(panel);

        btnEnregistrer.addActionListener(e -> enregistrerEtudiant());

        setVisible(true);
    }

    private void enregistrerEtudiant() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String matricule = matriculeField.getText().trim();
        String filiere = filiereField.getText().trim();
        String telephone = telephoneField.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || matricule.isEmpty()
                || filiere.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tous les champs sont obligatoires");
            return;
        }

        if (!telephone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "Le numéro doit contenir exactement 10 chiffres");
            return;
        }

        Etudiant e = new Etudiant();
        e.setNom(nom);
        e.setPrenom(prenom);
        e.setMatricule(matricule);
        e.setFiliere(filiere);
        e.setTelephone(telephone);

        boolean success = etudiantDAO.ajouterEtudiant(e);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Étudiant enregistré avec succès !");
            viderChamps();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'enregistrement",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viderChamps() {
        nomField.setText("");
        prenomField.setText("");
        matriculeField.setText("");
        filiereField.setText("");
        telephoneField.setText("");
    }
}
