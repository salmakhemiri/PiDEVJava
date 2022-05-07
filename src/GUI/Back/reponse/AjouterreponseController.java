/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.reponse;

import Entites.Reclamation;
import Entites.Reponse;
import GUI.Back.reclamation.AfficherreclamationController;
import Service.ReclamationService;
import Service.ReponseService;
import foodapp.MainClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DH Farouk
 */
public class AjouterreponseController implements Initializable {

    @FXML
    private TextField id_reclamation_id;
    @FXML
    private TextField id_commentaire;
    @FXML
    private Button insert;
    @FXML
    private Label error_commentaire;
    private boolean  verificationcommentaire;
    @FXML
    private Button id_retour;
    @FXML
    private Button Product;
    @FXML
    private Button category;
    @FXML
    private Button Stock;
    @FXML
    private Button Fournisseurs;
    @FXML
    private Button Commande;
    @FXML
    private Button Livreur;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse;
    @FXML
    private Button Equipe;
    @FXML
    private Button Equipement;
    @FXML
    private Button Client;
    @FXML
    private Button Personnel;
    @FXML
    private Button Front;
    @FXML
    private Button Logout;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AfficherreclamationController a= new AfficherreclamationController();
        
       
        
        id_reclamation_id.setText(String.valueOf( AfficherreclamationController.id_reclamation_reponse));
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException
{
    if(verificationcommentaire){
        ReponseService rs=new ReponseService();
         Reponse E =new  Reponse();
        
        E.setReclamation_id(Integer.parseInt (id_reclamation_id.getText()));
        E.setCommentaire(id_commentaire.getText());
        
        rs.ajouterReponse(E);
    }}

    @FXML
    private void commentaire(KeyEvent event) {
         int nbNonChar = 0;
            for (int i = 1; i < id_commentaire.getText().trim().length(); i++) {
                char ch = id_commentaire.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && id_commentaire.getText().trim().length() >=10) {
            
            error_commentaire.setText("commentaire valide");
            
            verificationcommentaire = true;
            } else {
              
              error_commentaire.setText("Il faut au moins 10 caracteres");
               verificationcommentaire = false;

            }
        
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reponse/afficherreponse.fxml")));
        stage.setScene(scene);
        stage.show();
    }

   

    @FXML
    private void PageProduct(ActionEvent event) {
    }

    @FXML
    private void PageCategory(ActionEvent event) {
    }

    @FXML
    private void PageStock(ActionEvent event) {
    }

    @FXML
    private void PageFournisseur(ActionEvent event) {
    }

    @FXML
    private void PageCommande(ActionEvent event) {
    }

    @FXML
    private void PageLivreur(ActionEvent event) {
    }

    @FXML
    private void PageReclamation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageEquipe(ActionEvent event) {
    }

    @FXML
    private void PageEquipement(ActionEvent event) {
    }

    @FXML
    private void PageClient(ActionEvent event) {
    }

    @FXML
    private void PagePersonnel(ActionEvent event) {
    }

    @FXML
    private void Front(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }
    }
    

