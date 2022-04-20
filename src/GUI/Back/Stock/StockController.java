/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Stock;

import Entities.Fournisseurs;
import Entities.Stock;
import Service.ServiceStock;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Salma
 */
public class StockController implements Initializable {

    ServiceStock Four = new ServiceStock();
    Stock S = null;
    ObservableList<Fournisseurs> oblist = FXCollections.observableArrayList();
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
    private Button Stock;
    @FXML
    private Button Fournisseurs;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Button logout;

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

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{8}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

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
        S.update(id, name, price, edditedCell.getNewValue().toString());
        displayAll();
        Notifications notificationBuilder = Notifications.create().title("Alert").text("En repture de stock").graphic(null).hideAfter(javafx.util.Duration.seconds(50))
                .position(Pos.BASELINE_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on ");

                    }
                });
        notificationBuilder.showWarning();
        notificationBuilder.show();
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        ServiceStock sf = new ServiceStock();
        String price = StockPrice.getText();
        String quantity = StockQT.getText();
        String numero = StockNum.getText();
        // int price1 = Integer.parseInt(price);
        //int QT = Integer.parseInt(quantity);
        //int num = Integer.parseInt(numero);

        System.out.println("fournisseur id   " + id_fournisseur.getValue().getId());
        sf.ajouter(new Stock(0, quantity, StockName.getText(), numero, price, id_fournisseur.getValue().getNom(), id_fournisseur.getValue().getId()));
        //controle de saisie sur num
        if (validatePhoneNumber(StockNum.getText()))
        //notif sur quantité stock 
        {
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
            }
        }

        JOptionPane.showMessageDialog(null, "Ajout effectué");
        displayAll();
        StockName.clear();
        StockNum.clear();
        StockQT.clear();
        StockPrice.clear();

    }

    // changement de page
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

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Jobs/Jobs.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
