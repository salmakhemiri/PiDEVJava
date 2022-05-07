/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 * @author Salma
 */
public class Commande {
    private int id;
    private int id_user;
    private String date;
    private int montant;
    private int etat;

    public Commande() {
    }

  

    


    public Commande(int id, int id_user, String date, int montant, int etat) {
        this.id = id;
        this.id_user = id_user;
        this.date = date;
        this.montant = montant;
        this.etat = etat;

    }

    public Commande(int id_user, String date, int montant, int etat) {
        this.id_user = id_user;
        this.date = date;
        this.montant = montant;
        this.etat = etat;
    }


    public Commande(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_user=" + id_user + ", date=" + date + ", montant=" + montant + ", etat=" + etat + '}';
    }


}
