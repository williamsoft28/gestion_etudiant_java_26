package com.gestion.main;

import com.gestion.config.DatabaseMigration;
import com.gestion.ui.LoginFrame;

public class Main {
    public static void main(String[] args) {

        DatabaseMigration.migrate(); // création des tables
        new LoginFrame();            // lancement de l’interface
    }
}
