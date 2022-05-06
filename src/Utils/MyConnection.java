/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author salma
 */
public class MyConnection {

    /* 2 etpae*/
    public static MyConnection instance;
    public String url = "jdbc:mysql://localhost:3306/food";
    public String login = "root";
    public String pwd = "";
    Connection cnx;
    /* 1 etpae*/
    private MyConnection() {
        try {

            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public static MyConnection getInstance() {

        if (instance == null) {

            instance = new MyConnection();
        }
        return instance;

    }

    /* 3 etape */

    public Connection getCnx() {
        return cnx;
    }

}
