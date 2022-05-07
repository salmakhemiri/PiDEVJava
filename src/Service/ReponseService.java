/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import Entites.Reponse;

import Utils.DataBase;
import java.sql.Connection;
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
 * @author Salma
 */
public class ReponseService {

    Connection con = DataBase.getInstance().getConnection();
    private Statement stmt;

    public ReponseService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterReponse(Reponse R) { 
          ReponseService cs =new ReponseService(); 
      try { 
            String requete = "INSERT INTO Reponse(reclamation_id,commentaire) VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
          
           
           
            pst.setInt(1, R.getReclamation_id());   
             pst.setString(2, R.getCommentaire());
        
            pst.executeUpdate();
            System.out.println("Reponse  ajouté !");
  } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    

    public List<Reponse> afficher() {
        List<Reponse> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Reponse";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reponse(rs.getInt(1),rs.getInt(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(Reponse E) {
 try {
            String requete = "DELETE FROM Reponse WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, E.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    public void Modifier(Reponse E) {
        try {
            String requete = "UPDATE `reponse` SET `commentaire`='"+E.getCommentaire()+"' WHERE `id`='"+E.getId()+"'";
            PreparedStatement pst = con.prepareStatement(requete);

           
           // pst.setString(1, E.getCommentaire());
            
            pst.executeUpdate();
            System.out.println("Reponse modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
