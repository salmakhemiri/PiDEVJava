/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import mercato.DAO.Classe.ReclamationService;
import mercato.Model.DateConverter;
import mercato.Model.DesktopNotification;
import mercato.Model.Reclamation;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane GlobalAnchorID;
    @FXML
    private TabPane tabToutsLesProjet;
    @FXML
    private Tab TabReclamationAjout;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private SplitMenuButton typeReclamation;
    @FXML
    private Button buttonAddReclam;
    @FXML
    private Tab TabMesReclamation;
    @FXML
    private ListView<Reclamation> ListViewMesReclamations;
    @FXML
    private Pane MonProfilMenu;
    @FXML
    private Button buttonAccederMonProfil;
    @FXML
    private ImageView PhotoProfilMonProfil;
    @FXML
    private Text nomPrenomMonProfil;
    @FXML
    private Text emailMonProfil;
    @FXML
    private Text soldeMonProfil;
    @FXML
    private Pane notifmenu;
    @FXML
    private Text notif1;
    @FXML
    private Text notif2;
    @FXML
    private Text notif4;
    @FXML
    private Text notif3;
    @FXML
    private Button bnotif1;
    @FXML
    private Button bnotif2;
    @FXML
    private Button bnotif3;
    @FXML
    private Button bnotif4;
    @FXML
    private Button buttonNotification;
    @FXML
    private Pane viewmenu;
    @FXML
    private Text notif11;
    @FXML
    private Text notif21;
    @FXML
    private Text notif41;
    @FXML
    private Text notif31;
    @FXML
    private Button bnotif11;
    @FXML
    private Button bnotif21;
    @FXML
    private Button bnotif31;
    @FXML
    private Button bnotif41;
    @FXML
    private Button buttonNotification1;
    @FXML
    private Pane dropDownMenu;
    @FXML
    private Text varUsernameToolbar;
    @FXML
    private ImageView varPhotoProfilToolbar;
    @FXML
    private ImageView home;
    @FXML
    private ImageView notif;
    @FXML
    private ImageView viewnotif;
    @FXML
    private AnchorPane navList;
    @FXML
    private Pane annoncep;
    @FXML
    private AnchorPane navList1;
    @FXML
    private Pane menu1;
    @FXML
    private AnchorPane navList2;
    @FXML
    private Pane menu2;
    private ObservableList<Reclamation> data;
    public static int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Reclamation> ls = new ArrayList();
        ReclamationService rs = new ReclamationService();

        Reclamation r = new Reclamation();
        ls = rs.getByUserId(1);
        data = FXCollections.observableArrayList();
        data.addAll(ls);
        ListViewMesReclamations.setItems(data);
        ListViewMesReclamations.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {

            @Override
            public ListCell<Reclamation> call(ListView<Reclamation> ListViewReclamation) {
                ListCell<Reclamation> cell = new ListCell<Reclamation>() {
                    protected void updateItem(Reclamation r, boolean bln) {
                        super.updateItem(r, bln);
                        if (r != null) {
                            VBox vb = new VBox(10);
                            HBox hb = new HBox(20);
                            hb.setStyle("-fx-border-color: #C8C8C8 ;");
                            vb.setAlignment(Pos.CENTER);
                            VBox vb2 = new VBox(10);
                            vb.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.BASELINE_LEFT);
                            //////////////////////////////////////////////////////////////
                            Button btnModifier = new Button();
                            btnModifier.setId("btnReclamation" + r.getRecId());
                            btnModifier.setText("Modifier ma reclamation");
                            Image imageAccesProduct = new Image("mercato/images/edit.png");
                            ImageView enterIV = new ImageView(imageAccesProduct);
                            enterIV.setFitHeight(20);
                            enterIV.setFitWidth(20);
                            btnModifier.setGraphic(enterIV);
                            btnModifier.getStyleClass().add("buttonLogin");
                            btnModifier.getStyleClass().add("buttonLogin:hover");
                            btnModifier.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent e) {
                                    try {
                                        id = r.getRecId();
                                        //String id = String.valueOf(r.getRecId());
                                        Stage stage = new Stage();
                                        Stage stageprev = (Stage) btnModifier.getScene().getWindow();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier_Reclamation.fxml"));
                                        Parent parent = loader.load();
                                        Scene scene = new Scene(parent);
                                        stage.setScene(scene);
                                        stage.setResizable(false);
                                        stage.show();
                                        stageprev.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Modifier_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //////////////////////////////////////////////////////////////
                            Button btnSupprimer = new Button();
                            btnSupprimer.setId("btnSupprimer" + r.getRecId());
                            btnSupprimer.setText("supprimer ma reclamation");
                            Image imageSuppProduct = new Image("mercato/images/delete.png");
                            ImageView supp = new ImageView(imageSuppProduct);
                            supp.setFitHeight(20);
                            supp.setFitWidth(20);
                            btnSupprimer.setGraphic(supp);
                            btnSupprimer.getStyleClass().add("buttonLogin");
                            btnSupprimer.getStyleClass().add("buttonLogin:hover");
                            btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent e) {

                                    try {
                                        ReclamationService rs = new ReclamationService();
                                        rs.Supprimer(r);
                                        DesktopNotification.notification("Reclamation Supprimée", "votre reclamation a ete supprimer avec succes", NotificationType.SUCCESS);
                                    } catch (Exception ex) {
                                        Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //////////////////////////////////////////////////////////////
                            Label invisible = new Label();
                            invisible.setVisible(false);
                            Label titre = new Label(r.getTitle().toUpperCase());
                            Label description = new Label("Description : " + r.getBody());
                            Label dateDepotText = new Label("Date de depot :" + String.valueOf(r.getDate()));
                            Label type = new Label("Type de reclamation :" + r.getSubject().toString());
                            Label etat = new Label("Etat de la reclamation : " + r.getEtatProbleme());
                            titre.setTextFill(Paint.valueOf("#80b201"));
                            vb.getChildren().addAll(titre);
                            vb2.getChildren().addAll(description, dateDepotText, type, etat, btnModifier, btnSupprimer, invisible);
                            hb.getChildren().addAll(vb, vb2);
                            setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        });

        TranslateTransition openNavi = new TranslateTransition(new Duration(450), MonProfilMenu);
        TranslateTransition closeNavi = new TranslateTransition(new Duration(450), MonProfilMenu);

        dropDownMenu.setOnMouseClicked((MouseEvent event60) -> {
            openNavi.setToY(MonProfilMenu.getHeight());
            openNavi.play();
        });

        MonProfilMenu.setOnMouseExited((MouseEvent event60) -> {

            closeNavi.setToY(-(MonProfilMenu.getHeight()));
            closeNavi.play();

        });
        dropDownMenu.setOnMouseEntered((MouseEvent event4) -> {
            TranslateTransition transTransition = TranslateTransitionBuilder.create()
                    .duration(new Duration(140))
                    .node(dropDownMenu)
                    .toY(-3)
                    .interpolator(Interpolator.LINEAR)
                    .cycleCount(1).
                    onFinished(e -> {
                        TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                .duration(new Duration(140))
                                .node(dropDownMenu)
                                .toY(3)
                                .interpolator(Interpolator.LINEAR)
                                .cycleCount(1)
                                .build();
                        transTransition2.play();
                    })
                    .build();
            transTransition.play();
        });

        home.setOnMouseEntered((MouseEvent event4) -> {
            TranslateTransition transTransition = TranslateTransitionBuilder.create()
                    .duration(new Duration(140))
                    .node(home)
                    .toY(-3)
                    .interpolator(Interpolator.LINEAR)
                    .cycleCount(1).
                    onFinished(e -> {
                        TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                .duration(new Duration(140))
                                .node(home)
                                .toY(3)
                                .interpolator(Interpolator.LINEAR)
                                .cycleCount(1)
                                .build();
                        transTransition2.play();
                    })
                    .build();
            transTransition.play();
        });
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {

                    Stage stage = new Stage();
                    Stage stageprev = (Stage) home.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilUser.fxml"));
                    Parent parent = loader.load();
                    Stage stagep = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    stageprev.close();
                } catch (IOException ex) {
                    Logger.getLogger("AcceuilUser.java").log(Level.SEVERE, null, ex);
                }
            }
        });
        TranslateTransition openNotif;
        openNotif = new TranslateTransition(new Duration(450), notifmenu);
        TranslateTransition closeNotif;
        closeNotif = new TranslateTransition(new Duration(450), notifmenu);

        notif.setOnMouseClicked((MouseEvent event61) -> {
            openNotif.setToY(notifmenu.getHeight());
            openNotif.play();
        });

        notifmenu.setOnMouseExited((MouseEvent event61) -> {

            closeNotif.setToY(-(notifmenu.getHeight()));
            closeNotif.play();

        });
        notif.setOnMouseEntered((MouseEvent event5) -> {
            TranslateTransition transTransition = TranslateTransitionBuilder.create()
                    .duration(new Duration(140))
                    .node(notif)
                    .toY(-3)
                    .interpolator(Interpolator.LINEAR)
                    .cycleCount(1).
                    onFinished(e -> {
                        TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                .duration(new Duration(140))
                                .node(notif)
                                .toY(3)
                                .interpolator(Interpolator.LINEAR)
                                .cycleCount(1)
                                .build();
                        transTransition2.play();
                    })
                    .build();
            transTransition.play();
        });

        notif.setOnMouseEntered((MouseEvent event4) -> {
            TranslateTransition transTransition = TranslateTransitionBuilder.create()
                    .duration(new Duration(140))
                    .node(notif)
                    .toY(-3)
                    .interpolator(Interpolator.LINEAR)
                    .cycleCount(1).
                    onFinished(e -> {
                        TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                .duration(new Duration(140))
                                .node(notif)
                                .toY(3)
                                .interpolator(Interpolator.LINEAR)
                                .cycleCount(1)
                                .build();
                        transTransition2.play();
                    })
                    .build();
            transTransition.play();
        });

        TranslateTransition openview;
        openview = new TranslateTransition(new Duration(450), viewmenu);
        TranslateTransition closeview;
        closeview = new TranslateTransition(new Duration(450), viewmenu);

        viewnotif.setOnMouseClicked((MouseEvent event61) -> {
            openview.setToY(viewmenu.getHeight());
            openview.play();
        });

        viewmenu.setOnMouseExited((MouseEvent event61) -> {

            closeview.setToY(-(viewmenu.getHeight()));
            closeview.play();

        });
        viewnotif.setOnMouseEntered((MouseEvent event5) -> {
            TranslateTransition transTransition = TranslateTransitionBuilder.create()
                    .duration(new Duration(140))
                    .node(viewnotif)
                    .toY(-3)
                    .interpolator(Interpolator.LINEAR)
                    .cycleCount(1).
                    onFinished(e -> {
                        TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                .duration(new Duration(140))
                                .node(viewnotif)
                                .toY(3)
                                .interpolator(Interpolator.LINEAR)
                                .cycleCount(1)
                                .build();
                        transTransition2.play();
                    })
                    .build();
            transTransition.play();
        });

        //init
        TranslateTransition openNav = new TranslateTransition(new Duration(350), navList);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), navList);
        TranslateTransition openNav1 = new TranslateTransition(new Duration(350), navList1);
        TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), navList1);
        TranslateTransition openNav2 = new TranslateTransition(new Duration(350), navList2);
        TranslateTransition closeNav2 = new TranslateTransition(new Duration(350), navList2);

        navList.setOnMouseEntered((MouseEvent event1) -> {

            openNav.setToX(-25);
            openNav.play();
        });

        navList.setOnMouseExited((MouseEvent event1) -> {

            closeNav.setToX(-(navList.getWidth()) + 43);
            closeNav.play();
        });

        navList1.setOnMouseEntered((MouseEvent event3) -> {

            openNav1.setToX(-25);
            openNav1.play();
        });

        navList1.setOnMouseExited((MouseEvent event3) -> {

            closeNav1.setToX(-(navList1.getWidth()) + 49);
            closeNav1.play();
        });

        navList2.setOnMouseEntered((MouseEvent event2) -> {

            openNav2.setToX(55);
            openNav2.play();
        });

        navList2.setOnMouseExited((MouseEvent event2) -> {

            closeNav2.setToX(-(navList2.getWidth()) + 128);
            closeNav2.play();
        });

        buttonAddReclam.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                 
                Reclamation r = new Reclamation();
                try {
                    r.setDate(DateConverter.dateConverter(new Date()));
                } catch (ParseException ex) {
                    Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                r.setBody(description.getText());
                r.setTitle(nom.getText());
               // r.setSubject(typeReclamation.getText());
                r.setSubject(String.valueOf(typeReclamation.getText()));
                r.setEtatProbleme("en attente");
                // r.setUserId(loggerid()); Pour l'utilisation de userid dans l'integration
                r.setUserId(1);
                ReclamationService rs = new ReclamationService();
                rs.Ajouter(r);

                DesktopNotification.notification("Reclamation Ajoutée", "votre reclamation a ete ajouter avec succes", NotificationType.SUCCESS);
            }
        });

    }

    @FXML
    private void menuConnectionAction(ActionEvent event) {
        typeReclamation.setText("Probleme de connection");
    }

    @FXML
    private void menuAnnoncesAction(ActionEvent event) {
        typeReclamation.setText("Probleme d'annonce");
    }

    @FXML
    private void menuRendezvousAction(ActionEvent event) {
        typeReclamation.setText("Probleme de rendez-vous");
    }

    @FXML
    private void menuLikesAction(ActionEvent event) {
        typeReclamation.setText("Probleme de like");
    }

    @FXML
    private void menuCommentairesAction(ActionEvent event) {
        typeReclamation.setText("Probleme de commentaire");
    }

    @FXML
    private void menuSponsorisationAction(ActionEvent event) {
        typeReclamation.setText("Probleme de sponsorisation");
    }

    @FXML
    private void AddReclamationAction(ActionEvent event) {
    }

    @FXML
    private void AjouterReclamationAction(Event event) {
    }

    @FXML
    private void MesReclamationAction(Event event) {
    }

    @FXML
    private void EnterProfileAc(ActionEvent event) {
    }

    @FXML
    private void EnterSoldeAc(ActionEvent event) {
    }

    @FXML
    private void EnterCommentairesAc(ActionEvent event) {
    }

    @FXML
    private void AccederProfilsAction(MouseEvent event) {
    }

    @FXML
    private void AccederForumAction(MouseEvent event) {
    }

}
