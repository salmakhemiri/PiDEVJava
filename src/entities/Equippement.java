/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 21626
 */
public class Equippement {
    
private  IntegerProperty id   ;
 private  StringProperty nom ;
 private  StringProperty metier ;

    public Equippement() {
    }

   public Equippement(String nom, String metier) {
		this.nom = new SimpleStringProperty(nom);
		this.metier = new SimpleStringProperty(metier);
		
   }
    public Equippement(Integer id,String nom, String metier) {
        this.id = new SimpleIntegerProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.metier = new SimpleStringProperty(metier);
		
   }

    public int getId() {
        return id.get();
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }
   
	
	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
	public StringProperty nomProperty() {
		return nom;
	}

	public String getMetier() {
		return metier.get();
	}

	public void setMetier(String metier) {
		this.metier.set(metier);
	}
	
	public StringProperty metierProperty() {
		return metier;
	}
}



