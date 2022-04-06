/*
 * Copyright comment here
 */
package com.mycompany.gestionEquipe.entities;

/**
 *
 * @author 21626
 */
public class Equipe {
 private int id;
 private String nom;
 private String prenom;
 private int age;
 private String metier; 

    public Equipe() {
    }

    public Equipe(int id, String nom, String prenom, int age, String metier) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.metier = metier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", metier=" + metier + '}';
    }

   

}