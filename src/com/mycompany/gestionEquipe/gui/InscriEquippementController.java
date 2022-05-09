/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.gui;

import com.mycompany.gestionEquipe.entities.Equipe;
import com.mycompany.gestionEquipe.entities.Equippement;
import com.mycompany.gestionEquipe.services.EquipeService;
import com.mycompany.gestionEquipe.services.EquippementService;
import com.mycompany.gestionEquipe.utils.Mailapi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class InscriEquippementController implements Initializable {

    @FXML
    private TextField tfnom1;
    @FXML
    private TextField tfmetier1;
    @FXML
    private TableView<Equippement> tb;
    @FXML
    private TableColumn<Equippement, String> c1;
    @FXML
    private TableColumn<Equippement, String> c2;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsuppr;
InterfaceEquippement mainApp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EquippementService equippementService = new EquippementService();
        tb.setItems(equippementService.afficher());
        System.out.println(equippementService.afficher());
                
        c1.setCellValueFactory(value -> value.getValue().getNom());
        c2.setCellValueFactory(value -> value.getValue().getMetier());
   tb.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showDetails(newValue));
    }    
    
    public void setMainApp(InterfaceEquippement mainApp){
        this.mainApp = mainApp;
        
    }   

    @FXML
    private void ajouuuuter(ActionEvent event) {
            
        String nom = tfnom1.getText();
       
       String metier = tfmetier1.getText();
    
    Equippement eq = new Equippement();
    eq.setNom(new SimpleStringProperty(nom));
    eq.setMetier(new SimpleStringProperty(metier));
    EquippementService q = new EquippementService();
    q.ajouterEquippement(eq);
    Mailapi.send("fedi.jouini@esprit.tn", "191JMT3782f", "fedi_jwini@outlook.com", "New Equippement Created","Equippement crée avec succées");
    EquippementService equippementService = new EquippementService();
        tb.setItems(equippementService.afficher());
    
    }


    @FXML
    private void supprimerrrrr(ActionEvent event) {
          int selectedIndex = tb.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = tb.getSelectionModel().selectedItemProperty().getValue().getId();
            EquippementService equippementServ = new EquippementService();
            equippementServ.supprimer(id);
            tb.setItems(equippementServ.afficher());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equippement Selected");
            alert.setContentText("Please select an equippement in the table.");

            alert.showAndWait();
    }
    
}

    @FXML
    private void modifiiiiier(ActionEvent event) {
        String nom = tfnom1.getText();
        String metier = tfmetier1.getText();
       
         Equipe e = new Equipe();
        int selectedIndex = tb.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Equippement equippement = tb.getSelectionModel().getSelectedItem();
              EquippementService EquippementService = new EquippementService();
             equippement.setNom(new SimpleStringProperty(tfnom1.getText()));
          equippement.setMetier(new SimpleStringProperty(tfmetier1.getText()));
            
              EquippementService es = new EquippementService();
            es.Modifier(equippement);
            tb.setItems(EquippementService.afficher());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equipe Selected");
            alert.setContentText("Please select a Equipe in the table.");

            alert.showAndWait();
        }
    }
       private void showDetails(Equippement newValue) {
        if (newValue != null){
           tfnom1.setText(newValue.getNom().get());
           tfmetier1.setText(newValue.getMetier().get());
          
        }else{
          
            tfnom1.setText("");
            tfmetier1.setText("");
            }
    }
}
