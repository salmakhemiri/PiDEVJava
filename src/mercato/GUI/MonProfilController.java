/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import mercato.DAO.Classe.SponsorService;
import mercato.Model.User;

/**
 * FXML Controller class
 *
 * @author PC-Yassine
 */
public class MonProfilController implements Initializable {

    @FXML
    private AnchorPane GlobalAnchorID1;
    @FXML
    private ImageView photoProfil1;
    @FXML
    private Label varNomPrenom;
    @FXML
    private Label varDataNaissance;
    @FXML
    private Label varSituationProfessionnel;
    @FXML
    private Button buttonChangerPSW;
    @FXML
    private ImageView photoProfilPageExp11;
    @FXML
    private AnchorPane GlobalAnchorID;
    @FXML
    private ImageView photoProfil;
    @FXML
    private Label varSolde;
    @FXML
    private Button buttonDeposerSoldeAc;
    @FXML
    private TextField TextSolde;
    @FXML
    private Button succSolde;
    @FXML
    private Button errorSolde;
    @FXML
    private ImageView photoProfilPageExp1;
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
    @FXML
    private Button buttonModifierMonCompte;
    @FXML
    private Text state;
    @FXML
    private Text date;
    @FXML
    private Button btnBasic;
    @FXML
    private Button btnPremium;
    @FXML
    private Label emailMonProfil1;
    @FXML
    private Label varUsernameToolbar1;
    @FXML
    private Text emailMonProfil2;
    @FXML
    private Text varUsernameToolbar2;
    @FXML
    private Label emailMonProfil3;
    @FXML
    private Label varUsernameToolbar3;
    @FXML
    private Text emailMonProfil4;
    @FXML
    private Text varUsernameToolbar4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {

                    Stage stage = new Stage();
                    Stage stageprev = (Stage) home.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilUser.fxml"));
                    Parent parent = loader.load();
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
          
        btnBasic.setOnMouseClicked((MouseEvent event5)->{
            User u = new User();
            SponsorService sps=new SponsorService();
            if (sps.isSponsoredUser(u));
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
        dropDownMenu.setOnMouseEntered(
                (MouseEvent event4) -> {
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
                }
        );

        home.setOnMouseEntered(
                (MouseEvent event4) -> {
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
         TranslateTransition openNav = new TranslateTransition(new Duration(350), navList);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), navList);
        TranslateTransition openNav1 = new TranslateTransition(new Duration(350), navList1);
        TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), navList1);
        TranslateTransition openNav2 = new TranslateTransition(new Duration(350), navList2);
        TranslateTransition closeNav2 = new TranslateTransition(new Duration(350), navList2);
       

        navList.setOnMouseEntered((MouseEvent event1) -> {

            openNav.setToX(141);
            openNav.play();
        });

        navList.setOnMouseExited((MouseEvent event1) -> {

            closeNav.setToX( 0);
            closeNav.play();
        });

        navList1.setOnMouseEntered((MouseEvent event3) -> {

            openNav1.setToX(150);
            openNav1.play();
        });

        navList1.setOnMouseExited((MouseEvent event3) -> {

            closeNav1.setToX(0);
            closeNav1.play();
        });

        navList2.setOnMouseEntered((MouseEvent event2) -> {

            openNav2.setToX(250);
            openNav2.play();
        });

        navList2.setOnMouseExited((MouseEvent event2) -> {

            closeNav2.setToX(0);
            closeNav2.play();
        });
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
