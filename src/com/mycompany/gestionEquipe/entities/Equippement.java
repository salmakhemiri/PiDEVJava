/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.entities;

/**
 *
 * @author 21626
 */
public class Equippement {
    
private int id   ;
 private String nom ;
 private String metier ;

    public Equippement() {
    }

    public Equippement(int id, String nom, String metier) {
        this.id = id;
        this.nom = nom;
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

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    @Override
    public String toString() {
        return "Equippement{" + "id=" + id + ", nom=" + nom + ", metier=" + metier + '}';
    }

}



