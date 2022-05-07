/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import Service.ReponseService;

/**
 *
 * @author DH Farouk
 */
public class Reponse {
    int id,reclamation_id;
    String commentaire;

    public Reponse(int id, int reclamation_id, String commentaire) {
        this.id = id;
        this.reclamation_id = reclamation_id;
        this.commentaire = commentaire;
    }
    public Reponse( int id_reclamation, String commentaire) {
       
        this.reclamation_id = id_reclamation;
        this.commentaire = commentaire;
    }

    public Reponse() {
         //To change body of generated methods, choose Tools | Templates.
    }
    

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", reclamation_id=" + reclamation_id + ", commentaire=" + commentaire + '}';
    }

    

   
    
    
    
}
