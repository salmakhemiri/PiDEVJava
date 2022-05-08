/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.services;
import com.mycompany.gestionEquipe.entities.Equipe;
import com.mycompany.gestionEquipe.entities.Equippement;
import com.mycompany.gestionEquipe.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21626
 */
public class EquipeService {
     Connection con = MyConnection.getInstance().getCnx(); 
    private Statement stmt;
   public EquipeService () {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
   } 
   public void ajouterEquipe(Equipe E) { 
          EquipeService es =new EquipeService(); 
      try { 
            String requete = "INSERT INTO Equipe (nom,prenom,age,metier) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, E.getNom());  
             pst.setString(2, E.getPrenom()); 
             pst.setInt(3, E.getAge()); 
             pst.setString(4, E.getMetier()); 
            pst.executeUpdate();
            System.out.println("Equipe  ajouté !");
  } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    public List<Equipe> afficher() {
         List<Equipe> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Equipe";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Equipe(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public void supprimer(Equipe e) {
 try {
            String requete = "DELETE FROM Equipe WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }
     
    public void Modifier(Equipe e) {
        try {              
            String requete = "UPDATE Equipe SET nom=?,prenom=?,age=?,metier=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            
            pst.setString(1, e.getNom());
            pst.setString(2, e.getPrenom());
            pst.setInt(3,e.getAge());
            pst.setString (4, e.getMetier());
            pst.setInt (5, e.getId());
            pst.executeUpdate();
            System.out.println("Equipe modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
