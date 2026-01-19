package com.gestion.ui;

import com.gestion.dao.EtudiantDAO;
import com.gestion.model.Etudiant;
import com.gestion.utils.MatriculeGenerator;

import javax.swing.*;
import java.awt.*;

public class InscriptionPanel extends JPanel {

    private final JTextField txtNom, txtPrenom, txtAnnee, txtMatricule;

    public InscriptionPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNom = new JTextField();
        txtPrenom = new JTextField();
        txtAnnee = new JTextField();
        txtMatricule = new JTextField();
        txtMatricule.setEditable(false);

        JButton btnGenerer = new JButton("Générer matricule");
        JButton btnEnregistrer = new JButton("Enregistrer");

        add(new JLabel("Nom"));
        add(txtNom);
        add(new JLabel("Prénom"));
        add(txtPrenom);
        add(new JLabel("Année de naissance"));
        add(txtAnnee);
        add(new JLabel("Matricule"));
        add(txtMatricule);

        add(btnGenerer);
        add(btnEnregistrer);

        btnGenerer.addActionListener(e -> genererMatricule());
        btnEnregistrer.addActionListener(e -> enregistrer());
    }

    private void genererMatricule() {
        try {
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();
            int annee = Integer.parseInt(txtAnnee.getText().trim());

            if(nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom et prénom obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            txtMatricule.setText(MatriculeGenerator.generer(nom, prenom, annee));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Année de naissance invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enregistrer() {
        String matricule = txtMatricule.getText().trim();
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();

        if(matricule.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez générer le matricule et remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // On crée l'étudiant complet avec les champs obligatoires
        Etudiant e = new Etudiant();
        e.setMatricule(matricule);
        e.setNom(nom);
        e.setPrenom(prenom);
        // Tu peux ajouter filiere, telephone plus tard

        boolean ok = new EtudiantDAO().ajouterEtudiant(e);

        if(ok) {
            JOptionPane.showMessageDialog(this, "Étudiant enregistré avec succès");
            vider();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void vider() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtAnnee.setText("");
        txtMatricule.setText("");
    }
}
