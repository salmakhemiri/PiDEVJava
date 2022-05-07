/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Category;
import Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 * /**
 *
 * @author Aicha
 */
public class ServiceCategory {

    private Connection con;
    private Statement ste;

    public ServiceCategory() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouter(Category c) throws SQLException {
        ste = con.createStatement();
        System.out.println("Categpru" + c);
        String requeteInsert = "INSERT INTO `food`.`category` (`id`, `name`, `text`, `color`) VALUES (NULL, '" + c.getName() + "' ,  '" + c.getText() + "' , '" + c.getColor() + "');";
        JOptionPane.showMessageDialog(null, "Categorie ajoutée avec succées");
        ste.executeUpdate(requeteInsert);
    }

    public boolean delete(int id, String nom) throws SQLException {
        PreparedStatement prd = con.prepareStatement("DELETE FROM product WHERE category_id='" + id + "' ;");
        System.out.println("id= " + id);
        PreparedStatement pre = con.prepareStatement("DELETE FROM category WHERE name='" + nom + "' ;");
        prd.executeUpdate();
        pre.executeUpdate();
        JOptionPane.showMessageDialog(null, "Categorie supprimée avec succées");
        return true;
    }

    public boolean update(String nom1, String nom2, String color, String Description) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE category SET name= '" + nom2 + "' , color= '" + color + "' ,text= '" + Description + "'  WHERE name='" + nom1 + "' ;");
        System.out.println("name1= " + nom1);
        System.out.println("name2= " + nom2);
        JOptionPane.showMessageDialog(null, "Categorie modifiée avec succées");
        pre.executeUpdate();

        return true;
    }

    public List<Category> readAll() throws SQLException {
        List<Category> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from category");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nomCat = rs.getString("name");
            String couleurCat = rs.getString("color");
            String descriptionCat = rs.getString("text");
            Category c = new Category(nomCat, couleurCat, descriptionCat);
            arr.add(c);
        }
        return arr;
    }

    public ObservableList<Category> affichecat() throws SQLException {
        ObservableList<Category> oblist = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from category");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nomCat = rs.getString("name");
            String couleurCat = rs.getString("color");
            String descriptionCat = rs.getString("text");
            Category c = new Category(id, nomCat, couleurCat, descriptionCat);

            oblist.add(c);
        }
        return oblist;
    }

}
