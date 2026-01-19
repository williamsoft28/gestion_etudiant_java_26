package com.gestion.ui;

import com.gestion.dao.EtudiantDAO;
import com.gestion.model.Etudiant;
import com.gestion.utils.AdminConfig;
import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {
        setTitle("Gestion des Étudiants - Connexion");
        setSize(420, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 242, 245));

        // Carte interne
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(330, 200));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Connexion", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(15));

        card.add(new JLabel("Matricule / Username"));
        userField = new JTextField();
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        card.add(userField);

        card.add(Box.createVerticalStrut(10));

        card.add(new JLabel("Mot de passe"));
        passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        card.add(passField);

        card.add(Box.createVerticalStrut(20));

        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setBackground(new Color(13, 110, 253));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(btnLogin);

        mainPanel.add(card);
        add(mainPanel);

        // Action du bouton login
        btnLogin.addActionListener(e -> seConnecter());

        setVisible(true);
    }

    private void seConnecter() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Login Admin
        if (user.equals(AdminConfig.USERNAME) && pass.equals(AdminConfig.PASSWORD)) {
            try {
                new AdminFrame(); // Dashboard Admin
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Erreur lors de l'ouverture de l'espace Admin",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        // Login Étudiant
        try {
            EtudiantDAO dao = new EtudiantDAO();
            Etudiant etudiant = dao.trouverParMatricule(user);

            if (etudiant != null) {
                new EtudiantFrame(etudiant);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Identifiants incorrects",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la connexion à la base de données",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
