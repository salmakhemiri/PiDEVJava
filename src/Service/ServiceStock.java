/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Fournisseurs;
import Entites.Stock;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Salma
 */
public class ServiceStock {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceStock() {
        cnx = DataBase.getInstance().getConnection();
    }

    public ArrayList<Stock> DisplayAll() throws SQLException {
        ArrayList<Stock> TabS = new ArrayList<>();
        String req = "SELECT * FROM stocks";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
   int total = Integer.parseInt(rs.getString("prix_unitaire")) *  Integer.parseInt(rs.getString("qte_prod"))  ;
   String total2 = Integer.toString(total);
   
            TabS.add(new Stock(rs.getInt("id"), rs.getString("qte_prod"), rs.getString("nom"), rs.getString("numerof"), rs.getString("prix_unitaire"), rs.getString("fournisseur"), rs.getInt("fournisseur_id"),total2));
        }

        return TabS;
    }

    public void ajouter(Stock S) throws SQLException {
        System.out.println("four:" + S.getFournisseur());
        System.out.println("fourID:" + S.getFournisseur_id());
        pre = cnx.prepareStatement("INSERT INTO stocks ( `nom`, `qte_prod`, `numerof`,  `prix_unitaire` ,  `fournisseur_id` , `fournisseur` ) VALUES ( ?, ?, ?, ?,?,?);");
        pre.setString(1, S.getNom());
        pre.setString(2, S.getQte_prod());
        pre.setString(3, S.getNumerof());
        pre.setString(4, S.getPrix_unitaire());
        pre.setInt(5, S.getFournisseur_id());
        pre.setString(6, S.getFournisseur());
        pre.executeUpdate();

    }

    public boolean update(int id, String nom, String prix_unitaire, String qte_prod) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE stocks SET nom= '" + nom + "' , prix_unitaire= '" + prix_unitaire + "' ,qte_prod= '" + qte_prod + "'   WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Stock modifié avec succées");
        pre.executeUpdate();

        return true;
    }

    public boolean delete(int id) throws SQLException {

        pre = cnx.prepareStatement("delete from stocks where id = '" + id + "'");
        pre.execute();
        return true;
    }

    public int count() {
        try {
            String req = "select count(*) AS total from stocks ";
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Fournisseurs detailsFournisseur(int id) throws SQLException {

        Fournisseurs F = null;
        ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select nom,numeor,email from fournisseur    where fournisseur.id = '" + id + "'");
        while (rs.next()) {

            String numero = rs.getString("numero");
            String name = rs.getString("nom");
            String email = rs.getString("email");
            // kif kif nraj3hom f ArrayList de type formateur

            F = new Fournisseurs(0, name, numero, "", 0, email);

        }
        return F;

    }

    public ArrayList<Stock> triParQT() throws SQLException {
        ArrayList<Stock> TabS = new ArrayList<>();
        String req = "SELECT * FROM stocks order by  prix_unitaire Desc";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabS.add(new Stock(rs.getInt("id"), rs.getString("qte_prod"), rs.getString("nom"), rs.getString("numerof"), rs.getString("prix_unitaire"), rs.getString("fournisseur"), rs.getInt("fournisseur_id")));
        }

        return TabS;
    }

    public ArrayList<Stock> triParNom() throws SQLException {
        ArrayList<Stock> TabS = new ArrayList<>();
        String req = "SELECT * FROM stocks order by  nom Desc";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabS.add(new Stock(rs.getInt("id"), rs.getString("qte_prod"), rs.getString("nom"), rs.getString("numerof"), rs.getString("prix_unitaire"), rs.getString("fournisseur"), rs.getInt("fournisseur_id")));
        }

        return TabS;
    }

    public ArrayList findFournisseur() throws SQLException {
        ArrayList arr = new ArrayList();
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select id,nom,numero,email from fournisseur  ");
        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("nom");
            String numero = rs.getString("numero");
            String email = rs.getString("email");
            Fournisseurs F = new Fournisseurs(id, name, numero, "", 0, email);

            arr.add(F);
        }
        return arr;

    }
    
    
   /* public long Recherche2() throws SQLException {

  List<Stock> p = DisplayAll();
        return p.stream().filter(b -> b.getQte_prod()> 1).filter(b -> b.getQte_prod()< 10 ).count();
    }
     public long Recherche2() throws SQLException {

  List<Stock> p = DisplayAll();
        return p.stream().filter(b -> b.getQte_prod()> 1).filter(b -> b.getQte_prod()< 10 ).count();
    }
       public long Recherche2() throws SQLException {

  List<Stock> p = DisplayAll();
        return p.stream().filter(b -> b.getQte_prod()> 1).filter(b -> b.getQte_prod()< 10 ).count();
    }*/
    
    
    
    
    
    
    

}
