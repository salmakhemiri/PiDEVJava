/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.User;

import Entites.Users;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListePersonnelController implements Initializable {

    UserService us = new UserService();

    ObservableList<Users> oblist = FXCollections.observableArrayList();
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Users> view;
    @FXML
    private TableColumn<Users, String> cl_name;
    @FXML
    private TableColumn<Users, String> cl_email;
    @FXML
    private TableColumn<Users, String> cl_prenom;
    @FXML
    private TableColumn<Users, Integer> cl_numTel;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndel;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Email;
    @FXML
    private TextField NumTel;
    @FXML
    private PasswordField Password;
    @FXML
    private Label erreurmail;

    private boolean verificationmail;
    @FXML
    private Button Logout;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private Button Livreur;
    @FXML
    private Button Commande;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addbtn.setDisable(true);
        init();
        view.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_email.setCellFactory(TextFieldTableCell.forTableColumn());
//     cl_numTel.setCellFactory(TextFieldTableCell.forTableColumn());

        Nom.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prenom.getText().length() == 0 || Email.getText().length() == 0 || NumTel.getText().length() == 0 || Password.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        Prenom.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prenom.getText().length() == 0 || Email.getText().length() == 0 || NumTel.getText().length() == 0 || Password.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        Email.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prenom.getText().length() == 0 || Email.getText().length() == 0 || NumTel.getText().length() == 0 || Password.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        NumTel.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prenom.getText().length() == 0 || Email.getText().length() == 0 || NumTel.getText().length() != 8 || Password.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }
        });
        Password.textProperty().addListener((ov, oldValue, newValue) -> {
            if (Nom.getText().length() == 0 || Prenom.getText().length() == 0 || Email.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });

    }

    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{8}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

    }

    public void init() {

        try {
            oblist = (ObservableList<Users>) us.afficherPersonnel();

            cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            cl_numTel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            view.setItems(oblist);

        } catch (SQLException ex) {

        }
    }

    @FXML
    public void ajoutPersonnel(ActionEvent event) throws IOException {

        if (validatePhoneNumber(NumTel.getText()) && verificationmail) {

            Users f = new Users(Email.getText(), Nom.getText(), Prenom.getText(), Password.getText(), "ROLE_PERSONNEL", Integer.parseInt(NumTel.getText()));
            us.ajouter(f);
            JOptionPane.showMessageDialog(null, "Ajout de personnel effectué avec succés");
            init();
            clearAll();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("numero invalid");
            a.show();

        }

    }
    // vider les champs

    public void clearAll() {

        Nom.setText("");
        Prenom.setText("");
        Email.setText("");
        NumTel.setText("");
        Password.setText("");

    }

    @FXML
    public void UpdateNom(CellEditEvent edditedCell) throws SQLException {

        Users personSelected = (Users) view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String prenom = personSelected.getPrenom();
        int num = personSelected.getNum_tel();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        us.updatePersonnel(id, edditedCell.getNewValue().toString(), prenom, email, num);
    }

    @FXML
    public void UpdatePrenom(CellEditEvent edditedCell) throws SQLException {
        UserService us = new UserService();
        Users personSelected = (Users) view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String prenom = personSelected.getPrenom();
        int num = personSelected.getNum_tel();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        us.updatePersonnel(id, name, edditedCell.getNewValue().toString(), email, num);
    }

    @FXML
    public void UpdateEmail(CellEditEvent edditedCell) throws SQLException {
        UserService us = new UserService();
        Users personSelected = (Users) view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String prenom = personSelected.getPrenom();
        int num = personSelected.getNum_tel();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        us.updatePersonnel(id, name, prenom, edditedCell.getNewValue().toString(), num);
    }

    @FXML
    public void UpdateNum(CellEditEvent edditedCell) throws SQLException {
        UserService us = new UserService();
        Users personSelected = (Users) view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String prenom = personSelected.getPrenom();
        int num = personSelected.getNum_tel();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        us.updatePersonnel(id, name, prenom, email, (int) edditedCell.getNewValue());
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
    public void delete(ActionEvent event) throws SQLException {
        UserService us = new UserService();
        Users u = (Users) view.getSelectionModel().getSelectedItem();
        if (!u.equals(null)) {
            us.delete(u.getId());
        }
        init();
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

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/login.fxml")));
            stage.setScene(scene);
            stage.show();

        }

    }

   @FXML
    private void PageProduct(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Product/Product.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageStock(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Stock/Stock.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageFournisseur(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Fornisseurs/fournisseurs.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
    }

@FXML
    private void PageCategory(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Category/category.fxml")));
        stage.setScene(scene);
        stage.show();
    }

@FXML
    private void PageLivreur(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Livreur/AfficherLivreurs.fxml")));
        stage.setScene(scene);
        stage.show();
    }

 @FXML
    private void PageCommande(ActionEvent event)throws IOException {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Commande/AfficherCommande.fxml")));
        stage.setScene(scene);
        stage.show();
        
    }
@FXML
    private void PageReponse(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reponse/afficherreponse.fxml")));
        stage.setScene(scene);
        stage.show();
    }
@FXML
private void PageReclamation(ActionEvent event)
            throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml")));
        stage.setScene(scene);
        stage.show();
    }

 @FXML
    private void PageEquipe(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Equipe/InscriEquipe.fxml")));
        stage.setScene(scene);
        stage.show();
    }
  @FXML
    private void PageEquipement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Equipement/InscriEquippement.fxml")));
        stage.setScene(scene);
        stage.show();
    }


     @FXML
    private void PageClient(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/User/ListeClients.fxml")));
        stage.setScene(scene);
        stage.show();
    }

 

}
