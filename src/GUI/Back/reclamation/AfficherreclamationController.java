/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.reclamation;

import Entites.Reclamation;
import Service.ReclamationService;
import foodapp.MainClass;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author DH Farouk
 */
public class AfficherreclamationController implements Initializable {
    public static int id_reclamation_reponse;

int idd=0;

    @FXML
    private ListView<Reclamation> id_list;
    
    @FXML

    ObservableList <Reclamation> Reclamation = FXCollections.observableArrayList();
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_description;
    @FXML
    private TextField id_etat;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_supprimer;
    @FXML
    private Button id_sendmail;
    @FXML
    private Button id_reponse;
    @FXML
    private Button id_stat;
    @FXML
    private Button Product;
    @FXML
    private Button category;
    @FXML
    private Button Stock;
    @FXML
    private Button Fournisseurs;
    @FXML
    private Button Commande;
    @FXML
    private Button Livreur;
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
        if(id_list.hasProperties() ){

        idd=id_list.getSelectionModel().getSelectedItem().getId();
        id_reclamation_reponse=idd; 
            System.out.println(id_reclamation_reponse);}
        
        

        try {
            afficher();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO
    }    
    public void afficher() throws SQLException{
        ReclamationService sr = new ReclamationService();
        Reclamation=FXCollections.observableArrayList(sr.afficher());
        id_list.setItems(Reclamation);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Reclamation r = new  Reclamation ();
          ReclamationService sr = new ReclamationService();
               
        r.setEtat(Integer.parseInt (id_etat.getText()));
        r.setDescription(id_description.getText());
        
         r.setNom(id_nom.getText());
        r.setEmail(id_email.getText());
        
        r.setId(id_list.getSelectionModel().getSelectedItem().getId());
                sr.Modifier (r);
                
                afficher();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        ReclamationService sr = new ReclamationService ();
        Reclamation p = new Reclamation();
        p.setId(id_list.getSelectionModel().getSelectedItem().getId());
        sr.supprimer(p);
       
        afficher();
    }

   

    @FXML
    private void fill(MouseEvent event)  throws ParseException
{
        
        Reclamation p= id_list.getSelectionModel().getSelectedItem();
        id_nom.setText(p.getNom());
        id_etat.setText(String.valueOf(p.getEtat()));
        id_description.setText(p.getDescription());
        id_email.setText(p.getEmail());
        
     


        
        
        
}

    public  static void sendmail(String recepient) 
        throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "farouk.douiri@esprit.tn";
        //Your gmail password
        String password = "ihqjtceaduhrmhmv";

        //Create a session with account credentials
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
 
            
           
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
      Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        
           
            
            try {
                 Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress( myAccountEmail));
                 message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(" reclamation ");
            message.setText("votre reclamation est traiter merci pour votre patience  ");
           
            return message;
                
           
            } catch (Exception ex) {
                Logger.getLogger(AfficherreclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
            
    }

    @FXML
    private void sendmail(ActionEvent event) throws Exception {
        sendmail("arwa.douiri@esprit.tn");
    }

    @FXML
    private void go_reponse(ActionEvent event) {
        
        
        if(id_list.hasProperties() )
        {    idd=id_list.getSelectionModel().getSelectedItem().getId();
            System.out.println(idd);
            
            id_reclamation_reponse=idd;
            
        System.out.println(id_reclamation_reponse);
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Back/reponse/ajouterreponse.fxml"));
                Stage stage =new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Participants");
                stage.show();
        } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void stat(ActionEvent event) {
        try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("statreclamation.fxml"));
                Stage stage =new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("statistique");
                stage.show();
        } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
    }

    @FXML
    private void PageProduct(ActionEvent event) {
    }

    @FXML
    private void PageCategory(ActionEvent event) {
    }

    @FXML
    private void PageStock(ActionEvent event) {
    }

    @FXML
    private void PageFournisseur(ActionEvent event) {
    }

    @FXML
    private void PageCommande(ActionEvent event) {
    }

    @FXML
    private void PageLivreur(ActionEvent event) {
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
    private void Front(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }

    
   
        
        
    
    
    


    

        
        
        
    }
        
        
        
        
        
        
        
   


   