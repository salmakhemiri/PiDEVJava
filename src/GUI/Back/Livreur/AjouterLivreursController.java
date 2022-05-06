/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Livreur;

import Entites.Livreurs;
import Service.LivreursService;
import foodapp.MainClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
 * @author oubei
 */
public class AjouterLivreursController implements Initializable {

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_prenom;
    @FXML
    private TextField id_telephone;
    @FXML
    private TextField id_email;
    @FXML
    private Button insert;
    @FXML
    private Label erruernom;

    private boolean verificationnom;
    @FXML
    private Label erreurtel;
    @FXML
    private Label erreuremail;
    private boolean verificationEmail;
    private boolean verificationPhone;
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
    private void Ajouter(ActionEvent event) {
        if (verificationnom && verificationEmail && verificationPhone) {

            LivreursService cs = new LivreursService();
            Livreurs L = new Livreurs();
            L.setNom(id_nom.getText());
            L.setPrenom(id_prenom.getText());
            L.setTel(Integer.parseInt(id_telephone.getText()));
            L.setEmail(id_email.getText());
            cs.ajouterLivreurs(L);
        }
    }

    @FXML
    private void nom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < id_nom.getText().trim().length(); i++) {
            char ch = id_nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_nom.getText().trim().length() >= 3) {

            erruernom.setText("Nom valide");

            verificationnom = true;
        } else {

            erruernom.setText("Il faut au moins 3 caracteres");
            verificationnom = false;

        }
    }

    @FXML
    private void tel(KeyEvent event) {
        if (id_telephone.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < id_telephone.getText().trim().length(); i++) {
                char ch = id_telephone.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(id_telephone.getText())) {
                erreurtel.setText("Tel valide");

                verificationPhone = true;
            } else {
                erreurtel.setText("Tel non valide");
                verificationPhone = false;

            }

        } else {
            erreurtel.setText("Il faut 8 chiffres");
            verificationPhone = false;
        }

    }

    @FXML
    private boolean email(KeyEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (id_email.getText() == null) {
            return false;
        }

        if (pat.matcher(id_email.getText()).matches() == false) {
            verificationEmail = false;
            erreuremail.setText("Veuillez verifier la forme **@*.**");
            return false;
//            

        } else {

            erreuremail.setText("Mail valide");
            verificationEmail = true;
        }
        return true;

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
           // el page eli nheb nemchliha afficherparticipant comme example
            Parent root=FXMLLoader.load(getClass().getResource("AfficherLivreurs.fxml"));
            
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Livreurs");
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
    private void PageCommande(ActionEvent event) {
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
