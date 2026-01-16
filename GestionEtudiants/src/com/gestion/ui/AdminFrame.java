package com.gestion.ui;

import javax.swing.*;

public class AdminFrame extends JFrame {

    public AdminFrame() {
        setTitle("Dashboard Administrateur");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Inscription", new InscriptionPanel());
        tabs.add("Affichage", new ListeEtudiantsPanel());

        add(tabs);
        setVisible(true);
    }
}
