/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Jobs;

import Service.ServiceFournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class JobsController implements Initializable {

    ServiceFournisseur F = new ServiceFournisseur();
    @FXML
    private Label text;
    @FXML
    private TextField Nom;
    @FXML
    private Button addbtn;
    @FXML
    private TextField Prénom;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Email;
    @FXML
    private Label text1;
    @FXML
    private Label erreurmail;
    private boolean verificationmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addbtn.setDisable(true);
        //controle de saisie mail
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Nom.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prénom.getText().length() == 0 || Email.getText().length() == 0 || Description.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        Prénom.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prénom.getText().length() == 0 || Email.getText().length() == 0 || Description.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        Email.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(Email.getText());
            if (matcher.matches() == false || Nom.getText().length() == 0 || Prénom.getText().length() == 0 || Email.getText().length() == 0 || Description.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        Description.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prénom.getText().length() == 0 || Email.getText().length() == 0 || Description.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });

    }

    @FXML
    private void envoyer(ActionEvent event) throws IOException {
        if( verificationmail )
    {

        F.envoyerMail(Nom.getText(), Prénom.getText(), Email.getText(), Description.getText());

    }
        }

   

    @FXML
        private boolean testmail(KeyEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (Email.getText() == null) {
            return false;
        }

        if (pat.matcher(Email.getText()).matches() == false) {
            verificationmail = false;
            
            erreurmail.setText("Veuillez verifier la forme **@*.**");
            return false;
//            

        } else {
            
             erreurmail.setText("Mail valide");
             verificationmail = true;
        }
        return true;
    }
         @FXML
    private void Produit(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Backoffice(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Product/Product.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/reclamation/ajoutreclamation.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    }


