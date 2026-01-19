-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestion_etudiants;

-- Utiliser la base de données
USE gestion_etudiants;

-- Création de la table etudiants
CREATE TABLE IF NOT EXISTS etudiants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    matricule VARCHAR(20) NOT NULL UNIQUE,
    filiere VARCHAR(50),
    telephone VARCHAR(20),
    date_naissance DATE
);