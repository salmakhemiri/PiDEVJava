/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Commande;

import Entites.Commande;
import Service.CommandeService;
import foodapp.MainClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author oubei
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField id_montant;
    @FXML
    private TextField id_etat;
    @FXML
    private Button insert;
    @FXML
    private DatePicker id_date;
    @FXML
    private Label errurmontant;
    @FXML
    private Label erruretat;
    @FXML
    private Label errurdate;

    private boolean verificationmontant;
    @FXML
    private Button id_page;
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
        // TODO
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        if (verificationmontant) {
            CommandeService cs = new CommandeService();
            Commande C = new Commande();
            C.setMontant(Integer.parseInt(id_montant.getText()));
            C.setEtat(Integer.parseInt(id_etat.getText()));
            Date datee = Date.valueOf(id_date.getValue());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(datee);
            C.setDate(strDate);
            cs.ajouterCommande(C);

        }
    }

    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @FXML
    private void testmontant(KeyEvent event) {
        int nbChar = 0;
        for (int i = 1; i < id_montant.getText().trim().length(); i++) {
            char ch = id_montant.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
            //System.out.println(nbChar);
        }

        if (isNumeric(id_montant.getText())) {
            errurmontant.setText("montant valide");

            verificationmontant = true;
        } else {
            errurmontant.setText("montant non valide");
            verificationmontant = false;

        }
    }

    @FXML
    private void testdate(KeyEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            // el page eli nheb nemchliha afficherparticipant comme example
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCommande.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Commande");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void PageLivreur(ActionEvent event) {
    }

    @FXML
    private void PageReclamation(ActionEvent event) {
    }

    @FXML
    private void PageReponse(ActionEvent event) {
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
