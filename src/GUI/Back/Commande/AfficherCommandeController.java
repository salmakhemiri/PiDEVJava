/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Commande;

import Entites.Commande;
import Service.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import foodapp.FoodApp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author oubei
 */
public class AfficherCommandeController implements Initializable {

    @FXML
    private ListView<Commande> id_list;
    @FXML
    private Button id_ajouter;
    @FXML
    private Button id_modifer;
    @FXML
    private Button id_supprimer;
    @FXML
    private TextField id_montant;
    @FXML
    private TextField id_etat;
    @FXML
    private DatePicker id_date;

    ObservableList<Commande> commande = FXCollections.observableArrayList();
    @FXML
    private Button id_qr;
    @FXML
    private Button id_Stats;
    @FXML
    private TextField rechercher;
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
            Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficher() throws SQLException {
        CommandeService cs = new CommandeService();
        Commande c = new Commande();
        commande = FXCollections.observableArrayList(cs.afficher());
        id_list.setItems(commande);
    }

    @FXML
    private void fill(MouseEvent event) throws ParseException {
        {

            Commande c = id_list.getSelectionModel().getSelectedItem();
            id_montant.setText(String.valueOf(c.getMontant()));
            id_etat.setText(String.valueOf(c.getEtat()));
            Date date1 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(c.getDate());
            java.sql.Date sqlDate = java.sql.Date.valueOf(date1.toString());
            LocalDate localDate2 = sqlDate.toLocalDate();
            id_date.setValue(localDate2);

        }

    }

    @FXML
    private void go_ajouter(ActionEvent event) {
        try {

            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("AjouterCommande.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event)
            throws SQLException {
        {
            try {
                Commande C = new Commande();
                CommandeService cs = new CommandeService();

                C.setEtat(Integer.parseInt(id_etat.getText()));
                C.setMontant(Integer.parseInt(id_montant.getText()));

                C.setId(id_list.getSelectionModel().getSelectedItem().getId());
                cs.Modifier(C);

                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        CommandeService cs = new CommandeService();
        Commande c = new Commande();
        c.setId(id_list.getSelectionModel().getSelectedItem().getId());
        cs.supprimer(c);
        afficher();

    }

    @FXML
    private void Create_QR(ActionEvent event) {
        try {
            String qrCodeData = "http://127.0.0.1:8000/commande/";
            String filePath = "D:\\QRCODE\\commande2.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix;
            matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, (Hashtable) hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void Stats(ActionEvent event) {
        try {

            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("StatsCommande.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("statistique");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FoodApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercher(KeyEvent event) {
        CommandeService sf = new CommandeService();
        ArrayList listcs = (ArrayList) sf.afficher();
        ObservableList OFormation = FXCollections.observableArrayList(listcs);
        FilteredList<Commande> filteredData = new FilteredList<>(OFormation, p -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getMontant()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getEtat()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getId_user()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Commande> sortedData = new SortedList<>(filteredData);
        id_list.setItems(sortedData);
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
    private void PageCategory(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Category/category.fxml")));
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
    private void PageLivreur(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Livreur/AfficherLivreurs.fxml")));
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


    @FXML
    private void Front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
    }


}
