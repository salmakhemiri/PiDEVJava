/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.entities;

import javafx.beans.property.StringProperty;

/**
 *
 * @author 21626
 */
public class Equippement {
    
 private int id;
 private StringProperty nom;
 private StringProperty metier ;

    public Equippement() {
    }

   public Equippement(int id, String nom, String metier) {  
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

    public StringProperty getMetier() {
        return metier;
    }

    public void setMetier(StringProperty metier) {
        this.metier = metier;
    }


    @Override
    public String toString() {
        return "Equippement{" + "id=" + id + ", nom=" + nom + ", metier=" + metier + '}';
    }

}



