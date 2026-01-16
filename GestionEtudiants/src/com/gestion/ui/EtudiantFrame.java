package com.gestion.ui;

import com.gestion.model.Etudiant;

import javax.swing.*;
import java.awt.*;

public class EtudiantFrame extends JFrame {

    public EtudiantFrame(Etudiant e) {
        setTitle("Espace Étudiant");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Nom : " + e.getNom()));
        panel.add(new JLabel("Prénom : " + e.getPrenom()));
        panel.add(new JLabel("Matricule : " + e.getMatricule()));
        panel.add(new JLabel("Filière : " + e.getFiliere()));
        panel.add(new JLabel("Téléphone : " + e.getTelephone()));

        add(panel);
        setVisible(true);
    }
}
