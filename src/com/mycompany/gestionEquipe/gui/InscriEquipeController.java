/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionEquipe.gui;

import com.mycompany.gestionEquipe.entities.Equipe;
import com.mycompany.gestionEquipe.services.EquipeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 21626
 */
public class InscriEquipeController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
       
        String nom = tfnom.getText();
       String prenom = tfprenom.getText();
       int age = Integer.parseInt(tfage.getText() );
       String metier = tfmetier.getText();
    
    Equipe e = new Equipe(0,nom,prenom,age,metier);
    EquipeService es = new EquipeService();
    es.ajouterEquipe(e);
    
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }
    
}
