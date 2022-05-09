/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Reclamation;
import Utils.DataBase;
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
 * @author Farouk
 */
public class ReclamationService {
     Connection con = DataBase.getInstance().getConnection(); 
    private Statement stmt;
   public ReclamationService () {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
   } 
   
   public void ajouterReclamation(Reclamation R) { 
          ReclamationService cs =new ReclamationService(); 
      try { 
            String requete = "INSERT INTO Reclamation (description,date,nom,email,etat) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
           
            pst.setString(1, R.getDescription()); 
            pst.setString (2, R.getDate());  
             pst.setString(3, R.getNom()); 
              pst.setString(4, R.getEmail());  
                  pst.setInt(5, R.getEtat());  
            

           
            
            
            pst.executeUpdate();
            System.out.println("Reclamation  ajouté !");
  } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    public List<Reclamation> afficher() {
         List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Reclamation";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                
                 list.add(new Reclamation( rs.getInt(1),rs.getString(4), rs.getString(2),rs.getString(3),rs.getString (5),rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public void supprimer(Reclamation r) {
 try {
            String requete = "DELETE FROM Reclamation WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }
     
    public void Modifier(Reclamation r) {
        try {              
            String requete = "UPDATE Reclamation SET description=?,date=?,nom=?,email=?,etat=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            
            pst.setString(1, r.getDescription());
          
            pst.setString (2, r.getDate());
            pst.setString (3, r.getNom());
              pst.setString (4, r.getEmail());
                 pst.setInt (5, r.getEtat());
              
            pst.setInt (6, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public long Recherche2() throws SQLException {

  List<Reclamation> p = afficher();
        //return p.stream().filter(b -> b.getEtat()> 1).filter(b -> b.getEtat() <= 10 ).count();
         return p.stream().filter(b -> b.getEtat()== 0).count();
    }
     public long Recherche3() throws SQLException {

  List<Reclamation> p = afficher();
        //return p.stream().filter(b -> b.getEtat()> 10).filter(b -> b.getEtat()<= 50 ).count();
         return p.stream().filter(b -> b.getEtat()== 1).count();
    }
     

 
}
