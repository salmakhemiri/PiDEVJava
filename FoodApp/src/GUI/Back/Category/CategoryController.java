/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Category;

import Entities.Category;
import Service.ServiceCategory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aicha
 */
public class CategoryController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Category> Catview;

    ServiceCategory cat = new ServiceCategory();
    ObservableList<Category> oblist = FXCollections.observableArrayList();
    private TextField catt;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndel;
    private TextField upcat;
    @FXML
    private TextField CatName;
    private TextField UpdateName;
    @FXML
    private TextField catColor;
    @FXML
    private TextArea CatDesc;
    private TextField UpdColor;
    private TextField UpdDescription;
    @FXML
    private TableColumn<Category, String> cl_name;
    @FXML
    private TableColumn<Category, String> cl_desc;
    @FXML
    private TableColumn<Category, String> cl_color;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addbtn.setDisable(true);
        init();
        //modifier double clique
        Catview.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_color.setCellFactory(TextFieldTableCell.forTableColumn());

        CatName.textProperty().addListener((ov, oldValue, newValue) -> {
            if (CatName.getText().length() == 0 || catColor.getText().length() == 0 || CatDesc.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        catColor.textProperty().addListener((ov, oldValue, newValue) -> {
            if (CatName.getText().length() == 0 || catColor.getText().length() == 0 || CatDesc.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        CatDesc.textProperty().addListener((ov, oldValue, newValue) -> {
            if (CatName.getText().length() == 0 || catColor.getText().length() == 0 || CatDesc.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
    }

    public void init() {
        try {
            oblist = (ObservableList<Category>) cat.affichecat();
            cl_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            cl_color.setCellValueFactory(new PropertyValueFactory<>("color"));
            cl_desc.setCellValueFactory(new PropertyValueFactory<>("text"));
            Catview.setItems(oblist);

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void ajoutcat(ActionEvent event) throws IOException {

        try {
            Category u = new Category(CatName.getText(), catColor.getText(), CatDesc.getText());
            cat.ajouter(u);
            init();
            clearAll();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // vider les champs
    private void clearAll() {

        CatName.setText("");
        catColor.setText("");
        CatDesc.setText("");

    }

    // Updates ( double click sur le tableau ) 
    @FXML
    public void UpdateName(CellEditEvent edditedCell) throws SQLException {
        Category personSelected = Catview.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        String color = personSelected.getColor();
        String description = personSelected.getText();
        personSelected.setName(edditedCell.getNewValue().toString());

        cat.update(name, edditedCell.getNewValue().toString(), color, description);
    }

    @FXML
    public void UpdateColor(CellEditEvent edditedCell) throws SQLException {
        Category personSelected = Catview.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        String color = personSelected.getColor();
        String description = personSelected.getText();
        personSelected.setColor(edditedCell.getNewValue().toString());

        cat.update(name, name, edditedCell.getNewValue().toString(), description);
    }

    @FXML
    public void UpdateDescription(CellEditEvent edditedCell) throws SQLException {
        Category personSelected = Catview.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        String color = personSelected.getColor();
        String description = personSelected.getText();
        personSelected.setText(edditedCell.getNewValue().toString());

        cat.update(name, name, color, edditedCell.getNewValue().toString());
    }

// Fin updates 
    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Category c = Catview.getSelectionModel().getSelectedItem();
        if (!c.equals(null)) {
            System.out.println("GET =" + c);
        }
        cat.delete(c.getId(), c.getName());
        init();
    }

    // changement de page
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
    private void Front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
