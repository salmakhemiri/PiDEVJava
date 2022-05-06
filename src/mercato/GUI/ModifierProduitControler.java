/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mercato.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mercato.DAO.Classe.MarketPlaceService;
import mercato.Model.Product;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import mercato.DAO.Classe.SponsorService;
import mercato.Model.DateConverter;
import mercato.Model.DesktopNotification;
import mercato.Model.Upload;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author PC-Yassine
 */
public class ModifierProduitControler implements Initializable {

    @FXML
    private AnchorPane GlobalAnchorID;
    @FXML
    private TextField nom;
    @FXML
    private MenuButton typeProduit;
    @FXML
    private MenuItem clothes;
    @FXML
    private MenuItem equipement;
    @FXML
    private MenuItem consumable;
    @FXML
    private Button image1;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private TextField quantity;
    @FXML
    private Button image2;
    @FXML
    private Button image3;
    @FXML
    private Button image4;
    @FXML
    private Rectangle recimgv1;
    @FXML
    private ImageView imagev1;
    @FXML
    private Rectangle recimgv2;
    @FXML
    private ImageView imagev2;
    @FXML
    private Rectangle recimgv4;
    @FXML
    private ImageView imagev4;
    @FXML
    private Rectangle recimgv3;
    @FXML
    private ImageView imagev3;
    @FXML
    private Button buttonupdateProduct;
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
    private Button refresh;
    public static int idl;
    public Image image11;
    public Image image12;
    public Image image13;
    public Image image14;
    private Upload up;
    private File file1 = new File("");
    private File file2 = new File("");
    private File file3 = new File("");
    private File file4 = new File("");
    private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
    private FileChooser.ExtensionFilter extFilterJPEG;
    private FileChooser.ExtensionFilter extFilterjpeg;
    private FileChooser.ExtensionFilter extFilterPNG;
    private FileChooser.ExtensionFilter extFilterpng;
    /**
     * Initializes the controller class.
     */
    public Product p = new Product();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//******************************************************************************************************************************
        int i;
        i = MarketplaceControler.idl;

        MarketPlaceService mps = new MarketPlaceService();
        p = mps.getById(i);
        nom.setText(p.getName());
        description.setText(p.getDescription());
        quantity.setText(String.valueOf(p.getQuantity()));
        prix.setText(String.valueOf(p.getPrice()));
        typeProduit.setText(p.getPt());
        image11 = new Image("/mercato/GUI/photos/" + p.getImage1());
        System.out.println(p.getState());
        try {
            image12 = new Image("/mercato/GUI/photos/" + p.getImage2());
            image13 = new Image("/mercato/GUI/photos/" + p.getImage3());
            image14 = new Image("/mercato/GUI/photos/" + p.getImage4());
        } catch (Exception e) {
        }
        imagev1.setImage(image11);
        imagev2.setImage(image12);
        imagev3.setImage(image13);
        imagev4.setImage(image14);
//*************************************************btn images*****************************************************************
        image1.setOnMouseClicked((MouseEvent event1) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            extFilterjpeg
                    = new FileChooser.ExtensionFilter("jpg files (*.JPEG)", "*.JPEG");
            extFilterJPEG
                    = new FileChooser.ExtensionFilter("jpg files (*.jpeg)", "*.jpeg");
            extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterjpeg, extFilterJPEG);
            up = new Upload();
            file1 = fileChooser.showOpenDialog(null);
            try {
                up.upload(file1, "");

            } catch (IOException ex) {
                Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Image img1;
                img1 = new Image(getClass().getClassLoader().getResource("mercato/GUI/photos/" + file1.getName()).toExternalForm());
                imagev1.setImage(img1);
            } catch (Exception e) {

            }
        });

        image2.setOnMouseClicked((MouseEvent event1) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            extFilterjpeg
                    = new FileChooser.ExtensionFilter("jpg files (*.JPEG)", "*.JPEG");
            extFilterJPEG
                    = new FileChooser.ExtensionFilter("jpg files (*.jpeg)", "*.jpeg");
            extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterjpeg, extFilterJPEG);
            up = new Upload();
            file2 = fileChooser.showOpenDialog(null);
            try {
                up.upload(file2, "");
            } catch (IOException ex) {
                Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image img2;
            img2 = new Image(getClass().getResource("/mercato/GUI/photos/" + file2.getName()).toExternalForm());
            imagev2.setImage(img2);

        });

        image3.setOnMouseClicked((MouseEvent event1) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            extFilterjpeg
                    = new FileChooser.ExtensionFilter("jpg files (*.JPEG)", "*.JPEG");
            extFilterJPEG
                    = new FileChooser.ExtensionFilter("jpg files (*.jpeg)", "*.jpeg");
            extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterjpeg, extFilterJPEG);
            up = new Upload();
            file3 = fileChooser.showOpenDialog(null);
            try {
                up.upload(file3, "");
            } catch (IOException ex) {
                Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image img3;
            img3 = new Image(getClass().getResource("/mercato/GUI/photos/" + file3.getName()).toExternalForm());
            imagev3.setImage(img3);

        });

        image4.setOnMouseClicked((MouseEvent event1) -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            extFilterjpeg
                    = new FileChooser.ExtensionFilter("jpg files (*.JPEG)", "*.JPEG");
            extFilterJPEG
                    = new FileChooser.ExtensionFilter("jpg files (*.jpeg)", "*.jpeg");
            extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterjpeg, extFilterJPEG);
            up = new Upload();
            file4 = fileChooser.showOpenDialog(null);
            try {
                up.upload(file4, "");
            } catch (IOException ex) {
                Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image img4;
            img4 = new Image(getClass().getResource("/mercato/GUI/photos/" + file4.getName()).toExternalForm());
            imagev4.setImage(img4);

            HBox hbxImg = new HBox();
            hbxImg.setAlignment(Pos.CENTER);
            hbxImg.getChildren().add(imagev4);
        });

//*************************************************btn modifier*****************************************************************************
        buttonupdateProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {

                Product p = new Product();
                try {
                    p.setDate(DateConverter.dateConverter(new Date()));
                } catch (ParseException ex) {
                    Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.setDescription(description.getText());
                p.setName(nom.getText());
                p.setPrice(Float.parseFloat(prix.getText()));
                p.setPt((typeProduit.getText()));
                p.setQuantity(Integer.parseInt(quantity.getText()));
                try {

                    up.upload(file1, "http://localhost/photos/Mercato");
                    p.setImage1(file1.getName());

                    if (file2.isFile()) {
                        up.upload(file2, "http://localhost/photos/Mercato");
                        p.setImage2(file2.getName());
                    }
                    if (file3.isFile()) {
                        up.upload(file3, "http://localhost/photos/Mercato");
                        p.setImage3(file3.getName());
                    }
                    if (file4.isFile()) {
                        up.upload(file4, "http://localhost/photos/Mercato");
                        p.setImage4(file4.getName());
                    }
                } catch (Exception e) {
                }

                if (p.getQuantity() == 0) {

                    p.setState("Sold");
                } else {
                    p.setState("Available");
                }
                p.setId(i);
                MarketPlaceService mps = new MarketPlaceService();
                mps.UpdateProduct(p);
                DesktopNotification.notification("Modifiet Produit", "votre produit a ete Modifier avec succes", NotificationType.SUCCESS);

                
                try {
                    Stage stage = new Stage();
                Stage stageprev = (Stage) buttonupdateProduct.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MarketPlace.fxml"));
                Parent parent;
                parent = loader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stageprev.close();
                } catch (IOException ex) {
                    Logger.getLogger(ModifierProduitControler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
//**************************************************************************************************************************
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
//**************************************************************************************************************************************
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
    }

    @FXML
    private void clothesAction(ActionEvent event) {
        typeProduit.setText("vetement");
    }

    @FXML
    private void equipementAction(ActionEvent event) {
        typeProduit.setText("equipement");
    }

    @FXML
    private void consumableAction(ActionEvent event) {
        typeProduit.setText("consommable");
    }

    @FXML
    private void secteurMenu(ActionEvent event) {
    }

    @FXML
    private void updateProductAction(ActionEvent event) {
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
