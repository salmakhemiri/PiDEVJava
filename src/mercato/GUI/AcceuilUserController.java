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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC-Yassine
 */
public class AcceuilUserController implements Initializable {

    @FXML
    private Pane paneAnnonce;
    @FXML
    private Pane paneProfils;
    @FXML
    private Pane panerendezvous;
    @FXML
    private Pane paneMarketplace;
    @FXML
    private Pane MonProfilMenu;
    @FXML
    private Button buttonAccederMonProfil;

    @FXML
    private Text nomPrenomMonProfil;
    @FXML
    private Text emailMonProfil;
    @FXML
    private Text soldeMonProfil;
    @FXML
    private Text notif1;
    @FXML
    private Text notif2;
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
    private Pane viewmenu;
    @FXML
    private Pane dropDownMenu;
    @FXML
    private Text varUsernameToolBar;
    @FXML
    private ImageView varPhotoProfilToolBar;
    @FXML
    private ImageView home;
    @FXML
    private ImageView viewnotif;
    @FXML
    private Pane PaneReclamation;
    @FXML
    private Pane notifmenu;
    @FXML
    private ImageView PhotoProfilMonProfil;
    @FXML
    private ImageView notif;
    @FXML
    private Text notif4;
    @FXML
    private Button boutonNotification;
    @FXML
    private Text view1;
    @FXML
    private Text view2;
    @FXML
    private Text view3;
    @FXML
    private Text view4;
    @FXML
    private Button bview1;
    @FXML
    private Button bview2;
    @FXML
    private Button bview3;
    @FXML
    private Button bview4;
    @FXML
    private Button boutonview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        paneMarketplace.setOnMouseEntered(
                (MouseEvent event4) -> {
                    TranslateTransition transTransition = TranslateTransitionBuilder.create()
                            .duration(new Duration(140))
                            .node(paneMarketplace)
                            .toY(-3)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1).
                            onFinished(e -> {
                                TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                        .duration(new Duration(140))
                                        .node(paneMarketplace)
                                        .toY(3)
                                        .interpolator(Interpolator.LINEAR)
                                        .cycleCount(1)
                                        .build();
                                transTransition2.play();
                            })
                            .build();
                    transTransition.play();
                });
        panerendezvous.setOnMouseEntered(
                (MouseEvent event4) -> {
                    TranslateTransition transTransition = TranslateTransitionBuilder.create()
                            .duration(new Duration(140))
                            .node(panerendezvous)
                            .toY(-3)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1).
                            onFinished(e -> {
                                TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                        .duration(new Duration(140))
                                        .node(panerendezvous)
                                        .toY(3)
                                        .interpolator(Interpolator.LINEAR)
                                        .cycleCount(1)
                                        .build();
                                transTransition2.play();
                            })
                            .build();
                    transTransition.play();
                });
        paneProfils.setOnMouseEntered(
                (MouseEvent event4) -> {
                    TranslateTransition transTransition = TranslateTransitionBuilder.create()
                            .duration(new Duration(140))
                            .node(paneProfils)
                            .toY(-3)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1).
                            onFinished(e -> {
                                TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                        .duration(new Duration(140))
                                        .node(paneProfils)
                                        .toY(3)
                                        .interpolator(Interpolator.LINEAR)
                                        .cycleCount(1)
                                        .build();
                                transTransition2.play();
                            })
                            .build();
                    transTransition.play();
                });
        paneAnnonce.setOnMouseEntered(
                (MouseEvent event4) -> {
                    TranslateTransition transTransition = TranslateTransitionBuilder.create()
                            .duration(new Duration(140))
                            .node(paneAnnonce)
                            .toY(-3)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1).
                            onFinished(e -> {
                                TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                        .duration(new Duration(140))
                                        .node(paneAnnonce)
                                        .toY(3)
                                        .interpolator(Interpolator.LINEAR)
                                        .cycleCount(1)
                                        .build();
                                transTransition2.play();
                            })
                            .build();
                    transTransition.play();
                });
        PaneReclamation.setOnMouseEntered(
                (MouseEvent event4) -> {
                    TranslateTransition transTransition = TranslateTransitionBuilder.create()
                            .duration(new Duration(140))
                            .node(PaneReclamation)
                            .toY(-3)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1).
                            onFinished(e -> {
                                TranslateTransition transTransition2 = TranslateTransitionBuilder.create()
                                        .duration(new Duration(140))
                                        .node(PaneReclamation)
                                        .toY(3)
                                        .interpolator(Interpolator.LINEAR)
                                        .cycleCount(1)
                                        .build();
                                transTransition2.play();
                            })
                            .build();
                    transTransition.play();
                });
        
        PaneReclamation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); 
                try {

                    Stage stage = new Stage();
                    Stage stageprev = (Stage) home.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                    Parent parent = loader.load();
                    Stage stagep = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    stageprev.close();
                } catch (IOException ex) {
                    Logger.getLogger("ReclamationController.java").log(Level.SEVERE, null, ex);
                }
            }
        });
        
        paneMarketplace.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); 
                try {

                    Stage stage = new Stage();
                    Stage stageprev = (Stage) home.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Marketplace.fxml"));
                    Parent parent = loader.load();
                    Stage stagep = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    stageprev.close();
                } catch (IOException ex) {
                    Logger.getLogger("MarketPlaceControler.java").log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
