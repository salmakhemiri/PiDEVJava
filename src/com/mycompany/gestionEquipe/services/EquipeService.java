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
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 21626
 */
public class EquipeService {
     Connection con = MyConnection.getInstance().getCnx(); 
    private Statement stmt;
    private Object myconnection;
   public EquipeService () {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
   } 
   public void ajouterEquipe(Equipe E) { 
          EquipeService cs =new EquipeService(); 
      try { 
            String requete = "INSERT INTO Equipe (nom,prenom,age,metier) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
           //pst.setInt(1, E.getId());
            pst.setString(1, E.getNom().get());  
             pst.setString(2, E.getPrenom().get()); 
             pst.setInt(3, E.getAge()); 
             pst.setString(4, E.getMetier().get()); 
            

           
            
            
            pst.executeUpdate();
            System.out.println("Equipe  ajouté !");
  } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    public ObservableList<Equipe> afficher() {
         ObservableList<Equipe> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM Equipe";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt(1));
                equipe.setNom(new SimpleStringProperty(rs.getString(2)));
                equipe.setPrenom(new SimpleStringProperty(rs.getString(3)));
                equipe.setMetier(new SimpleStringProperty(rs.getString(5)));
                equipe.setAge(rs.getInt(4));
                list.add(equipe);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM Equipe WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
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
            
            pst.setString(1, e.getNom().get());
            pst.setString(2, e.getPrenom().get());
            pst.setInt(3,e.getAge());
            pst.setString (4, e.getMetier().get());
            pst.setInt (5, e.getId());
            pst.executeUpdate();
            System.out.println("Equipe modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
}
