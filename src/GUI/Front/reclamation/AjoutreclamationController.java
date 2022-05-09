/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.reclamation;

import Entites.Reclamation;
import Service.ReclamationService;
import foodapp.FoodApp;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author DH Farouk
 */
public class AjoutreclamationController implements Initializable {

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_etat;
    @FXML
    private TextField id_description;
    @FXML
    private TextField id_email;
    @FXML
    private DatePicker id_date;
    @FXML
    private Button insert;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_description;
    @FXML
    private Label error_email;
    private boolean verificationnom;
    private boolean verificationdescription;
    private boolean verificationEmail;
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
    private void ajouter(ActionEvent event) throws SQLException {
        if (verificationEmail && verificationdescription && verificationnom) {
            ReclamationService rs = new ReclamationService();
            Reclamation r = new Reclamation();
            r.setNom(id_nom.getText());
            r.setEtat(Integer.parseInt(id_etat.getText()));
            r.setDescription(id_description.getText());
            r.setEmail(id_email.getText());
            Date datee = Date.valueOf(id_date.getValue());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(datee);
            r.setdate(strDate);
            rs.ajouterReclamation(r);
            JOptionPane.showMessageDialog(null, "Ajout effectué");
            
//            try {
//                Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stageclose.close();
//                // el page eli nheb nemchliha afficherparticipant comme example
//                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml"));
//
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.setTitle("reclamation");
//                stage.show();
//            } catch (IOException ex) {
//                Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
//            }

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

            error_nom.setText("Nom valider");

            verificationnom = true;
        } else {

            error_nom.setText("Il faut au moins 3 caracteres");
            verificationnom = false;

        }
    }

    @FXML
    private void description(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < id_description.getText().trim().length(); i++) {
            char ch = id_description.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_description.getText().trim().length() >= 10) {

            error_description.setText("description valide");

            verificationdescription = true;
        } else {

            error_description.setText("Il faut au moins 10 caracteres");
            verificationdescription = false;

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
            error_email.setText("Veuillez verifier la forme *@.**");
            return false;
//            

        } else {

            error_email.setText("Mail valide");
            verificationEmail = true;
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
    private void OffrePage(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Jobs/Jobs.fxml")));
        stage.setScene(scene);
        stage.show();
    }

  

   
    @FXML
    private void Logout(ActionEvent event) throws IOException {
        // ajouter message de confirmation 

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Voulez-vous vraiment vous deconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/Inscription.fxml")));
            stage.setScene(scene);
            stage.show();

        }

    }

}
