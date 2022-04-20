/*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
 */
package GUI.Back.Product;

import Entities.Category;
import Entities.Product;
import Service.ServiceProduct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Aicha
 */
public class ProductController implements Initializable {

    List<String> type;
    String imgG = "";
    Product f = null;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField ProdName;
    @FXML
    private TextField ProdPrice;
    @FXML
    private TextField ProdQT;
    @FXML
    private TextField ProdDiscount;
    @FXML
    private TextArea ProdDesc;
    @FXML
    private ComboBox<Category> id_category;
    @FXML
    private ImageView imageview;
    @FXML
    private Button imagechose;
    @FXML
    private TextField rechercher;
    private RadioButton tri1;
    @FXML
    private TableView<Product> view;
    @FXML
    private TableColumn<Product, String> cl_name;
    private TableColumn<Product, String> cl_category;
    @FXML
    private TableColumn<Product, String> cl_desc;
    @FXML
    private TableColumn<Product, String> cl_price;
    @FXML
    private TableColumn<Product, String> cl_qt;
    @FXML
    private TableColumn<Product, String> cl_discount;
    @FXML
    private TableColumn<Product, String> cl_initial_price;
    @FXML
    private Button Product;
    @FXML
    private Button category;
    @FXML
    private Button ajout;
    private RadioButton tri2;
    @FXML
    private Label message;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ajout.setDisable(true);
        view.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_discount.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_initial_price.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_qt.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            displayAll();
            addButtonToTable();
            rechercher();

        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // remplir liste des categories
        Callback<ListView<Category>, ListCell<Category>> cellFactory = new Callback<ListView<Category>, ListCell<Category>>() {

            @Override
            public ListCell<Category> call(ListView<Category> l) {
                return new ListCell<Category>() {

                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        id_category.setButtonCell(cellFactory.call(null));
        id_category.setCellFactory(cellFactory);

        ServiceProduct us = new ServiceProduct();
        List<Category> arr = new ArrayList<>();
        try {
            arr = us.findCatrgory();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Category u : arr) {
            String name = u.getName();

            id_category.getItems().add(u);
        }

        // fin remplir liste category 
        // format d'image acceptées
        type = new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");

        // controll de saisie  l formulaire lezem tkoun m3ebya 
        id_category.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || newValue == null || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        ProdDesc.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || id_category.getValue() == null || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        ProdDiscount.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || id_category.getValue() == null || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        ProdQT.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || id_category.getValue() == null || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        ProdPrice.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || id_category.getValue() == null || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        ProdName.textProperty().addListener((ov, oldValue, newValue) -> {
            if (id_category.getValue() == null || OnlyNumber(ProdPrice.getText(), ProdPrice.getText().length()) == false || OnlyNumber(ProdQT.getText(), ProdQT.getText().length()) == false || OnlyNumber(ProdDiscount.getText(), ProdDiscount.getText().length()) == false || ProdName.getText().length() == 0 || ProdPrice.getText().length() == 0 || ProdQT.getText().length() == 0 || ProdDiscount.getText().length() == 0 || ProdDesc.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });

    }
// static car la méthode est une methode de classe, donc elle sera testée sur une variable
// chartAt(i) tekhou men str la position de i dans la chaine de caractère w onla teste keny bin 0 et 9
    public static boolean OnlyNumber(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) >= '0'
                    && str.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

// ajouter button supprimer pour chaque ligne de tableau 
    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
                    private final Button delete = new Button("");
                    private final HBox pane = new HBox(delete);
                    //ajouter l'image pour button supprimer 

                    {
                        Image btn_delete = new Image(getClass().getResourceAsStream("delete.png"));
                        delete.setGraphic(new ImageView(btn_delete));
                        delete.setMaxSize(10, 10);

                        // ajouter message au survol sur button
                        final Tooltip tooltip = new Tooltip();
                        tooltip.setText("supprimer ");
                        delete.setTooltip(tooltip);
                        final Tooltip tooltip2 = new Tooltip();

                        // ajouter fonction supprimer au button avec message de confirmation 
                        delete.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce produit?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {
                                ServiceProduct sf = new ServiceProduct();
                                Product p = getTableView().getItems().get(getIndex());
                                try {
                                    sf.delete(p.getId());
                                    displayAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }

                    // pour afficher button supprimer 
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
        view.getColumns().add(actionCol);

    }
//fonction recherche sur le tableau

    @FXML
    void rechercher() throws SQLException {
        ServiceProduct sf = new ServiceProduct();
        ArrayList listcs = (ArrayList) sf.DisplayAll();
        ObservableList OFormation = FXCollections.observableArrayList(listcs);
        FilteredList<Product> filteredData = new FilteredList<>(OFormation, p -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getPrice()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getDescription()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(view.comparatorProperty());
        view.setItems(sortedData);
    }
// Afficher tt les produits

    public void displayAll() throws SQLException {
        ServiceProduct P = new ServiceProduct();
        List lists = P.DisplayAll();

        ObservableList ListProduct = FXCollections.observableArrayList(lists);

        view.setItems(ListProduct);

        //nombre.setText(Integer.toString(number));
        cl_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cl_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        cl_qt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        cl_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        cl_initial_price.setCellValueFactory(new PropertyValueFactory<>("initial_price"));
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        ServiceProduct sf = new ServiceProduct();
        String price = ProdPrice.getText();
        String discount = ProdDiscount.getText();
        double price1 = Integer.parseInt(price);
        double discount1 = Integer.parseInt(discount);
        double newPrice = (price1 * (100 - discount1)) / 100;
        String pric = Double.toString(newPrice);
        if (imgG.length() == 0) {

            sf.ajouter(new Product(0, id_category.getValue().getId(), ProdName.getText(), ProdDesc.getText(), pric, "", ProdQT.getText(), ProdDiscount.getText(), ProdPrice.getText()));
        }
        sf.ajouter(new Product(0, id_category.getValue().getId(), ProdName.getText(), ProdDesc.getText(), pric, imgG, ProdQT.getText(), ProdDiscount.getText(), ProdPrice.getText()));
if((Integer.parseInt(ProdDiscount.getText()))>=0 && (Integer.parseInt(ProdDiscount.getText()))<=100){
    System.out.println("le Discount doit être compris entre 0% et 100%");
}
        if (Integer.parseInt(ProdDiscount.getText()) == 0 ){
        Notifications notificationBuilder = Notifications.create().title("Alert").text("ce produit n'est pas en promotion").graphic(null).hideAfter(javafx.util.Duration.seconds(20))
                    .position(Pos.BASELINE_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
                            System.out.println("clicked on ");
                 }
                    });
            notificationBuilder.showWarning();
            notificationBuilder.show();}
        JOptionPane.showMessageDialog(null, "Ajout effectué");
        displayAll();
        ProdName.clear();
        ProdPrice.clear();
        ProdQT.clear();
        ProdDiscount.clear();
        imageview.setImage(null);
        ProdDesc.setText(null);

    }

    @FXML
    // fontion hedhii ta3ml l upload lel image en local et sur un serveur ftp ( API  mezyen )  n3aytolha fl fonction ajouter
    private void import_image(ActionEvent event) throws IOException {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc = f.showOpenDialog(null);
        if (f != null) {
             //code sur lien https://profreehost.com/account/?view=manage&account_id=1923615
            String server = "ftpupload.net";
            int port = 21;
            String user = "unaux_31551320";
            String pass = "40yehax52ztq";

            FTPClient ftpClient = new FTPClient();
            try {

                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                String $mess = ftpClient.getReplyString();
                System.out.println($mess);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

                File firstLocalFile = new File(fc.getAbsoluteFile().toString());

                String firstRemoteFile = "/htdocs/" + fc.getName().toString();
                InputStream inputStream = new FileInputStream(firstLocalFile);

                System.out.println("Start uploading image");
                boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
                inputStream.close();
                if (done) {
                    message.setText("your image was uploaded successfully.");
                } else {
                    message.setText("error");
                }

            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            imgG = fc.getName().toString();

            // le path de copie des images C:\Users\perso\Desktop\3èmeannée\semestre2\PI DEV\Fast Truck\FoodApp\src\GUI\Front\images
            Path temp = Files.copy(fc.toPath(), Paths.get("C:\\Users\\perso\\Desktop\\3èmeannée\\semestre2\\PIDEV\\FastTruck\\FoodApp\\src\\GUI\\Front\\images" + imgG), StandardCopyOption.REPLACE_EXISTING);
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            imageview.setImage(i);
        }
        fc.exists();
    }

    @FXML
    private void updateName(CellEditEvent edditedCell) throws SQLException {
        ServiceProduct P = new ServiceProduct();
        Product personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        int id = personSelected.getId();
        String price = personSelected.getPrice();
        String qt = personSelected.getQuantite();
        String description = personSelected.getDescription();
        String discount = personSelected.getDiscount();
        String init_price = personSelected.getInitial_price();
        personSelected.setDescription(edditedCell.getNewValue().toString());

        P.update(id, edditedCell.getNewValue().toString(), init_price, discount, price, description, qt);
        displayAll();
    }

    @FXML
    private void updateDescription(CellEditEvent edditedCell) throws SQLException {
        ServiceProduct P = new ServiceProduct();
        Product personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        int id = personSelected.getId();
        String price = personSelected.getPrice();
        String qt = personSelected.getQuantite();
        String description = personSelected.getDescription();
        String discount = personSelected.getDiscount();
        String init_price = personSelected.getInitial_price();
        personSelected.setDescription(edditedCell.getNewValue().toString());

        P.update(id, name, init_price, discount, price, edditedCell.getNewValue().toString(), qt);
        displayAll();
    }

    @FXML
    private void UpdateQt(CellEditEvent edditedCell) throws SQLException {
        ServiceProduct P = new ServiceProduct();
        Product personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        int id = personSelected.getId();
        String price = personSelected.getPrice();
        String qt = personSelected.getQuantite();
        String description = personSelected.getDescription();
        String discount = personSelected.getDiscount();
        String init_price = personSelected.getInitial_price();
        personSelected.setDescription(edditedCell.getNewValue().toString());

        P.update(id, name, init_price, discount, price, description, edditedCell.getNewValue().toString());
        displayAll();
    }

    @FXML
    private void UpdateDis(CellEditEvent edditedCell) throws SQLException {
        ServiceProduct P = new ServiceProduct();
        Product personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        int id = personSelected.getId();
        String price = personSelected.getPrice();
        String qt = personSelected.getQuantite();
        String description = personSelected.getDescription();
        String discount = personSelected.getDiscount();
        String init_price = personSelected.getInitial_price();
        personSelected.setDescription(edditedCell.getNewValue().toString());

        double price1 = Integer.parseInt(init_price);
        double discount1 = Integer.parseInt(edditedCell.getNewValue().toString());
        double newPrice = (price1 * (100 - discount1)) / 100;
        String pric = Double.toString(newPrice);

        P.update(id, name, init_price, edditedCell.getNewValue().toString(), pric, description, qt);
        displayAll();
        
    }

    @FXML
    private void UpdatePrice(CellEditEvent edditedCell) throws SQLException {
        ServiceProduct P = new ServiceProduct();
        Product personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getName();
        int id = personSelected.getId();
        String price = personSelected.getPrice();
        String qt = personSelected.getQuantite();
        String description = personSelected.getDescription();
        String discount = personSelected.getDiscount();
        String init_price = personSelected.getInitial_price();
        personSelected.setDescription(edditedCell.getNewValue().toString());

        double price1 = Integer.parseInt(edditedCell.getNewValue().toString());
        double discount1 = Integer.parseInt(discount);
        double newPrice = (price1 * (100 - discount1)) / 100;
        String pric = Double.toString(newPrice);
        P.update(id, name, edditedCell.getNewValue().toString(), discount, pric, description, qt);
        displayAll();
        
    }

    // changement de page
    @FXML
    private void PageCategory(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Category/category.fxml")));
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
