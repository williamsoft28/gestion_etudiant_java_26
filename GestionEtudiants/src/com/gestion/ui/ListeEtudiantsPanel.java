package com.gestion.ui;

import com.gestion.dao.EtudiantDAO;
import com.gestion.model.Etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeEtudiantsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public ListeEtudiantsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"Matricule", "Nom", "Prénom"}, 0
        );

        table = new JTable(model);
        charger();

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> supprimer());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnSupprimer, BorderLayout.SOUTH);
    }

    private void charger() {
        model.setRowCount(0);
        List<Etudiant> liste = new EtudiantDAO().findAll();

        for (Etudiant e : liste) {
            model.addRow(new Object[]{
                    e.getMatricule(),
                    e.getNom(),
                    e.getPrenom()
            });
        }
    }

    private void supprimer() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String matricule = model.getValueAt(row, 0).toString();
        new EtudiantDAO().supprimer(matricule);
        charger();
    }
}
