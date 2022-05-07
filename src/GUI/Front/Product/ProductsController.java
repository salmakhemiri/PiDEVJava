/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Product;

import Entites.Product;
import Service.ServiceProduct;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class ProductsController implements Initializable {

    @FXML
    private Label text;
    @FXML
    private VBox liste_formation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DisplayOne();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DisplayOne() throws SQLException, IOException {
        ServiceProduct ServP = new ServiceProduct();
        ArrayList<Product> TabP = ServP.DisplayAll();

        for (Product f : TabP) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ProductEX.fxml"));
            Parent n = (Parent) loader.load();
            GUI.Front.Product.ProductEXController form = loader.getController();
            form.InitProduct(f);
            liste_formation.getChildren().add(n);
        }
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
