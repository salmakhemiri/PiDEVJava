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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 21626
 */
public class EquippementService {

    Connection con = MyConnection.getInstance().getCnx();
    private Statement stmt;

    public EquippementService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterEquippement(Equippement Eq) {
        EquippementService eq = new EquippementService();
        try {
            String requete = "INSERT INTO Equippement (nom,metier) VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
            // pst.setInt(1, S.getIdEquipe());
            pst.setString(1, Eq.getNom().get());
         pst.setString(2, Eq.getMetier().get());       

            pst.executeUpdate();
            System.out.println("Equippement  ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Equippement> afficher() {
        ObservableList<Equippement> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM Equippement";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equippement equippement = new Equippement();
                equippement.setId(rs.getInt(1));
                equippement.setNom(new SimpleStringProperty(rs.getString(2)));
                equippement.setMetier(new SimpleStringProperty(rs.getString(3)));

                list.add(equippement);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM Equippement WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    public void Modifier(Equippement Eq) {
        try {
            String requete = "UPDATE Equippement SET nom=?,metier=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setString(1, Eq.getNom().get());
            pst.setString(2, Eq.getMetier().get());
            pst.setInt(3, Eq.getId());
            pst.executeUpdate();
            System.out.println("Equippement modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
