/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import Entites.Category;
import Entites.Product;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aicha
 */
public class ServiceProduct {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceProduct() {
        cnx = DataBase.getInstance().getConnection();
    }

    public ArrayList<Product> DisplayAll() throws SQLException {
        ArrayList<Product> TabF = new ArrayList<>();
        String req = "SELECT * FROM product";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabF.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("price"), rs.getString("image"), rs.getString("quantite"), rs.getString("discount"), rs.getString("initial_price")));
        }

        return TabF;
    }

    public void ajouter(Product P) throws SQLException {

        pre = cnx.prepareStatement("INSERT INTO product ( `name`, `image`, `description`,  `price` ,  `quantite` ,  `discount`,`category_id`,`initial_price`) VALUES ( ?, ?, ?, ?,?,?,?,?);");
        pre.setString(1, P.getName());
        pre.setString(2, P.getImage());
        pre.setString(3, P.getDescription());
        pre.setString(4, P.getPrice());
        pre.setString(5, P.getQuantite());
        pre.setString(6, P.getDiscount());
        pre.setInt(7, P.getCategory_id());
        pre.setString(8, P.getInitial_price());
        pre.executeUpdate();
        AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);

    }

    public boolean update(int id, String nom, String init_price, String discount, String price, String Description, String QT) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE product SET name= '" + nom + "' , initial_price= '" + init_price + "' ,description= '" + Description + "'  ,price= '" + price + "'  ,discount= '" + discount + "' ,quantite= '" + QT + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Product modifiée avec succées");
        pre.executeUpdate();

        return true;
    }

    public boolean delete(int id) throws SQLException {

        pre = cnx.prepareStatement("delete from product where id = '" + id + "'");
        pre.execute();
        return true;
    }

    public Product details(int id) throws SQLException {

        String req = "select * from product where id ='" + id + "'";

        Product P = null;

        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                //n7ot les valeurs recupé fi variable de type formation ( moch lezem Array list 5ater 3andi formation brk )

                P = new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("price"), rs.getString("image"), rs.getString("quantite"), rs.getString("discount"), rs.getString("initial_price"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return P;
    }

    public Category detailsCategory(int id) throws SQLException {

        Category C = null;
        ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select name,text,color from category    where category.id = '" + id + "'");
        while (rs.next()) {

            String color = rs.getString("color");
            String name = rs.getString("name");
            String description = rs.getString("text");

            C = new Category(0, name, description, color);

        }
        return C;

    }

    public ArrayList<Product> triParNombre() throws SQLException {
        ArrayList<Product> TabF = new ArrayList<>();
        String req = "SELECT * FROM product order by quantite ";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            TabF.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("price"), rs.getString("image"), rs.getString("quantite"), rs.getString("discount"), rs.getString("initial_price")));

        }
        return TabF;
    }

    public ArrayList<Product> triParTitre() throws SQLException {
        ArrayList<Product> TabF = new ArrayList<>();
        String req = "SELECT * FROM product order by name ";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            TabF.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("price"), rs.getString("image"), rs.getString("quantite"), rs.getString("discount"), rs.getString("initial_price")));

        }
        return TabF;
    }

    public ArrayList findCatrgory() throws SQLException {
        ArrayList arr = new ArrayList();
        ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select id,name,text,color from category  ");
        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("text");
            String color = rs.getString("color");

            Category f = new Category(id, name, description, color);
            arr.add(f);
        }
        return arr;

    }

}
