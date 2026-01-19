package com.gestion.ui;

import com.gestion.dao.EtudiantDAO;
import com.gestion.model.Etudiant;
import com.gestion.utils.MatriculeGenerator;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class InscriptionPanel extends JPanel {

    private final JTextField txtNom, txtPrenom, txtTelephone, txtFiliere, txtMatricule;
    private final JSpinner spinnerDate;

    public InscriptionPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 240, 245));

        // Formulaire principal
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 10));
        formPanel.setBackground(new Color(240, 240, 245));

        txtNom = createTextField();
        txtPrenom = createTextField();
        txtTelephone = createTextField();
        txtFiliere = createTextField();
        txtMatricule = createTextField();
        txtMatricule.setEditable(false);
        txtMatricule.setBackground(new Color(220, 220, 220));

        // Date picker
        spinnerDate = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerDate, "yyyy-MM-dd");
        spinnerDate.setEditor(dateEditor);

        // Titre
        JLabel titre = new JLabel("Inscription d'un nouvel étudiant");
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setForeground(new Color(50, 50, 100));

        // Ajout des champs
        formPanel.add(createLabel("Nom *"));
        formPanel.add(txtNom);
        formPanel.add(createLabel("Prénom *"));
        formPanel.add(txtPrenom);
        formPanel.add(createLabel("Date de naissance *"));
        formPanel.add(spinnerDate);
        formPanel.add(createLabel("Téléphone"));
        formPanel.add(txtTelephone);
        formPanel.add(createLabel("Filière"));
        formPanel.add(txtFiliere);
        formPanel.add(createLabel("Matricule"));
        formPanel.add(txtMatricule);

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(240, 240, 245));

        JButton btnGenerer = createButton("Générer matricule", new Color(70, 130, 180));
        JButton btnEnregistrer = createButton("Enregistrer", new Color(34, 139, 34));

        buttonPanel.add(btnGenerer);
        buttonPanel.add(btnEnregistrer);

        btnGenerer.addActionListener(e -> genererMatricule());
        btnEnregistrer.addActionListener(e -> enregistrer());

        // Disposition
        add(titre, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(new Color(50, 50, 100));
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 12));
        tf.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200), 1));
        return tf;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        return btn;
    }

    private void genererMatricule() {
        try {
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();

            if(nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom et prénom sont obligatoires", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Récupérer la date
            java.util.Date dateUtil = (java.util.Date) spinnerDate.getValue();
            if (dateUtil == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date de naissance", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate dateNaissance = dateUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            txtMatricule.setText(MatriculeGenerator.generer(nom, prenom, dateNaissance));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la génération du matricule", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void enregistrer() {
        String matricule = txtMatricule.getText().trim();
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();

        if(matricule.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez générer le matricule et remplir tous les champs obligatoires", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            java.util.Date dateUtil = (java.util.Date) spinnerDate.getValue();
            LocalDate dateNaissance = dateUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            Etudiant e = new Etudiant();
            e.setMatricule(matricule);
            e.setNom(nom);
            e.setPrenom(prenom);
            e.setTelephone(txtTelephone.getText().trim());
            e.setFiliere(txtFiliere.getText().trim());
            e.setDateNaissance(dateNaissance);

            boolean ok = new EtudiantDAO().ajouterEtudiant(e);

            if(ok) {
                JOptionPane.showMessageDialog(this, "✓ Étudiant enregistré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                vider();
            } else {
                JOptionPane.showMessageDialog(this, "✗ Erreur lors de l'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void vider() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtTelephone.setText("");
        txtFiliere.setText("");
        txtMatricule.setText("");
        spinnerDate.setValue(new java.util.Date());
    }
}
