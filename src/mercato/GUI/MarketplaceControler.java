/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mercato.GUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import mercato.DAO.Classe.MarketPlaceService;
import mercato.DAO.Classe.SponsorService;
import mercato.Model.DateConverter;
import mercato.Model.DesktopNotification;
import mercato.Model.Product;
import mercato.Model.SponsoredUser;
import mercato.Model.Upload;
import mercato.Model.User;
import mercato.Technique.ConfigurationJava;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author PC-Yassine
 */
public class MarketplaceControler implements Initializable {

    @FXML
    private AnchorPane GlobalAnchorID;
    @FXML
    private TabPane tabToutsLesProjet;
    @FXML
    private Tab TabMarketPlace;
    @FXML
    private ListView<Product> ListViewProduit;
    @FXML
    private Tab TabMesProduit;
    @FXML
    private ListView<Product> ListViewMesProduit;
    @FXML
    private Tab TabProduitAjout;
    @FXML
    private TextField nom;
    @FXML
    private MenuButton typeProduit;
    @FXML
    private Button buttonAddProduct;
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
    private Rectangle recimgv3;
    @FXML
    private ImageView imagev3;
    @FXML
    private Rectangle recimgv4;
    @FXML
    private ImageView imagev4;
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
    private AnchorPane navList;
    @FXML
    private AnchorPane navList1;
    @FXML
    private Pane menu1;
    @FXML
    private AnchorPane navList2;
    @FXML
    private Pane menu2;
    @FXML
    private Pane dropDownMenu;
    @FXML
    private Pane notifmenu;
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
    public static int idl;
    private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
    private FileChooser.ExtensionFilter extFilterJPEG;
    private FileChooser.ExtensionFilter extFilterjpeg;
    private FileChooser.ExtensionFilter extFilterPNG;
    private FileChooser.ExtensionFilter extFilterpng;
    private Upload up;
    private File file1 = new File("");
    private File file2 = new File("");
    private File file3 = new File("");
    private File file4 = new File("");
    private List<Product> products = new ArrayList<>();
    private List<Product> myproducts = new ArrayList<>();
    @FXML
    private MenuItem clothes;
    @FXML
    private MenuItem equipement;
    @FXML
    private MenuItem consumable;
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
    private Pane annoncep;

    private ObservableList<Product> data;
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
    private ImageView recherche1;
    @FXML
    private MenuButton trier1;
    @FXML
    private MenuItem dateDecTrier1;
    @FXML
    private MenuItem dateTrierCr1;
    @FXML
    private MenuItem prixTrierDec1;
    @FXML
    private MenuItem prixTrierCr1;
    @FXML
    private MenuButton trier11;
    @FXML
    private MenuItem dateDecTrier11;
    @FXML
    private MenuItem dateTrierCr11;
    @FXML
    private MenuItem prixTrierDec11;
    @FXML
    private MenuItem prixTrierCr11;
    @FXML
    private ImageView recherchebtn11;
    @FXML
    private TextField recherchebar1;
    @FXML
    private TextField recherchebar11;

    /**
     * Initializes the controller class.
     */ 
    public void afficherMesProducts() {
        try {
            ListViewMesProduit.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> ListViewProduit) {
                ListCell<Product> cell = new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product p, boolean bln) {
                        super.updateItem(p, bln);
                        if (p != null) {
                            VBox vb = new VBox(10);
                            HBox hb = new HBox(20);
                            hb.setStyle("-fx-border-color: #C8C8C8 ;");
                            vb.setAlignment(Pos.CENTER);
                            VBox vb2 = new VBox(10);
                            vb.setAlignment(Pos.CENTER);
                            vb2.setAlignment(Pos.BASELINE_LEFT);
                            //////////////////////////////////////////////////////////////
                            Button btnModifier = new Button();
                            btnModifier.setId("btnProduit" + p.getId());
                            btnModifier.setText("Modifier ce produit");
                            Image imageAccesProduct = new Image("mercato/images/competences.png");
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
                                        idl = p.getId();

                                        Stage stageprev = (Stage) btnModifier.getScene().getWindow();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduct.fxml"));
                                        Parent parent = loader.load();
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(parent);
                                        stage.setScene(scene);
                                        stage.show();
                                        stageprev.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //////////////////////////////////////////////////////////////
                            Button btnSupprimer = new Button();
                            btnSupprimer.setId("btnSupprimer" + p.getId());
                            btnSupprimer.setText("supprimer ce produit");
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
                                        MarketPlaceService mps = new MarketPlaceService();
                                        mps.deleteProduct(p.getId());
                                    } catch (Exception ex) {
                                        Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //////////////////////////////////////////////////////////////
                            Label invisible = new Label();
                            invisible.setVisible(false);
                            Label titre = new Label(p.getName().toUpperCase());
                            Label description = new Label("Description : " + p.getDescription());
                            Label dateDepotText = new Label("Date de depot :");
                            Label status = new Label("Status :" + p.getState().toString());
                            Label price = new Label("prix DT : " + p.getPrice());
                            titre.setTextFill(Paint.valueOf("#80b201"));
                            ImageView imgv = new ImageView();
                            imgv.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.6) , 30, 0.2 , 1, 1 ); ");
                            String s = getClass().getResource("/mercato/GUI/photos/") + p.getImage1().trim();
                            Image image;
                            image = new Image(s);
                            imgv.setFitHeight(200);
                            imgv.setFitWidth(300);
                            imgv.setImage(image);
                            vb.getChildren().addAll(imgv, titre);
                            vb2.getChildren().addAll(description, dateDepotText, status, price, btnModifier, btnSupprimer, invisible);
                            hb.getChildren().addAll(vb, vb2);
                            setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        });
            } catch (Exception e) {
        }

    }
        
    public void afficherProducts() {
        try {
            ListViewProduit.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
                @Override
                public ListCell<Product> call(ListView<Product> ListViewProduit) {
                    ListCell<Product> cell;
                    cell = new ListCell<Product>() {

                        @Override
                        protected void updateItem(Product p, boolean bln) {
                            super.updateItem(p, bln);
                            if (p != null) {
                                VBox vb = new VBox(10);
                                HBox hb = new HBox(20);
                                hb.setStyle("-fx-border-color: #C8C8C8 ;");
                                vb.setAlignment(Pos.CENTER);
                                VBox vb2 = new VBox(20);
                                vb.setAlignment(Pos.CENTER);
                                vb2.setAlignment(Pos.BASELINE_LEFT);
                                Button btnProduit = new Button();
                                btnProduit.setId("btnProduit" + p.getId());
                                btnProduit.setText("Acceder au produit");
                                Image imageAccesProjet = new Image("mercato/images/enter.png");
                                ImageView enterIV = new ImageView(imageAccesProjet);
                                enterIV.setFitHeight(20);
                                enterIV.setFitWidth(20);
                                btnProduit.setGraphic(enterIV);
                                btnProduit.getStyleClass().add("buttonLogin");
                                btnProduit.getStyleClass().add("buttonLogin:hover");
                                btnProduit.setOnAction(new EventHandler<ActionEvent>() {
                                    ///////////////////////////////////////////////////////////
                                    @Override
                                    public void handle(ActionEvent e) {
                                        try {

                                            idl = p.getId();
                                            Stage stage = new Stage();

                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
                                            Parent parent = loader.load();
                                            Scene scene = new Scene(parent);
                                            stage.setScene(scene);
                                            stage.setResizable(false);
                                            stage.show();

                                        } catch (IOException ex) {
                                            Logger.getLogger(MarketplaceControler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                //////////////////////////////////////////////////////////////
                                Label invisible = new Label();
                                invisible.setVisible(false);
                                Label titre = new Label(p.getName().toUpperCase());
                                Label description = new Label("Description : " + p.getDescription());
                                Label dateDepotText = new Label("Date de depot :");
                                Label status = new Label("Status :" + p.getState());
                                Label price = new Label("prix DT : " + p.getPrice());
                                titre.setTextFill(Paint.valueOf("#80b201"));
                                ImageView imgv = new ImageView();
                                imgv.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.6) , 30, 0.2 , 1, 1 ); ");
                                String s = getClass().getResource("/mercato/GUI/photos/") + p.getImage1().trim();
                                Image image;
                                image = new Image(s);
                                imgv.setFitHeight(200);
                                imgv.setFitWidth(300);
                                imgv.setImage(image);
                                vb.getChildren().addAll(imgv, titre);
                                vb2.getChildren().addAll(description, dateDepotText, status, price, btnProduit, invisible);
                                hb.getChildren().addAll(vb, vb2);
                                setGraphic(hb);
                            }
                        }
                    };
                    return cell;

                }
            });
        } catch (Exception e) {
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////:////////////////////////::
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//******************************************************list all products*****************************************************************

        MarketPlaceService mps = new MarketPlaceService();
        products = mps.ListAll();
        data = FXCollections.observableArrayList();
        data.addAll(products);
        ListViewProduit.setItems(data);
        afficherProducts();

//***********************************************************mes produit**********************************************************
        SponsoredUser sp = new SponsoredUser();
        SponsorService sps = new SponsorService();
        sp = sps.FindSponsoredUser(4);
        myproducts = mps.getBySponsoredUser(sp);
        data = FXCollections.observableArrayList();
        data.addAll(myproducts);
        ListViewMesProduit.setItems(data);
        afficherMesProducts();
//**********************************************************upload images********************************************************************
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

        });
//********************************************************animations*******************************************************
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
//***************************************************btn acceuil*****************************************************************
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                
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
//***********************************************btn addproduct****************************************************************
        buttonAddProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {

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
                p.setPt(String.valueOf(typeProduit.getText()));
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
                p.setState("Available");

                SponsorService sp = new SponsorService();
                p.setSp(sp.FindSponsoredUser(4));
                MarketPlaceService mps = new MarketPlaceService();
                mps.addProduct(p);
                DesktopNotification.notification("Ajout Produit", "votre produit a ete ajouter avec succes", NotificationType.SUCCESS);
            }
        });
//*******************************************************rechercher***************************************************************
        recherche1.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (recherchebar1.getText().isEmpty()) {
                    recherchebar1.setStyle("-fx-text-box-border: #f44336 ;");

                    MarketPlaceService mps = new MarketPlaceService();
                    products = mps.ListAll();
                    data = FXCollections.observableArrayList();
                    data.addAll(products);
                    ListViewProduit.setItems(data);
                    afficherProducts();
                } else {

                    products = mps.findProdByName(recherchebar1.getText().toLowerCase().trim());
                    data = FXCollections.observableArrayList();
                    data.addAll(products);
                    ListViewProduit.setItems(data);
                    afficherProducts();
                    if (products.isEmpty()) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Pas de Resultat");
                        a.show();
                        products = mps.ListAll();
                        data = FXCollections.observableArrayList();
                        data.addAll(products);
                        ListViewProduit.setItems(data);
                        afficherProducts();
                    } else {
                        products = mps.findProdByName(recherchebar1.getText().toLowerCase().trim());
                        data = FXCollections.observableArrayList();
                        data.addAll(products);
                        ListViewProduit.setItems(data);
                        afficherProducts();
                    }
                }
            }
        });
        
        recherchebtn11.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (recherchebar11.getText().isEmpty()) {
                    recherchebar11.setStyle("-fx-text-box-border: #f44336 ;");

                    MarketPlaceService mps = new MarketPlaceService();
                    myproducts = mps.ListAll();
                    data = FXCollections.observableArrayList();
                    data.addAll(myproducts);
                    ListViewMesProduit.setItems(data);
                    afficherProducts();
                } else {
                    myproducts = mps.findMyProdByName(1,recherchebar11.getText().toLowerCase().trim());
                    data = FXCollections.observableArrayList();
                    data.addAll(myproducts);
                    ListViewMesProduit.setItems(data);
                    afficherMesProducts();
                    if (myproducts.isEmpty()) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Pas de Resultat");
                        a.show();
                        myproducts = mps.ListAll();
                        data = FXCollections.observableArrayList();
                        data.addAll(myproducts);
                        ListViewMesProduit.setItems(data);
                        afficherProducts();
                    } else {
                        myproducts = mps.findProdByName(recherchebar11.getText().toLowerCase().trim());
                        data = FXCollections.observableArrayList();
                        data.addAll(myproducts);
                        ListViewMesProduit.setItems(data);
                        afficherProducts();
                    }
                }
            }
        });
    }

    @FXML
    private void AddProductAction(ActionEvent event) {
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
    private void TrierDateDecAction1(ActionEvent event) {
        dateDecTrier1.setText("Date v");

    }

    @FXML
    private void dateTrierCrAction1(ActionEvent event) {
        dateTrierCr1.setText("Date ^");
    }

    @FXML
    private void prixTrierDecAction1(ActionEvent event) {
        prixTrierDec1.setText("Prix v");
    }

    @FXML
    private void prixTrierCrAction1(ActionEvent event) {
        prixTrierCr1.setText("Prix ^");
    }

    @FXML
    private void TrierDateDecAction11(ActionEvent event) {
        dateDecTrier11.setText("Date v");
    }

    @FXML
    private void dateTrierCrAction11(ActionEvent event) {
        dateTrierCr11.setText("Date ^");
    }

    @FXML
    private void prixTrierDecAction11(ActionEvent event) {
        prixTrierDec11.setText("Prix v");

    }

    @FXML
    private void prixTrierCrAction11(ActionEvent event) {
        prixTrierCr11.setText("Prix ^");
    }

}
