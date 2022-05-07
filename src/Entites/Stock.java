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
public class Stock {

    private int id;
    private String qte_prod;
    private String nom;
    private String numerof;
    private String prix_unitaire;
    private String fournisseur;
    private int fournisseur_id;
    private String total ;

    public Stock(int id, String qte_prod, String nom, String numerof, String prix_unitaire, String fournisseur, int fournisseur_id) {
        this.id = id;
        this.qte_prod = qte_prod;
        this.nom = nom;
        this.numerof = numerof;
        this.prix_unitaire = prix_unitaire;
        this.fournisseur = fournisseur;
        this.fournisseur_id = fournisseur_id;
  
        
    }
    public Stock(int id, String qte_prod, String nom, String numerof, String prix_unitaire, String fournisseur, int fournisseur_id,String total) {
        this.id = id;
        this.qte_prod = qte_prod;
        this.nom = nom;
        this.numerof = numerof;
        this.prix_unitaire = prix_unitaire;
        this.fournisseur = fournisseur;
        this.fournisseur_id = fournisseur_id;
             this.total=total;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(String qte_prod) {
        this.qte_prod = qte_prod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumerof() {
        return numerof;
    }

    public void setNumerof(String numerof) {
        this.numerof = numerof;
    }

    public String getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(String prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getFournisseur_id() {
        return fournisseur_id;
    }

    public void setFournisseur_id(int fournisseur_id) {
        this.fournisseur_id = fournisseur_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", qte_prod=" + qte_prod + ", nom=" + nom + ", numerof=" + numerof + ", prix_unitaire=" + prix_unitaire + ", fournisseur=" + fournisseur + ", fournisseur_id=" + fournisseur_id + '}';
    }

    public int getgetQte_prod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
