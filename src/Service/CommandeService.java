/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Commande;
import Utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salma
 */
public class CommandeService {

    Connection con = MyConnection.getInstance().getCnx();
    private Statement stmt;

    public CommandeService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterCommande(Commande C) {
        CommandeService cs;
        cs = new CommandeService();
        try {
            String requete = "INSERT INTO commande (iduser,date,montant,etat) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setInt(1, C.getId_user());
            pst.setString(2, C.getDate());
            pst.setInt(3, C.getMontant());
            pst.setInt(4, C.getEtat());

            pst.executeUpdate();
            System.out.println("Commande  ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commande(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(Commande c) {
        try {
            String requete = "DELETE FROM commande WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Modifier(Commande C) {
        try {
            String requete = "UPDATE commande SET iduser=?,date=?,montant=?,etat=? WHERE id=? ";
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setInt(1, C.getId_user());
            pst.setString(2, C.getDate());
            pst.setInt(3, C.getMontant());
            pst.setInt(4, C.getEtat());
            pst.setInt(5, C.getId());
            pst.executeUpdate();
            System.out.println("Commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public long Recherche2() throws SQLException {

        List<Commande> C = afficher();
        //return p.stream().filter(b -> b.getEtat()> 1).filter(b -> b.getEtat() <= 10 ).count();
        return C.stream().filter(b -> b.getEtat() == 0).count();
    }

    public long Recherche3() throws SQLException {

        List<Commande> C = afficher();
        //return p.stream().filter(b -> b.getEtat()> 10).filter(b -> b.getEtat()<= 50 ).count();
        return C.stream().filter(b -> b.getEtat() == 1).count();
    }
}
