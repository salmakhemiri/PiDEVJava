/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataSource;
import mercato.DAO.Interface.ReclamationInterface;
import mercato.Model.Reclamation;
import mercato.Technique.DataBase;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class ReclamationService implements ReclamationInterface<Reclamation>{
    
    Connection cnx = DataBase.getInstance().getConnection();
    private Connection db;
    

     public void Ajouter(Reclamation r) {
        try {
            String requete = "insert into reclamation (title,body,subject,user_id,date,etatProbleme) values (?,?,?,?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, r.getTitle());
            pst.setString(2, r.getBody());
            pst.setString(3, r.getSubject());
            pst.setInt(4, r.getUserId());
            pst.setDate(5,(Date)r.getDate());
            pst.setString(6,r.getEtatProbleme());
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Supprimer(Reclamation r) {
         try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getRecId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Modifier(Reclamation r) {
        try {
            String requete = "UPDATE reclamation SET title=?,body=?,subject=?,user_id=?,date=?,etatProbleme=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, r.getTitle());
            pst.setString(2, r.getBody());
            pst.setString(3, r.getSubject());
            pst.setInt(4, r.getUserId());
            pst.setDate(5,(Date)r.getDate());
            pst.setString(6,r.getEtatProbleme());
            pst.setInt(7, r.getRecId());
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

     public List<Reclamation> Afficher() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"),rs.getString("title"),rs.getString("body"),rs.getString("subject"),rs.getInt("user_id"),rs.getDate("date"),rs.getString("etatProbleme")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     public List<Reclamation> Rechercher(Reclamation t){
        List<Reclamation> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where user_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getUserId());
            ResultSet rr = pst.executeQuery();
            while (rr.next()) {
               list.add(new Reclamation(rr.getInt("id"),rr.getString("title"),rr.getString("body"),rr.getString("subject"),rr.getInt("user_id"),rr.getDate("date"),rr.getString("etatProbleme")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
     
@Override
    public Reclamation getById(int id) {
        Reclamation r = new Reclamation();
        try {
            String query = "SELECT * FROM reclamation WHERE id = ?";
            ResultSet result;
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
                r.setRecId(result.getInt("id"));
                r.setTitle(result.getString("title"));
                r.setBody(result.getString("body"));
                r.setSubject(result.getString("subject"));
                r.setUserId(result.getInt("user_id"));
                r.setDate(result.getDate("date"));
                r.setEtatProbleme(result.getString("etatProbleme"));
             }
            ps.close();
            result.close();

        } catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    
    @Override
    public List<Reclamation> getByUserId(int user_id) {
    List<Reclamation> list = new ArrayList<>();
    try {
    String requete = "SELECT * FROM reclamation where user_id=?";
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setInt(1,user_id);
    ResultSet rr = pst.executeQuery();
    while (rr.next()) {
    list.add(new Reclamation(rr.getInt("id"),rr.getString("title"),rr.getString("body"),rr.getString("subject"),rr.getInt("user_id"),rr.getDate("date"),rr.getString("etatProbleme")));
    }
    } catch (SQLException ex) {
    System.err.println(ex.getMessage());
    }
    return list;
    
    }
    
    @Override
    public List<Reclamation> findByType(String type) {
    List<Reclamation> list = new ArrayList<>();
    try {
    String requete = "SELECT * FROM reclamation where subject=?";
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setString(1,type);
    ResultSet rr = pst.executeQuery();
    while (rr.next()) {
    list.add(new Reclamation(rr.getInt("id"),rr.getString("title"),rr.getString("body"),rr.getString("subject"),rr.getInt("user_id"),rr.getDate("date"),rr.getString("etatProbleme")));
    }
    } catch (SQLException ex) {
    System.err.println(ex.getMessage());
    }
    return list;
    }
    
    @Override
    public List<Reclamation> getByEtat(Reclamation t,String etatProbleme) {
    List<Reclamation> list = new ArrayList<>();
    try {
    String requete = "SELECT * FROM reclamation where etatProbleme=?";
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setString(1,etatProbleme);
    ResultSet rr = pst.executeQuery();
    while (rr.next()) {
    list.add(new Reclamation(rr.getInt("id"),rr.getString("title"),rr.getString("body"),rr.getString("subject"),rr.getInt("user_id"),rr.getDate("date"),rr.getString("etatProbleme")));
    }
    } catch (SQLException ex) {
    System.err.println(ex.getMessage());
    }
    return list;
    
    }
        
    @Override
    public List<Reclamation> Trier() {
    List<Reclamation> list = this.Afficher();
    
        list.sort((Reclamation o1 ,Reclamation o2)->o2.getDate().compareTo(o2.getDate()));
        return list;
   
    }

   
}
