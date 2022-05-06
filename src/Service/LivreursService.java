/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Livreurs;
import Utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salma
 */
public class LivreursService {
    Connection con = MyConnection.getInstance().getCnx();
    private Statement stmt;

    public LivreursService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterLivreurs(Livreurs L) {
        LivreursService cs = new LivreursService();
        try {
            String requete = "INSERT INTO livreurs (nom,prenom,tel,email) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
            // pst.setInt(1, code.getIdCodePromo());
            pst.setString(1, L.getNom());
            pst.setString(2, L.getPrenom());
            pst.setInt(3, L.getTel());
            pst.setString(4, L.getEmail());


            pst.executeUpdate();
            System.out.println("livreurs  ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Livreurs> afficher() {
        List<Livreurs> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM livreurs";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Livreurs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(Livreurs l) {
        try {
            String requete = "DELETE FROM livreurs WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, l.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Modifier(Livreurs l) {
        try {
            String requete = "UPDATE livreurs SET nom=?,prenom=?,tel=?,email=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setString(1, l.getNom());
            pst.setString(2, l.getPrenom());
            pst.setInt(3, l.getTel());
            pst.setString(4, l.getEmail());
            pst.setInt(5, l.getId());
            pst.executeUpdate();
            System.out.println("Livreurs modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
