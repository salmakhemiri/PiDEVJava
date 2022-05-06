/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Technique;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author ASUS
 */
public class DataBase {
    private String url;
    private String login;
    private String password;
    private Connection connection;
    private Properties properties;
    private static DataBase instance;

    private DataBase() {
        try {
            properties = new Properties();
            //properties.load(new FileInputStream(new File("configuration.properties")));
            url = "jdbc:mysql://localhost:3306/mercato_1";
            login = "root";
            password = "";
           
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
    
}
