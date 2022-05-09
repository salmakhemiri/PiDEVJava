/*
 * Copyright comment here
 */
package com.mycompany.gestionEquipe.entities;

import javafx.beans.property.StringProperty;

/**
 *
 * @author 21626
 */
public class Equipe {
 private int id;
 private StringProperty nom;
 private StringProperty prenom;
 private int age;
 private StringProperty metier; 

    public Equipe() {
    }

    public Equipe(int id, String nom, String prenom, int age, String metier) {
        
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getNom() {
        return nom;
    }

    public void setNom(StringProperty nom) {
        this.nom = nom;
    }
 

    public StringProperty getPrenom() {
        return prenom;
    }

    public void setPrenom(StringProperty prenom) {
        this.prenom = prenom;
    }


    public StringProperty getMetier() {
        return metier;
    }

    public void setMetier(StringProperty metier) {
        this.metier = metier;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", metier=" + metier + '}';
    }

   

}