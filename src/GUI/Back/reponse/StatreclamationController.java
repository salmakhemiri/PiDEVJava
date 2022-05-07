/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.reponse;

import Service.ReclamationService;
import foodapp.FoodApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DH Farouk
 */
public class StatreclamationController implements Initializable {

    @FXML
    private Button id_retour;
    @FXML
    private PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService p =new ReclamationService();
          chart.setTitle("etat: \n           (0 EN COURS) \n    (1 TRAITER)"); 
        try {
            chart.getData().setAll(new PieChart.Data("EN COURS", p.Recherche2()), new PieChart.Data("TRAITER", p.Recherche3())
                   
            );
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StatreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        
        
        
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
           // el page eli nheb nemchliha afficherparticipant comme example
            Parent root=FXMLLoader.load(getClass().getResource("afficherreclamation.fxml"));
            
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("reclamation");
            stage.show();
        } catch (IOException ex) {
                Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    private void PageReclamation(ActionEvent event)  throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    
}
