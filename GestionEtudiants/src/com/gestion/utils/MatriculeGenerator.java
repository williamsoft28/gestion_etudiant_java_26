package com.gestion.utils;

import java.util.Random;

public class MatriculeGenerator {

    public static String generer(String nom, String prenom, int annee) {
        char n = Character.toUpperCase(nom.charAt(0));
        char p = Character.toUpperCase(prenom.charAt(0));
        int random = new Random().nextInt(90) + 10; // 10 à 99

        return "" + n + p + annee + random;
    }
}
