/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Classe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mercato.DAO.Interface.Iuser;
import mercato.Model.User;
import mercato.Technique.DataBase;


/**
 *
 * @author lenovo
 */
public class UserService implements Iuser<User>{
 private Connection cnx = DataBase.getInstance().getConnection();

    
    @Override
    public void ajouter(User t) {
      try {
            String requete = "INSERT INTO user (username,roles,password,email,name,last_name,birth_date,speciality,status,experience,hight,weight,cv,media,company,position,pays_natals,sexe,lien_profil_pic) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getRoles());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getName());
            pst.setString(6, t.getLast_name());
            pst.setDate(7, (Date) t.getBirth_date());
            pst.setString(8, t.getSpeciality());
            pst.setString(9, t.getStatus());
            pst.setString(10, t.getExperience());
            pst.setInt(11, t.getHight());
            pst.setInt(12, t.getWeight());
            pst.setString(13, t.getCv());
            pst.setString(14, t.getMedia());
            pst.setString(15, t.getCompany());
            pst.setString(16, t.getPosition());
            pst.setString(17, t.getPays_natals());
            pst.setString(18, t.getSexe());
            pst.setString(19, t.getLien_profil_pic());
            
            
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(User t) {
 try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    @Override
    public void modifier(User t) {
        try {
            String requete = "UPDATE user SET username=?,roles=?,password=?,email=?,name=?,last_name=?,birth_date=?,speciality=?,status=?,experience=?,hight=?,weight=?,cv=?,media=?,company=?,position=?,pays_natals=?,sexe=?,lien_profil_pic=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(20, t.getId());
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getRoles());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getName());
            pst.setString(6, t.getLast_name());
            pst.setDate(7, (Date) t.getBirth_date());
            pst.setString(8, t.getSpeciality());
            pst.setString(9, t.getStatus());
            pst.setString(10, t.getExperience());
            pst.setInt(11, t.getHight());
            pst.setInt(12, t.getWeight());
            pst.setString(13, t.getCv());
            pst.setString(14, t.getMedia());
            pst.setString(15, t.getCompany());
            pst.setString(16, t.getPosition());
            pst.setString(17, t.getPays_natals());
            pst.setString(18, t.getSexe());
            pst.setString(19, t.getLien_profil_pic());
            
            pst.executeUpdate();
            System.out.println("Personne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
         List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21),rs.getInt(22)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
   
    public List<User> rechercher(String Username){
        List<User> list = new ArrayList<>();

    try{
    String requete = "SELECT * FROM user where username=?";
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setString(1,Username);
    ResultSet m = pst.executeQuery();
    while (m.next()) {
               list.add(new User(m.getInt("id"),m.getString("username"), m.getString("roles"),m.getString("password"),m.getString("email"),m.getString("name"),m.getString("last_name"),m.getDate("birth_date"),m.getString("speciality"),m.getString("status"),m.getString("experience"),m.getInt("hight"),m.getInt("weight"),m.getString("cv"),m.getString("media"),m.getString("company"),m.getString("position"),m.getString("pays_natals"),m.getString("sexe"),m.getString("lien_profil_pic")));
            }
    } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
    

     public List<User> trierUsernames() throws SQLException{
    List<User> list = this.afficher();

         list.sort((o1,o2)->o1.getUsername().compareTo(o2.getUsername()));

        return list; 
    }
    }
    

