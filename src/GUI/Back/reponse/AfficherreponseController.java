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

import foodapp.FoodApp;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DH Farouk
 */
public class AfficherreponseController implements Initializable {

    @FXML
    private ListView<Reponse> id_list;
    @FXML
    private Button id_modifier;
    @FXML
    private TextField id_commentaire;
    @FXML
    private Button id_supprimer;
    @FXML
    private Button id_ajout;
    ObservableList<Reponse> Reponse = FXCollections.observableArrayList();
    @FXML
    private Button Commande;
    @FXML
    private Button Livreur;
    @FXML
    private Button Reclamation;
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
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            afficher();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO
    }

    public void afficher() throws SQLException {
        ReponseService sr = new ReponseService();

        Reponse = FXCollections.observableArrayList(sr.afficher());
        id_list.setItems(Reponse);
    }

    @FXML
    private void fill(MouseEvent event)
            throws ParseException {

        Reponse p = id_list.getSelectionModel().getSelectedItem();

        id_commentaire.setText(p.getCommentaire());

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Reponse r = new Reponse();
        ReponseService sr = new ReponseService();

        r.setCommentaire(id_commentaire.getText());

        r.setId(id_list.getSelectionModel().getSelectedItem().getId());
        sr.Modifier(r);

        afficher();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        {
            ReponseService sr = new ReponseService();
            Reponse p = new Reponse();
            p.setId(id_list.getSelectionModel().getSelectedItem().getId());
            sr.supprimer(p);

            afficher();
        }

    }

    @FXML
    private void go_ajout(ActionEvent event) {
        try {

            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("ajouterreponse.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
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
    private void PageCommande(ActionEvent event) throws IOException {
        Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Back/Commande/AfficherCommande.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Achat");
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    private void PageReclamation(ActionEvent event)
            throws IOException {
         Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Achat");
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


   

}
