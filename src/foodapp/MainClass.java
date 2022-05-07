/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodapp;


import Entites.Reclamation;
import Entites.Reponse;

import Service.ReclamationService;
import Service.ReponseService;

import Utils.MyConnection;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author salma
 */
public class MainClass extends Application {
    
 
@Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/reponse/ajouterreponse.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}                                   

