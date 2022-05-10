/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Equipement;

import Entites.Equipe;
import Entites.Equippement;
import Service.EquipeService;
import Service.EquippementService;
import Utils.Mailapi;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
    @FXML
    private Button Logout;
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurtype;
    
    private boolean verificationnom;
    private boolean verificationtype;
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
     if ( verificationnom && verificationtype ) {      
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
    private void PagePersonnel(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/User/ListePersonnel.fxml")));
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

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/login.fxml")));
            stage.setScene(scene);
            stage.show();

        }

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
    private void testnom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < tfnom1.getText().trim().length(); i++) {
            char ch = tfnom1.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfnom1.getText().trim().length() >= 3) {

            erreurnom.setText("Nom valide");

            verificationnom = true;
        } else {

            erreurnom.setText("Il faut au moins 3 caracteres");
            verificationnom = false;

        }
    }

    @FXML
    private void testtype(KeyEvent event) {
         int nbNonChar = 0;
        for (int i = 1; i < tfmetier1.getText().trim().length(); i++) {
            char ch = tfmetier1.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfmetier1.getText().trim().length() >= 4) {

            erreurtype.setText("Type valide");

            verificationtype = true;
        } else {

            erreurtype.setText("Il faut au moins 4 caracteres");
            verificationtype = false;

        }
    }

   
}
