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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import mercato.DAO.Interface.MarketPlaceInterface;
import mercato.Model.Product;

import mercato.Model.SponsoredUser;
import mercato.Technique.DataBase;

/**
 *
 * @author PC-Yassine
 */
public class MarketPlaceService implements MarketPlaceInterface {

    public static void setCellFactory(Callback<ListView<Product>, ListCell<Product>> callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Connection db = DataBase.getInstance().getConnection();

    ;

    @Override
    public boolean addProduct(Product p) {
        //db = DataBase.getInstance().getConnection();
        String req = "insert into product (name,description,image1,image2,image3,image4,price,productType,state,sponsorId,date,quantity) values ('" + p.getName() + "','" + p.getDescription() + "','" + p.getImage1() + "','" + p.getImage2() + "','" + p.getImage3() + "','" + p.getImage4() + "','" + p.getPrice() + "','" + p.getPt() + "','" + p.getState() + "','" + p.getSp().getSponsorId() + "','" + p.getDate() + "','" + p.getQuantity() + "') ";

        Statement statement;
        try {
            statement = db.createStatement();
            statement.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MarketPlaceService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean UpdateProduct(Product p) {
        String query = "update Product SET  name = ?, description = ?, image1 = ?, image2 = ?, image3 = ?, image4 = ?, price = ?, productType = ?, state = ?, quantity = ? WHERE productId = " + p.getId();
        PreparedStatement ps;
        try {
            ps = db.prepareStatement(query);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage1());
            ps.setString(4, p.getImage2());
            ps.setString(5, p.getImage3());
            ps.setString(6, p.getImage4());
            ps.setFloat(7, p.getPrice());
            ps.setString(8, p.getPt());
            ps.setString(9, p.getState());
            ps.setInt(10, p.getQuantity());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MarketPlaceService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Product> ListAll() {
        String req = "select * from product ";
        List<Product> products = new ArrayList<>();
        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            Product p;
            while (result.next()) {
                p = new Product();
                p.setDate(result.getDate("date"));
                p.setPt(String.valueOf(result.getString("productType")));
                p.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                p.setState(String.valueOf(result.getString("state")));
                p.setImage1(result.getString("image1"));
                p.setImage2(result.getString("image2"));
                p.setImage3(result.getString("image3"));
                p.setImage4(result.getString("image4"));
                p.setPrice(result.getFloat("price"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setId(result.getInt("productId"));
                p.setQuantity(result.getInt("quantity"));

                products.add(p);

            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String req = "select * from product where productId=" + id;

        Statement statement;
        Product p = new Product();
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {
                p.setId(result.getInt("productId"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setImage1(result.getString("image1"));
                p.setImage2(result.getString("image2"));
                p.setImage3(result.getString("image3"));
                p.setImage4(result.getString("image4"));
                p.setPrice(result.getFloat("price"));
                p.setPt(String.valueOf(result.getString("productType")));
                p.setState(String.valueOf(result.getString("state")));
                p.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                p.setDate(result.getDate("date"));
                p.setQuantity(result.getInt("quantity"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public List<Product> getBySponsoredUser(SponsoredUser sp) {
        String req = "select * from product where sponsorId=" + sp.getSponsorId();
        List<Product> products = new ArrayList<>();

        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            Product p;
            while (result.next()) {
                p = new Product();
                p.setDate(result.getDate("date"));
                p.setPt(String.valueOf(result.getString("productType")));
                p.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                p.setState(String.valueOf(result.getString("state")));
                p.setImage1(result.getString("image1"));
                p.setImage2(result.getString("image2"));
                p.setImage3(result.getString("image3"));
                p.setImage4(result.getString("image4"));
                p.setPrice(result.getFloat("price"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setId(result.getInt("productId"));
                p.setQuantity(result.getInt("quantity"));

                products.add(p);

            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    @Override
    public boolean deleteProduct(int id) {
        String req = "delete from product where productId=" + id;
        Statement statement;

        try {
            statement = db.createStatement();
            statement.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Product> findMyProdByName(int i, String nom) {

        String req = "select * from product where sponsorId = " + i + " and name='" + nom + "' ";

        List<Product> productList = new ArrayList<>();

        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            Product p;
            while (result.next()) {
                p = new Product();
                p.setDate(result.getDate("date"));
                p.setPt(String.valueOf(result.getString("productType")));
                p.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                p.setState(String.valueOf(result.getString("state")));
                p.setImage1(result.getString("image1"));
                p.setImage2(result.getString("image2"));
                p.setImage3(result.getString("image3"));
                p.setImage4(result.getString("image4"));
                p.setPrice(result.getFloat("price"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setId(result.getInt("productId"));
                p.setQuantity(result.getInt("quantity"));

                productList.add(p);

            }
            return productList;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }

    @Override
    public List<Product> findProdByName(String nom) {

        String req = "select * from product where name='" + nom + "' ";

        List<Product> productList = new ArrayList<>();

        Statement statement;
        try {
            statement = db.createStatement();
            ResultSet result = statement.executeQuery(req);
            Product p;
            while (result.next()) {
                p = new Product();
                p.setDate(result.getDate("date"));
                p.setPt(String.valueOf(result.getString("productType")));
                p.setSp(new SponsorService().FindSponsoredUser(result.getInt("sponsorId")));
                p.setState(String.valueOf(result.getString("state")));
                p.setImage1(result.getString("image1"));
                p.setImage2(result.getString("image2"));
                p.setImage3(result.getString("image3"));
                p.setImage4(result.getString("image4"));
                p.setPrice(result.getFloat("price"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setId(result.getInt("productId"));
                p.setQuantity(result.getInt("quantity"));

                productList.add(p);

            }
            return productList;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }
}
