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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mercato.DAO.Interface.SponsorInterface;
import mercato.Model.SponsoredUser;
import mercato.Model.Status;
import mercato.Model.Type;
import mercato.Model.User;
import mercato.Technique.DataBase;

/**
 *
 * @author PC-Yassine
 */
public class SponsorService implements SponsorInterface {

    private Connection db;
    public static SponsorService sponsorService = new SponsorService();


    
    public SponsorService() {
        try {
            db = DataBase.getInstance().getConnection();
        } catch (Exception e) {
        }
    }
    
    
    

    @Override
    public void AddSponsoredUser(SponsoredUser su) {
        String req = "insert into sponsoreduser (date,type,status,endDate,userid) values ('" + su.getDate() + "','" + su.getType() + "','" + su.getStatus() + "','" + su.getEndDate() + "','" + su.getUser().getId() + "') ";

        Statement statement;
        try {
            statement = db.createStatement();
            statement.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }


    
    
    @Override
    public Boolean DeletSponsoredUser(int id) {
        String req = "delete from sponsoreduser where sponsoredId=" + id;
        Statement statement;
        
        try {
            statement = db.createStatement();
            int result = statement.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public Boolean DowngradeSponsoredUser(SponsoredUser sp) {
        String query = "UPDATE sponsoreduser SET status = " + Status.disactivated + "WHERE id = " + sp.getSponsorId() ;
            
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
    
    
    //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    @Override
    public Boolean UpgradeSponsoredUser(SponsoredUser sp) {
       String query = "UPDATE sponsoreduser" + "SET type = " + Type.Premium + "WHERE id = " + sp.getSponsorId() ;

      
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
    public List<SponsoredUser> FindAllSponsoredUser() {
        String req = "select * from sponsoreduser ";
        List<SponsoredUser> spList = new ArrayList<>();

        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            SponsoredUser sp;
            while (result.next()) {
                sp = new SponsoredUser();
                sp.setDate(result.getDate("date"));
                sp.setType(Type.valueOf(result.getString("type")));
                sp.setUser(new ViewService().getUserById(result.getInt("userId")));
                sp.setStatus(Status.valueOf(result.getString("status")));
                sp.setEndDate(result.getDate("endDate"));

                spList.add(sp);

            }
            return spList;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spList;
    }
    
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    @Override
    public SponsoredUser FindSponsoredUser(int id) {
            SponsoredUser sp = new SponsoredUser();
        try {
            String query = "SELECT * FROM SponsoredUser WHERE sponsorId = ?";
            ResultSet res;
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            res = ps.executeQuery();
            if (res.next()) {
                sp.setSponsorId(id);
                sp.setDate(res.getDate("date"));
                sp.setType(Type.valueOf(res.getString("type")));
                sp.setUser(new ViewService().getUserById(res.getInt("userId")));
                sp.setStatus(Status.valueOf(res.getString("status")));
                sp.setEndDate(res.getDate("endDate"));
                
            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(SponsoredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp;

    }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public boolean isSponsoredUser(User u) {
            SponsoredUser sp = new SponsoredUser();
        try {
            String query = "SELECT * FROM SponsoredUser WHERE userId = ?";
            ResultSet res;
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, u.getId());
            res = ps.executeQuery();
            if (res.next()) {
                sp.setDate(res.getDate("date"));
                sp.setType(Type.valueOf(res.getString("type")));
                sp.setUser(u);
                sp.setStatus(Status.valueOf(res.getString("status")));
                sp.setEndDate(res.getDate("endDate"));
            }
            ps.close();
            res.close();
            
      return true;
        }
            
            catch (SQLException ex) {
            Logger.getLogger(SponsoredUser.class.getName()).log(Level.SEVERE, null, ex);
        return false; 
        }
           }

   


}
