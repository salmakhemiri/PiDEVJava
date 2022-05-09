/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.gui;

import com.itextpdf.text.DocumentException;
import com.mycompany.gestionEquipe.entities.Equipe;
import com.mycompany.gestionEquipe.services.EquipeService;
import com.mycompany.gestionEquipe.utils.SMSAPI;
import com.mycompany.gestionEquipe.utils.ServicePdf;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import sun.applet.Main;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author 21626
 */
public class InscriEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> equipeTable;
    @FXML
    private TableColumn<Equipe,String> nomColumn;
    @FXML
    private TableColumn<Equipe,String> prenomColumn;
            
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfmetier;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsup;
    
    private InterfaceEquipe mainApp;

    @FXML
    private Button btnpdf;
    
   
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            EquipeService equipeService = new EquipeService();
        equipeTable.setItems(equipeService.afficher());
        System.out.println(equipeService.afficher());
                
        nomColumn.setCellValueFactory(value -> value.getValue().getNom());
        prenomColumn.setCellValueFactory(value -> value.getValue().getPrenom());
        equipeTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showDetails(newValue));
    }    
    
    public void setMainApp(InterfaceEquipe mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void Ajouter(ActionEvent event) throws AWTException {
       
        String nom = tfnom.getText();
       String prenom = tfprenom.getText();
      int age = Integer.parseInt(tfage.getText());
       String metier = tfmetier.getText();
    
    Equipe e = new Equipe();
    e.setNom(new SimpleStringProperty(nom));
    e.setPrenom(new SimpleStringProperty(prenom));
    e.setAge(age);
    e.setMetier(new SimpleStringProperty(metier));
    EquipeService es = new EquipeService();
    es.ajouterEquipe(e);
    SMSAPI.sendSMS("+21626629623", "Equipe Crée");
SystemTray tray = SystemTray.getSystemTray();

                //If the icon is a file
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                //Alternative (if the icon is on the classpath):
                //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);

                trayIcon.displayMessage("Notification ajout", "Equipe ajoutée avec succès", MessageType.INFO);
    EquipeService equipeService = new EquipeService();
        equipeTable.setItems(equipeService.afficher());
   
    }
 
 
        @FXML
    private void Modifier(ActionEvent event) {
          String nom = tfnom.getText();
       String prenom = tfprenom.getText();
       int age = Integer.parseInt(tfage.getText() );
       String metier = tfmetier.getText();
       
         Equipe e = new Equipe();
        int selectedIndex = equipeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Equipe equipe = equipeTable.getSelectionModel().getSelectedItem();
              EquipeService EquipeService = new EquipeService();
             equipe.setNom(new SimpleStringProperty(tfnom.getText()));
          equipe.setPrenom(new SimpleStringProperty(tfprenom.getText()));
          equipe.setAge(Integer.parseInt(tfage.getText()));
          equipe.setMetier(new SimpleStringProperty(tfmetier.getText()));
            
              EquipeService es = new EquipeService();
            es.Modifier(equipe);
            equipeTable.setItems(EquipeService.afficher());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equipe Selected");
            alert.setContentText("Please select a Equipe in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int selectedIndex = equipeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = equipeTable.getSelectionModel().selectedItemProperty().getValue().getId();
            EquipeService EquipeService = new EquipeService();
            EquipeService.supprimer(id);
            equipeTable.setItems(EquipeService.afficher());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equipe Selected");
            alert.setContentText("Please select an equipe in the table.");

            alert.showAndWait();
        }
    }
    
 
   
    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        ServicePdf sp= new ServicePdf();
        sp.equipePDF();
        
    }

    private void showDetails(Equipe newValue) {
        if (newValue != null){
           tfprenom.setText(newValue.getPrenom().get());
           tfnom.setText(newValue.getNom().get());
           tfmetier.setText(newValue.getMetier().get());
           tfage.setText(String.valueOf(newValue.getAge()));
        }else{
            tfprenom.setText("");
            tfnom.setText("");
            tfmetier.setText("");
            }
    }
    
}

