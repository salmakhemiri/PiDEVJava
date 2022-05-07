/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Fornisseurs;

import Entites.Fournisseurs;
import GUI.Back.Category.CategoryController;
import Service.ServiceFournisseur;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;



/**
 * FXML Controller class
 *
 * @author Salma
 */
public class FournisseursController implements Initializable {

    ServiceFournisseur Four = new ServiceFournisseur();

    ObservableList<Fournisseurs> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Fournisseurs> view;
    @FXML
    private TableColumn<Fournisseurs, String> cl_name;
    @FXML
    private TableColumn<Fournisseurs, String> cl_num;
    @FXML
    private TableColumn<Fournisseurs, String> cl_desig;
    @FXML
    private TableColumn<Fournisseurs, String> cl_email;
    @FXML
    private TextField FourName;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndel;
    @FXML
    private TextField FourNum;
    @FXML
    private TextField FourEmail;
    @FXML
    private TextField fourDes;
    @FXML
    private Button btnstock;
    @FXML
    private Button btnOrder;
    @FXML
    private ImageView display;
    @FXML
    private Label erreurname;
    @FXML
    private Label erreurnum;
    @FXML
    private Label erreurmail;
    @FXML
    private Label erreurdesig;
       private boolean verificationname;
     private boolean verificationnum;
 private boolean verificationmail;
 
 private boolean verificationdesiign;
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
        addbtn.setDisable(true);
        init();
        view.setEditable(true);
        cl_name.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_desig.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_num.setCellFactory(TextFieldTableCell.forTableColumn());
        cl_email.setCellFactory(TextFieldTableCell.forTableColumn());

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        FourName.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });

        FourNum.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() != 8 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });

        FourEmail.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
        fourDes.textProperty().addListener((ov, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(FourEmail.getText());
            if (matcher.matches() == false || FourName.getText().length() == 0 || FourNum.getText().length() == 0 || FourEmail.getText().length() == 0 || fourDes.getText().length() == 0) {
                addbtn.setDisable(true);
            } else {
                addbtn.setDisable(false);
            }

        });
    }

    public void init() {
        try {
            oblist = (ObservableList<Fournisseurs>) Four.afficheFournisseur();

            cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cl_num.setCellValueFactory(new PropertyValueFactory<>("numero"));
            cl_desig.setCellValueFactory(new PropertyValueFactory<>("designation"));
            cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            view.setItems(oblist);

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void ajoutFour(ActionEvent event) throws IOException {
if(  verificationmail && verificationname && verificationnum)
    {
        try {
            Fournisseurs f = new Fournisseurs(0, FourName.getText(), FourNum.getText(), fourDes.getText(), 0, FourEmail.getText());

            Four.ajouter(f);
            init();
            clearAll();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }

    private void clearAll() {

        FourName.setText("");
        FourNum.setText("");
        FourEmail.setText("");
        fourDes.setText("");

    }

    // updates 
    @FXML
    private void UpdateName(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, edditedCell.getNewValue().toString(), num, designation, email);
    }

    @FXML
    private void UpdateNum(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = edditedCell.getNewValue().toString();

        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, designation, email);
    }

    @FXML
    private void UpdateDesignation(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, edditedCell.getNewValue().toString(), email);
    }

    @FXML
    private void UpdateEmail(CellEditEvent edditedCell) throws SQLException {
        Fournisseurs personSelected = view.getSelectionModel().getSelectedItem();
        int id = personSelected.getId();
        String name = personSelected.getNom();
        String numero = personSelected.getNumero();
        int num = Integer.parseInt(numero);
        String designation = personSelected.getDesignation();
        String email = personSelected.getEmail();
        personSelected.setNom(edditedCell.getNewValue().toString());

        Four.update(id, name, num, designation, edditedCell.getNewValue().toString());
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Fournisseurs f = view.getSelectionModel().getSelectedItem();
        if (!f.equals(null)) {
            Four.delete(f.getId());
        }
        init();
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
    private void PageStock(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Stock/Stock.fxml")));
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
    private void Front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ScreenShot(ActionEvent event) throws IOException {
        try {
           Robot robot = new Robot () ;
           //Rectangle rectangle= new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
          Rectangle rectangle = new Rectangle(0, 0, 800, 500) ;
            BufferedImage image = robot.createScreenCapture(rectangle);
            Image myImage = SwingFXUtils.toFXImage(image, null);
            
            ImageIO.write(image, "jpg", new File("out.jpg")) ;
           
           display.setImage(myImage);

        } catch (AWTException ex) {
            Logger.getLogger(FournisseursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void testname(KeyEvent event) {
           int nbNonChar = 0;
            for (int i = 1; i < FourName.getText().trim().length(); i++) {
                char ch = FourName.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && FourName.getText().trim().length() >=3) {
           
            erreurname.setText("Nom valide");
            
            verificationname = true;
            } else {
            
              erreurname.setText("Il faut au moins 3 caracteres");
              verificationname= false;

            }
    }
public boolean isNumeric(String str){
        if(str==null){
            return false;
        }
        try
        {
            int x=Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    @FXML
    private void testnum(KeyEvent event) {
        if (FourNum.getText().trim().length() == 8 ){
            int nbChar = 0;
            for (int i = 1; i < FourNum.getText().trim().length(); i++) {
                char ch = FourNum.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(FourNum.getText())) {
                erreurnum.setText("Tel valide");
                 
                verificationnum= true;
            } else {           
                erreurnum.setText("Tel non valide");
                verificationnum = false;

            }

        } else {
            erreurnum.setText("Il faut 8 chiffres");
            verificationnum = false;
        }
    }

    @FXML
    private boolean testmail(KeyEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (FourEmail.getText() == null) {
            return false;
        }

        if (pat.matcher(FourEmail.getText()).matches() == false) {
            verificationmail = false;
            
            erreurmail.setText("Veuillez verifier la forme **@*.**");
            return false;
//            

        } else {
            
             erreurmail.setText("Mail valide");
             verificationmail = true;
        }
        return true;
    }

    @FXML
    private void testdesig(KeyEvent event) {
          int nbNonChar = 0;
            for (int i = 1; i < fourDes.getText().trim().length(); i++) {
                char ch = fourDes.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && fourDes.getText().trim().length() >=3) {
           
            erreurdesig.setText("Nom valide");
            
            verificationdesiign = true;
            } else {
            
              erreurdesig.setText("Il faut au moins 3 caracteres");
              verificationdesiign= false;

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
    private void PageCommande(ActionEvent event)throws IOException {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Commande/AfficherCommande.fxml")));
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void PageReclamation(ActionEvent event) {
    }

    @FXML
    private void PageReponse(ActionEvent event) {
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
