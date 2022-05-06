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
import java.util.logging.Level;
import java.util.logging.Logger;
import mercato.DAO.Interface.ViewInterface;
import mercato.Model.SponsoredUser;
import mercato.Model.User;
import mercato.Model.View;
import mercato.Technique.DataBase;

/**
 *
 * @author PC-Yassine
 */
public class ViewService implements ViewInterface{
    private Connection db;
    static ViewService viewService = new ViewService();

    public ViewService() {
        try {
            db = DataBase.getInstance().getConnection();
        } catch (Exception e) {
        }
    }
    
    @Override
    public boolean add(View v) {
        String query = "insert into viewsponsor (viewid,date,sponsorid,userid,viewed) values ('" + v.getViewId()+ "','" + v.getDate()+ "','" + v.getSp().getSponsorId() + "','" + v.getUser().getId() + "','" + v.isViewed() + "') ";
        Statement statement;
        try {
            statement = db.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public boolean viewed(View v) {
        String query = "UPDATE viewsponsor SET viewed = " + true + "WHERE id = " + v.getViewId() ;
            
           Statement statement;
        try {
            statement = db.createStatement();
            statement.executeUpdate(query);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<View> getallBySponsoredUser(SponsoredUser sp) {
               String req = "select * from view where userId="+ sp.getUser().getId();
        List<View> vList = new ArrayList<>();

        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            View v;
            while (result.next()) {
                v= new View();
                v.setDate(result.getDate("date"));
                v.setViewed(result.getBoolean("viewed"));
                v.setUser(new ViewService().getUserById(result.getInt("userId")));
                v.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                
                vList.add(v);
            
            }
            return vList;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vList;
    }


    @Override
    public View getById(int id) {
        View v = new View();
        try {
            String query = "SELECT * FROM sponsorview WHERE id = ?";
            ResultSet res;
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            res = ps.executeQuery();
            if (res.next()) {
                v.setDate(res.getDate("date"));
                v.setViewed(res.getBoolean("viewed"));
                v.setUser(new ViewService().getUserById(res.getInt("userId")));
                v.setSp(new SponsorService().FindSponsoredUser(res.getInt("sponsorId")));
                
            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(SponsoredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    
    public User getUserById(int id) {
        User u = new User();
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            ResultSet res;
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            res = ps.executeQuery();
            if (res.next()) {
                u.setId(id);
                u.setEmail(res.getString("email"));
                u.setUsername(res.getString("username"));
                u.setName(res.getString("last_name"));
                u.setLast_name(res.getString("name"));  
            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(SponsoredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    

    
}
