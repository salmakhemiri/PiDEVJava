/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.GUI;


import mercato.DAO.Classe.ReclamationService;
import mercato.Model.Reclamation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mercato.Model.DesktopNotification;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Admin_ReclamationController implements Initializable {

    @FXML
    private ListView<Reclamation> ListViewReclamation;
    @FXML
    private Pane dropDownMenu;
    @FXML
    private Text varUsernameToolBar;
    @FXML
    private ImageView varPhotoProfilToolBar;
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
    
    private ObservableList<Reclamation> data;
    private List<Reclamation> reclamations = new ArrayList<>();
    public static Reclamation adminReclamation;
    ReclamationService rs = new ReclamationService();
    Reclamation r = new Reclamation();
    

    /**
     * Initializes the controller class.
     */
    public void afficherMesReclamations() {
        ListViewReclamation.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {
            @Override
            public ListCell<Reclamation> call(ListView<Reclamation> Reclamation) {
                ListCell<Reclamation> cell = new ListCell<Reclamation>() {
                    @Override
                    protected void updateItem(Reclamation r, boolean bln) {
                        super.updateItem(r, bln);
                        if (r != null) {
                            VBox vb = new VBox(10);
                            HBox hb = new HBox(20);
                            hb.setStyle("-fx-border-color: #C8C8C8 ;");
                            vb.setAlignment(Pos.CENTER);
                            VBox vb2 = new VBox(10);
                            vb2.setAlignment(Pos.BASELINE_LEFT);
                            //////////////////////////////////////////////////////////////
                            Button btnResolue = new Button();
                            btnResolue.setId("btnReclamationRes" + r.getRecId());
                            btnResolue.setText("Résolue");
                            btnResolue.getStyleClass().add("buttonLogin");
                            btnResolue.getStyleClass().add("buttonLogin:hover");
                            btnResolue.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    try {
                                        r.setEtatProbleme("Résolue");
                                        rs.Modifier(r);
                                        DesktopNotification.notification("Reclamation Resolue", "La reclamation a ete resolue avec succes", NotificationType.SUCCESS);                                        
                                    } catch (Exception ex) {
                                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //////////////////////////////////////////////////////////////
                            Button btnencours = new Button();
                            btnencours.setId("btnEncours" + r.getRecId());
                            btnencours.setText("traitement en cours");
                            Image imageencours = new Image("mercato/images/clock.png");
                            ImageView encours = new ImageView(imageencours);
                            encours.setFitHeight(20);
                            encours.setFitWidth(20);
                            btnencours.setGraphic(encours);
                            btnencours.getStyleClass().add("buttonLogin");
                            btnencours.getStyleClass().add("buttonLogin:hover");
                            btnencours.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {

                                    try {
                                        r.setEtatProbleme("En cours");
                                        rs.Modifier(r);
                                        DesktopNotification.notification("Reclamation En Cours", "La reclamation a ete mise en cours de resolution avec succes", NotificationType.SUCCESS);
                                    } catch (Exception ex) {
                                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            ////////////////////////////////////////////////////////:////:/
                            Label invisible = new Label();
                            invisible.setVisible(false);
                            Label titre = new Label(r.getTitle().toUpperCase());
                            Label description = new Label("Description : " + r.getBody());
                            Label dateDepotText = new Label("Date de depot :" + String.valueOf(r.getDate()));
                            Label type = new Label("Type de reclamation :" + r.getSubject());
                            Label etat = new Label("Etat de la reclamation : " + r.getEtatProbleme());
                            titre.setTextFill(Paint.valueOf("#80b201"));
                            vb.getChildren().addAll(titre);
                            vb2.getChildren().addAll(description, dateDepotText, type, etat, btnResolue, btnencours, invisible);
                            hb.getChildren().addAll(vb, vb2);
                            setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//******************************************************btn list all***********************************************************

        reclamations = rs.Afficher();
        data = FXCollections.observableArrayList();
        data.addAll(reclamations);
        ListViewReclamation.setItems(data);
        afficherMesReclamations();




    }


    @FXML
    private void searchProbleme(MouseEvent event) {
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
        if (searchText.getText().isEmpty()) {
                    searchText.setStyle("-fx-text-box-border: #f44336 ;");

                    
                    reclamations = rs.Afficher();
                    data = FXCollections.observableArrayList();
                    data.addAll(reclamations);
                    ListViewReclamation.setItems(data);
                    afficherMesReclamations();
                } else {

                    reclamations = rs.findByType(searchText.getText().toLowerCase().trim());
                    data = FXCollections.observableArrayList();
                    data.addAll(reclamations);
                    ListViewReclamation.setItems(data);
                    afficherMesReclamations();
                    if (reclamations.isEmpty()) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Pas de Resultat");
                        a.show();
                        reclamations = rs.Afficher();
                        data = FXCollections.observableArrayList();
                        data.addAll(reclamations);
                        ListViewReclamation.setItems(data);
                        afficherMesReclamations();
                    } else {
                        reclamations = rs.findByType(searchText.getText().toLowerCase().trim());
                        data = FXCollections.observableArrayList();
                        RechercherMenuButton.setText("typeReclamation");
                        data.addAll(reclamations);
                        ListViewReclamation.setItems(data);
                        afficherMesReclamations();
                    }
                }
    }

    @FXML
    private void AfficherUser(ActionEvent event) {
       if (searchText.getText().isEmpty()) {
            searchText.setStyle("-fx-text-box-border: #f44336 ;");
            reclamations = rs.Afficher();
            data = FXCollections.observableArrayList();
            data.addAll(reclamations);
            ListViewReclamation.setItems(data);
            afficherMesReclamations();
        } else {

            if (reclamations.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Pas de Resultat");
                a.show();
                reclamations = rs.Afficher();
                data = FXCollections.observableArrayList();
                data.addAll(reclamations);
                ListViewReclamation.setItems(data);
                afficherMesReclamations();

            } else {
                data = FXCollections.observableArrayList();
                reclamations = rs.getByUserId(1);
                ListViewReclamation.setItems(data);
                RechercherMenuButton.setText("User");
                data.addAll(reclamations);
            }
        }
    }

    @FXML
    private void navListAction(MouseEvent event) {
        //********************************************************btn list resolue***************************************************
        navList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reclamations = rs.getByEtat(r, "Résolue");
                data = FXCollections.observableArrayList();
                data.addAll(reclamations);
                ListViewReclamation.setItems(data);
                afficherMesReclamations();
            }
        });
    }

    @FXML
    private void navList1Action(MouseEvent event) {
        //******************************************************btn list encours***********************************************************
        navList1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reclamations = rs.getByEtat(r, "En cours");
                data = FXCollections.observableArrayList();
                data.addAll(reclamations);
                ListViewReclamation.setItems(data);
                afficherMesReclamations();
            }
        });
    }

    @FXML
    private void navList2Action(MouseEvent event) {
        //******************************************************btn list all***********************************************************
        navList2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reclamations = rs.Afficher();
                data = FXCollections.observableArrayList();
                data.addAll(reclamations);
                ListViewReclamation.setItems(data);
                afficherMesReclamations();
            }
        });
    }

}
