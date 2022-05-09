/*
 * Copyright comment here
 */
package Entites;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 21626
 */
public class Equipe {
 private  IntegerProperty id;
 private  StringProperty nom;
 private  StringProperty prenom;
 private  IntegerProperty age;
 private  StringProperty metier; 

    public Equipe() {
    }

  public Equipe(String nom, String prenom,int age , String metier) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.age = new SimpleIntegerProperty(14);
		this.metier = new SimpleStringProperty(metier);
	}
public Equipe(int id,String nom, String prenom,int age , String metier) {
		this.id = new SimpleIntegerProperty(id);
                this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.age = new SimpleIntegerProperty(age);
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

public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	
	public StringProperty prenomProperty() {
		return prenom;
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}
	
	public IntegerProperty ageProperty() {
		return age;
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