/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Fournisseurs;

import Entities.Fournisseurs;
import Service.ServiceFournisseur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Salma
 */
public class FournisseursController implements Initializable {

    ServiceFournisseur Four = new ServiceFournisseur();

    ObservableList<Fournisseurs> oblist = FXCollections.observableArrayList();
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Fournisseurs> view;
    @FXML
    private TableColumn<Fournisseurs, String> cl_name;
    @FXML
    private TableColumn<Fournisseurs, String> cl_num;
    @FXML
    private TableColumn<Fournisseurs, String> cl_desig;
    @FXML
    private TableColumn<Fournisseurs, String> cl_email;
    @FXML
    private TextField FourName;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndel;
    @FXML
    private TextField FourNum;
    @FXML
    private TextField FourEmail;
    @FXML
    private TextField fourDes;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addbtn.setDisable(true);
        init();
        view.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desig.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_num.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_email.setCellFactory(TextFieldTableCell.forTableColumn());
//controle de saisie mail
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        FourName.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("nom invalide");
            a.show();
                
                addbtn.setDisable(false);
            }

        });

        FourNum.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() != 8 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });

        FourEmail.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        fourDes.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
    }
private static boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{8}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
		
	}
    public void init() {
        try {
            oblist = (ObservableList<Fournisseurs>) Four.afficheFournisseur();

            cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cl_num.setCellValueFactory(new PropertyValueFactory<>("numero"));
            cl_desig.setCellValueFactory(new PropertyValueFactory<>("designation"));
            cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            view.setItems(oblist);

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void ajoutFour(ActionEvent event) throws IOException {

        try {
            Fournisseurs f = new Fournisseurs(0, FourName.getText(), FourNum.getText(), fourDes.getText(), 0, FourEmail.getText());
if (validatePhoneNumber (FourNum.getText()))
 {
            Four.ajouter(f);
            init();
            clearAll();}
 else{
           Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("numero invalid");
            a.show();  
        }
        } catch (SQLException ex) {
            Logger.getLogger(FournisseursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearAll() {

        FourName.setText("");
        FourNum.setText("");
        FourEmail.setText("");
        fourDes.setText("");

    }

    // updates 
    @FXML
    private void UpdateName(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, edditedCell.getNewValue().toString(), num, designation, email);
    }

    @FXML
    private void UpdateNum(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = edditedCell.getNewValue().toString();

        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, designation, email);
    }

    @FXML
    private void UpdateDesignation(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, edditedCell.getNewValue().toString(), email);
    }

    @FXML
    private void UpdateEmail(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, designation, edditedCell.getNewValue().toString());
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Fournisseurs f = view.getSelectionModel().getSelectedItem();
        if (!f.equals(null)) {
            Four.delete(f.getId());
        }
        init();
    }
    // changement de page

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
    private void Front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Jobs/Jobs.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
