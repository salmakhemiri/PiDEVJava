/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.Product;

import Entities.Category;
import Entities.Product;
import Service.ServiceProduct;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class ProductEXController implements Initializable {

    public ServiceProduct serviceP = new ServiceProduct();
    @FXML
    private Label nom;
    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    @FXML
    private Text description;
    @FXML
    private Label remise;
    @FXML
    private ImageView image;
    @FXML
    private Label category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void InitProduct(Product P) throws SQLException {

        Product p = serviceP.details(P.getId());
        Category C = serviceP.detailsCategory(P.getCategory_id());

        nom.setText("" + p.getName());
        prix.setText("" + p.getPrice());
        description.setText("" + p.getDescription());
        quantite.setText("" + p.getQuantite());
        remise.setText("" + p.getDiscount());
        image.setImage(new Image(getClass().getResourceAsStream("/GUI/Front/images/" + P.getImage())));
        category.setText(C.getName() + " : " + C.getText());

    }

}
