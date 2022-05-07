/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Salma
 */
public class Reclamation{
 private int id   ;
 private String nom;
  private int etat;
  private String description ; 
  private String date ;
 private String email ; 
  
 

    public Reclamation() {
    }

    public Reclamation(int id, String nom,  String description, String date, String email,int etat) {
        this.id = id;
        this.nom = nom;
        
        this.description = description;
        this.date = date;
        this.email = email;
        this.etat = etat;
    }

    public Reclamation(String nom, int etat, String description, String  date, String email) {
        this.nom = nom;
        this.etat = etat;
        this.description = description;
        this.date = date;
        this.email = email;
    }
    

    public Reclamation(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }

    public String  getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setdate(String  date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamation" + "id=" + id + ", nom=" + nom + ", email=" + email +", description=" + description + ", date=" + date +  ", etat=" + etat + '}';
    }
 
}
