/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Salma
 */
public class Fournisseurs {

    private int id;
    private String nom;
    private String numero;
    private String designation;
    private int statu;
    private String email;

    public Fournisseurs(int id, String nom, String numero, String designation, int statu, String email) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.designation = designation;
        this.statu = statu;
        this.email = email;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fournisseurs{" + "id=" + id + ", nom=" + nom + ", numero=" + numero + ", designation=" + designation + ", statu=" + statu + ", email=" + email + '}';
    }

}
