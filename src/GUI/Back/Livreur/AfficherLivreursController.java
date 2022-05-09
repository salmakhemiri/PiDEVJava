/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Livreur;

import Entites.Livreurs;
import GUI.Back.Commande.AfficherCommandeController;
import Service.LivreursService;
import foodapp.FoodApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.PieChart;
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
 * @author oubei
 */
public class AfficherLivreursController implements Initializable {

    @FXML
    private ListView<Livreurs> id_list;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_prenom;
    @FXML
    private TextField id_telephone;
    @FXML
    private TextField id_email;
    @FXML
    private Button id_ajouter;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_supprimer;

    ObservableList<Livreurs> Livreurs = FXCollections.observableArrayList();
    @FXML
    private PieChart pieChart;
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
        // TODO
        try {
            afficher();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherLivreursController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Livreur aicha", 700),
                        new PieChart.Data("Livreur salma", 700));

        pieChart.setData(pieChartData);
        pieChart.setTitle("Tout les Livreurs");

    }

    public void afficher() throws SQLException {
        LivreursService cs = new LivreursService();
        Livreurs = FXCollections.observableArrayList(cs.afficher());
        id_list.setItems(Livreurs);
    }

    @FXML
    private void fill(MouseEvent event) {

        Livreurs l = id_list.getSelectionModel().getSelectedItem();
        id_nom.setText(String.valueOf(l.getNom()));
        id_prenom.setText(String.valueOf(l.getPrenom()));
        id_telephone.setText(String.valueOf(l.getTel()));
        id_email.setText(String.valueOf(l.getEmail()));
    }

    @FXML
    private void go_ajouter(ActionEvent event) {
        try {

            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("AjouterLivreurs.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_modifier(ActionEvent event)
            throws SQLException {
        {
            try {
                Livreurs L = new Livreurs();
                LivreursService cs = new LivreursService();

                L.setNom(id_nom.getText());
                L.setPrenom(id_prenom.getText());
                L.setTel(Integer.parseInt(id_telephone.getText()));
                L.setEmail(id_email.getText());
                L.setId(id_list.getSelectionModel().getSelectedItem().getId());
                cs.Modifier(L);

                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void go_supprimer(ActionEvent event) throws SQLException {
        LivreursService cs = new LivreursService();
        Livreurs l = new Livreurs();
        l.setId(id_list.getSelectionModel().getSelectedItem().getId());
        cs.supprimer(l);
        afficher();
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
    private void PageCommande(ActionEvent event) throws IOException {
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
