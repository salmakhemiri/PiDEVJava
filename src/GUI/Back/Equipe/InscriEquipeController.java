/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Equipe;

import com.itextpdf.text.DocumentException;
import Entites.Equipe;
import Service.EquipeService;
import Utils.SMSAPI;
import Utils.ServicePdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.FileOutputStream;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author 21626
 */
public class InscriEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> equipeTable;
    @FXML
    private TableColumn<Equipe,String> nomColumn;
    @FXML
    private TableColumn<Equipe,String> prenomColumn;
            
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfmetier;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsup;
    
    private InterfaceEquipe mainApp;

    @FXML
    private Button btnpdf;
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
    @FXML
    private Label erreurname;
    @FXML
    private Label erreurprenom;
    
   private boolean verificationnom;
    private boolean verificationprenom;
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            EquipeService equipeService = new EquipeService();
        equipeTable.setItems(equipeService.afficher());
        System.out.println(equipeService.afficher());
                
        nomColumn.setCellValueFactory(value -> value.getValue().getNom());
        prenomColumn.setCellValueFactory(value -> value.getValue().getPrenom());
        equipeTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showDetails(newValue));
    }    
    
    public void setMainApp(InterfaceEquipe mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void Ajouter(ActionEvent event) throws AWTException {
      if (verificationnom && verificationprenom ) {
        String nom = tfnom.getText();
       String prenom = tfprenom.getText();
      int age = Integer.parseInt(tfage.getText());
       String metier = tfmetier.getText();
    
    Equipe e = new Equipe();
    e.setNom(new SimpleStringProperty(nom));
    e.setPrenom(new SimpleStringProperty(prenom));
    e.setAge(age);
    e.setMetier(new SimpleStringProperty(metier));
    EquipeService es = new EquipeService();
    es.ajouterEquipe(e);
    SMSAPI.sendSMS("+21626629623", "Equipe Crée");
SystemTray tray = SystemTray.getSystemTray();

                //If the icon is a file
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                //Alternative (if the icon is on the classpath):
                //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);

                JOptionPane.showMessageDialog(null, "Equipe ajoutée avec succès ");
    EquipeService equipeService = new EquipeService();
        equipeTable.setItems(equipeService.afficher());
   
    }
 }
 
        @FXML
    private void Modifier(ActionEvent event) {
          String nom = tfnom.getText();
       String prenom = tfprenom.getText();
       int age = Integer.parseInt(tfage.getText() );
       String metier = tfmetier.getText();
       
         Equipe e = new Equipe();
        int selectedIndex = equipeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Equipe equipe = equipeTable.getSelectionModel().getSelectedItem();
              EquipeService EquipeService = new EquipeService();
             equipe.setNom(new SimpleStringProperty(tfnom.getText()));
          equipe.setPrenom(new SimpleStringProperty(tfprenom.getText()));
          equipe.setAge(Integer.parseInt(tfage.getText()));
          equipe.setMetier(new SimpleStringProperty(tfmetier.getText()));
            
              EquipeService es = new EquipeService();
            es.Modifier(equipe);
            equipeTable.setItems(EquipeService.afficher());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equipe Selected");
            alert.setContentText("Please select a Equipe in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int selectedIndex = equipeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = equipeTable.getSelectionModel().selectedItemProperty().getValue().getId();
            EquipeService EquipeService = new EquipeService();
            EquipeService.supprimer(id);
            equipeTable.setItems(EquipeService.afficher());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Equipe Selected");
            alert.setContentText("Please select an equipe in the table.");

            alert.showAndWait();
        }
    }
    
 
   
    @FXML
    private void pdf(ActionEvent event) throws IOException {

        try {
            String file_name = "C:\\Users\\Salma\\Desktop\\3éme\\PI\\FoodApp (2)\\FoodApp\\PdfEquipe\\equipe.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            document.add(new Paragraph("Liste des equipes "));
            document.add(new Paragraph("---------------------------------- "));
            //table
            PdfPTable table = new PdfPTable(5);
            PdfPCell cell1 = new PdfPCell(new Phrase("id"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Phrase("nom"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Phrase("prenom"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell4 = new PdfPCell(new Phrase("age"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell5 = new PdfPCell(new Phrase("metier"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);

            for (int i = 0; i < equipeTable.getItems().size(); i++) {
                table.addCell("" + equipeTable.getItems().get(i).getId());
                table.addCell("" + equipeTable.getItems().get(i).getNom());
                table.addCell("" + equipeTable.getItems().get(i).getPrenom());
                table.addCell("" + equipeTable.getItems().get(i).getAge());
                table.addCell("" + equipeTable.getItems().get(i).getMetier());


            }
            document.add(table);
            document.close();
            Desktop.getDesktop().open(new File("C:\\Users\\Salma\\Desktop\\3éme\\PI\\FoodApp (2)\\FoodApp\\PdfEquipe\\equipe.pdf"));

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void showDetails(Equipe newValue) {
        if (newValue != null){
           tfprenom.setText(newValue.getPrenom().get());
           tfnom.setText(newValue.getNom().get());
           tfmetier.setText(newValue.getMetier().get());
           tfage.setText(String.valueOf(newValue.getAge()));
        }else{
            tfprenom.setText("");
            tfnom.setText("");
            tfmetier.setText("");
            }
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
    private void PageStock(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Stock/Stock.fxml")));
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
    private void PageCategory(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Category/category.fxml")));
        stage.setScene(scene);
        stage.show();
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
    private void PagePersonnel(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/User/ListePersonnel.fxml")));
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void Logout(ActionEvent event) throws IOException {
        // ajouter message de confirmation 

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Voulez-vous vraiment vous deconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/login.fxml")));
            stage.setScene(scene);
            stage.show();

        }

    }
 @FXML
    private void PageClient(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/User/ListeClients.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PageEquipement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/Equipement/InscriEquippement.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tesrName(KeyEvent event) {
            int nbNonChar = 0;
        for (int i = 1; i < tfnom.getText().trim().length(); i++) {
            char ch = tfnom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfnom.getText().trim().length() >= 3) {

            erreurname.setText("Nom valide");

            verificationnom = true;
        } else {

            erreurname.setText("Il faut au moins 3 caracteres");
            verificationnom = false;

        }
    }

    @FXML
    private void testPrenom(KeyEvent event) {
          int nbNonChar = 0;
        for (int i = 1; i < tfprenom.getText().trim().length(); i++) {
            char ch = tfprenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfprenom.getText().trim().length() >= 3) {

            erreurprenom.setText("Prenom valide");

            verificationprenom = true;
        } else {

            erreurprenom.setText("Il faut au moins 3 caracteres");
            verificationprenom = false;

        }
    }

       
}

