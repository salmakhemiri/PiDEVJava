/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Stock;

import Alert.AlertDialog;
import Entites.Fournisseurs;
import Entites.Stock;
import Service.ServiceStock;
import Utils.DataBase;
import com.barcodelib.barcode.Linear;
import static com.barcodelib.barcode.a.b.h.f;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 * FXML Controller class
 *
 * @author Salma
 */
public class StockController implements Initializable {

    ServiceStock Four = new ServiceStock();
    Stock S = null;
    ObservableList<Fournisseurs> oblist = FXCollections.observableArrayList();
    PreparedStatement prepared = null;
    private ResultSet res = null;
    Connection con = DataBase.getInstance().getConnection();
    private Statement stmt;
    @FXML
    private Pane pnlOverview;
    private TextField ProdName;
    private TextField ProdPrice;
    private TextField ProdQT;
    private TextField ProdDiscount;
    @FXML
    private TableView<Stock> view;
    @FXML
    private TableColumn<Stock, String> cl_name;
    @FXML
    private TableColumn<Stock, String> cl_numero;
    @FXML
    private TableColumn<Stock, String> cl_price;
    @FXML
    private TableColumn<Stock, String> cl_qt;
    @FXML
    private TableColumn<Stock, String> cl_total;
    @FXML
    private Button ajout;
    @FXML
    private RadioButton tri1;
    @FXML
    private RadioButton tri2;
    @FXML
    private ComboBox<Fournisseurs> id_fournisseur;
    @FXML
    private TextField StockName;
    @FXML
    private TextField StockNum;
    @FXML
    private TextField StockQT;
    @FXML
    private TextField StockPrice;
    @FXML
    private TableColumn<?, ?> cl_four;
    @FXML
    private Label nombre;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private TextField write_barcode;
    @FXML
    private TextField read_barcode;
    @FXML
    private Label erreurnSom;
    @FXML
    private Label erreurSnum;
    @FXML
    private Label erreurSquantité;
    @FXML
    private Label erreurSprix;
    private boolean verificationnom;
    private boolean verificationprix;
    private boolean verificationquantite;
    private boolean verificationnum;
    @FXML
    private Button stat;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private Button Livreur;
    @FXML
    private Button Commande;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse;
    @FXML
    private Button Equipe;
    @FXML
    private Button Equipement;
    @FXML
    private Button Client;
    @FXML
    private Button Personnel;
    @FXML
    private Button Front;
    @FXML
    private Button Logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ajout.setDisable(true);
        ToggleGroup group = new ToggleGroup();
        tri1.setToggleGroup(group);
        tri2.setToggleGroup(group);

        view.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_numero.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_price.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_qt.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_qt.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            displayAll();
            addButtonToTable();

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Callback<ListView<Fournisseurs>, ListCell<Fournisseurs>> cellFactory = new Callback<ListView<Fournisseurs>, ListCell<Fournisseurs>>() {

            @Override
            public ListCell<Fournisseurs> call(ListView<Fournisseurs> l) {
                return new ListCell<Fournisseurs>() {

                    @Override
                    protected void updateItem(Fournisseurs item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNom());
                        }
                    }
                };
            }
        };

        id_fournisseur.setButtonCell(cellFactory.call(null));
        id_fournisseur.setCellFactory(cellFactory);
        //remplissage du ServiceProduct
        ServiceStock us = new ServiceStock();
        List<Fournisseurs> arr = new ArrayList<>();
        try {
            arr = us.findFournisseur();
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Fournisseurs u : arr) {
            String name = u.getNom();
            int id = u.getId();

            id_fournisseur.getItems().add(u);
        }
        id_fournisseur.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || StockName.getText().length() == 0 || StockNum.getText().length() == 0 || StockQT.getText().length() == 0 || StockPrice.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        StockName.textProperty().addListener((ov, oldValue, newValue) -> {
            if (id_fournisseur.getValue() == null || StockName.getText().length() == 0 || StockNum.getText().length() == 0 || StockQT.getText().length() == 0 || StockPrice.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        StockNum.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(StockNum.getText(), StockNum.getText().length()) == false || OnlyNumber(StockQT.getText(), StockQT.getText().length()) == false || OnlyNumber(StockPrice.getText(), StockPrice.getText().length()) == false || id_fournisseur.getValue() == null || StockName.getText().length() == 0 || StockNum.getText().length() == 0 || StockQT.getText().length() == 0 || StockPrice.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        StockQT.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(StockNum.getText(), StockNum.getText().length()) == false || OnlyNumber(StockQT.getText(), StockQT.getText().length()) == false || OnlyNumber(StockPrice.getText(), StockPrice.getText().length()) == false || id_fournisseur.getValue() == null || StockName.getText().length() == 0 || StockNum.getText().length() == 0 || StockQT.getText().length() == 0 || StockPrice.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        StockPrice.textProperty().addListener((ov, oldValue, newValue) -> {
            if (OnlyNumber(StockNum.getText(), StockNum.getText().length()) == false || OnlyNumber(StockQT.getText(), StockQT.getText().length()) == false || OnlyNumber(StockPrice.getText(), StockPrice.getText().length()) == false || id_fournisseur.getValue() == null || StockName.getText().length() == 0 || StockNum.getText().length() == 0 || StockQT.getText().length() == 0 || StockPrice.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
    }

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

    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>>() {
            @Override
            public TableCell<Stock, Void> call(final TableColumn<Stock, Void> param) {
                final TableCell<Stock, Void> cell = new TableCell<Stock, Void>() {

                    private final Button delete = new Button("");

                    private final HBox pane = new HBox(delete);

                    {

                        Image btn_delete = new Image(getClass().getResourceAsStream("delete.png"));

                        delete.setGraphic(new ImageView(btn_delete));

                        delete.setMaxSize(10, 10);

                        final Tooltip tooltip = new Tooltip();
                        tooltip.setText("supprimer ");
                        delete.setTooltip(tooltip);
                        final Tooltip tooltip2 = new Tooltip();

                        delete.setOnAction((ActionEvent event) -> {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce produit?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {

                                ServiceStock sf = new ServiceStock();
                                Stock S = getTableView().getItems().get(getIndex());

                                try {
                                    sf.delete(S.getId());
                                    displayAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        });
                    }

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

    public void displayAll() throws SQLException {

        ServiceStock sS = new ServiceStock();
        List lists = sS.DisplayAll();
        int number = sS.count();
        ObservableList ListStock = FXCollections.observableArrayList(lists);

        view.setItems(ListStock);

        nombre.setText(Integer.toString(number));
        cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));

        cl_numero.setCellValueFactory(new PropertyValueFactory<>("numerof"));
        cl_price.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        cl_qt.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
        cl_four.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        cl_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    public void tri_Nombre() throws SQLException {

        ServiceStock sf = new ServiceStock();
        List listcs = sf.triParQT();

        ObservableList listFormations = FXCollections.observableArrayList(listcs);

        view.setItems(listFormations);

        cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));

        cl_numero.setCellValueFactory(new PropertyValueFactory<>("numerof"));
        cl_price.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        cl_qt.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
        cl_four.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
    }

    @FXML
    public void tri_Titre() throws SQLException {

        ServiceStock sf = new ServiceStock();
        List listcs = sf.triParNom();

        ObservableList listFormations = FXCollections.observableArrayList(listcs);

        view.setItems(listFormations);

        cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));

        cl_numero.setCellValueFactory(new PropertyValueFactory<>("numerof"));
        cl_price.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        cl_qt.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
        cl_four.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
    }

    @FXML
    private void updateName(TableColumn.CellEditEvent edditedCell) throws SQLException {
        ServiceStock S = new ServiceStock();
        Stock personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getNom();
        int id = personSelected.getId();
        String price = personSelected.getPrix_unitaire();
        String qt = personSelected.getQte_prod();

        personSelected.setNom(edditedCell.getNewValue().toString());

        S.update(id, edditedCell.getNewValue().toString(), price, qt);
        displayAll();
    }

    @FXML
    private void updateNumero(TableColumn.CellEditEvent edditedCell) throws SQLException {
        ServiceStock S = new ServiceStock();
        Stock personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getNom();
        int id = personSelected.getId();
        String price = personSelected.getPrix_unitaire();
        String qt = personSelected.getQte_prod();

        S.update(id, edditedCell.getNewValue().toString(), price, qt);
        displayAll();
    }

    @FXML
    private void UpdatePrice(TableColumn.CellEditEvent edditedCell) throws SQLException {
        ServiceStock S = new ServiceStock();
        Stock personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getNom();
        int id = personSelected.getId();
        int price = Integer.parseInt(edditedCell.getNewValue().toString());
        String qt = personSelected.getQte_prod();
        S.update(id, name, edditedCell.getNewValue().toString(), qt);
        displayAll();
    }

    @FXML
    private void UpdateQt(TableColumn.CellEditEvent edditedCell) throws SQLException {

        ServiceStock S = new ServiceStock();
        Stock personSelected = view.getSelectionModel().getSelectedItem();
        String name = personSelected.getNom();
        int id = personSelected.getId();
        String price = personSelected.getPrix_unitaire();
        int qt = Integer.parseInt(edditedCell.getNewValue().toString());
        if (qt == 0) {
            Notifications notificationBuilder = Notifications.create().title("Alert").text("En repture de stock").graphic(null).hideAfter(javafx.util.Duration.seconds(50))
                    .position(Pos.BASELINE_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked on ");

                        }
                    });
            notificationBuilder.showWarning();
            notificationBuilder.show();
        } else {
            S.update(id, name, price, edditedCell.getNewValue().toString());
            displayAll();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (verificationprix && verificationnom) {
            ServiceStock sf = new ServiceStock();
            String price = StockPrice.getText();
            String quantity = StockQT.getText();
            String numero = StockNum.getText();
            // int price1 = Integer.parseInt(price);
            //int QT = Integer.parseInt(quantity);
            //int num = Integer.parseInt(numero);

            System.out.println("fournisseur id   " + id_fournisseur.getValue().getId());
            if (Integer.parseInt(StockQT.getText()) == 0) {
                Notifications notificationBuilder = Notifications.create().title("Alert").text("En repture de stock").graphic(null).hideAfter(javafx.util.Duration.seconds(50))
                        .position(Pos.BASELINE_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("clicked on ");

                            }
                        });
                notificationBuilder.showWarning();
                notificationBuilder.show();
            } else {
                sf.ajouter(new Stock(0, quantity, StockName.getText(), numero, price, id_fournisseur.getValue().getNom(), id_fournisseur.getValue().getId()));

                AlertDialog.showNotification("ajout", "avec succee", AlertDialog.image_checked);
                displayAll();
                StockName.clear();
                StockNum.clear();
                StockQT.clear();
                StockPrice.clear();
            }

        }
    }

    @FXML
    private void ecrire_barre(ActionEvent event) {
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(write_barcode.getText());
            barcode.setI(11.0f);

            String fname = write_barcode.getText();
            barcode.renderBarcode("C:\\Users\\Salma\\Desktop\\3éme\\PI\\FoodApp (2)\\FoodApp" + fname + ".png");

        } catch (Exception e) {

        }
    }

    @FXML
    private void lire_barre(ActionEvent event
    ) {
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
    private void PageProduct(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Product/Product.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageFournisseur(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Fornisseurs/fournisseurs.fxml")));
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

    @FXML
    private void testNom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < StockName.getText().trim().length(); i++) {
            char ch = StockName.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && StockName.getText().trim().length() >= 3) {

            erreurnSom.setText("Nom valide");

            verificationnom = true;
        } else {

            erreurnSom.setText("Il faut au moins 3 caractères");
            verificationnom = false;

        }
    }

    @FXML
    private void testNum(KeyEvent event) {
        if (StockNum.getText().trim().length() >= 3) {
            int nbChar = 0;
            for (int i = 1; i < StockNum.getText().trim().length(); i++) {
                char ch = StockNum.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(StockNum.getText())) {
                erreurSnum.setText("Reference valide");

                verificationnum = true;
            } else {
                erreurSnum.setText("Reference non valide");
                verificationnum = false;

            }

        } else {
            erreurSnum.setText("Il faut au moins 3 chiffres");
            verificationnum = false;
        }
    }

    @FXML
    private void testQuantite(KeyEvent event) {
        if (StockQT.getText().trim().length() >= 2) {
            int nbChar = 0;
            for (int i = 1; i < StockQT.getText().trim().length(); i++) {
                char ch = StockQT.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(StockQT.getText())) {
                erreurSquantité.setText("Quantité valide");

                verificationquantite = true;
            } else {
                erreurSquantité.setText("Quantité non valide");
                verificationquantite = false;

            }

        } else {
            erreurSquantité.setText("Il faut 3 chiffres");
            verificationquantite = false;
        }
    }

    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @FXML
    private void testPrix(KeyEvent event) {
        if (StockPrice.getText().trim().length() >= 1) {
            int nbChar = 0;
            for (int i = 1; i < StockPrice.getText().trim().length(); i++) {
                char ch = StockPrice.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(StockPrice.getText())) {
                erreurSprix.setText("Prix valide");

                verificationprix = true;
            } else {
                erreurSprix.setText("Prix non valide");
                verificationprix = false;

            }

        } else {
            erreurSprix.setText("Il faut au moins 1 chiffres");
            verificationprix = false;
        }
    }

    @FXML
    private void statS(ActionEvent event) throws IOException {
        try {

            String query = "select qte_prod ,id from stocks";
            prepared = con.prepareStatement(query);
            res = prepared.executeQuery();

            JDBCCategoryDataset dataset = new JDBCCategoryDataset(con, query);
            JFreeChart chart = ChartFactory.createBarChart("statistiques des stocks ", "quantité", "id stock", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Statistiques Stock", chart);
            frame.setVisible(true);
            frame.setSize(400, 650);
            System.out.println(dataset);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, res);
        }
    }

    @FXML
    private void PageLivreur(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Livreur/AfficherLivreurs.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageCommande(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Commande/AfficherCommande.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void PageReponse(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reponse/afficherreponse.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageReclamation(ActionEvent event)
            throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/reclamation/afficherreclamation.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PageEquipe(ActionEvent event) {
    }

    @FXML
    private void PageEquipement(ActionEvent event) {
    }

    @FXML
    private void PageClient(ActionEvent event) {
    }

    @FXML
    private void PagePersonnel(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }
}
