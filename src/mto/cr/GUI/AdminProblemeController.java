/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mto.cr.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminProblemeController implements Initializable {

    @FXML
    private ListView<?> listReclamation;
    @FXML
    private Pane dropDownMenu;
    @FXML
    private Text varUsernameToolBar;
    @FXML
    private ImageView varPhotoProfilToolBar;
    @FXML
    private Text notificationNumber;
    @FXML
    private ImageView home;
    @FXML
    private AnchorPane navList;
    @FXML
    private Pane menu;
    @FXML
    private Text AccederResolus;
    @FXML
    private AnchorPane navList1;
    @FXML
    private Pane menu1;
    @FXML
    private Text AccederNonResolus;
    @FXML
    private AnchorPane navList2;
    @FXML
    private Pane menu2;
    @FXML
    private Text AccederTous;
    @FXML
    private TextField searchText;
    @FXML
    private Pane searchButton;
    @FXML
    private MenuButton RechercherMenuButton;
    @FXML
    private MenuItem ReclamationMenuItem;
    @FXML
    private MenuItem UserMenuItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnterProfileAc(MouseEvent event) {
    }

    @FXML
    private void searchProbleme(MouseEvent event) {
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
    }

    @FXML
    private void AfficherUser(ActionEvent event) {
    }
    
}
