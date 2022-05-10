/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.User;

import Entites.Users;
import Service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.*;
import org.eclipse.persistence.config.ReferenceMode;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.partitioning.PartitioningPolicy;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.eclipse.persistence.exceptions.EclipseLinkException;
import org.eclipse.persistence.exceptions.ExceptionHandler;
import org.eclipse.persistence.exceptions.IntegrityChecker;
import org.eclipse.persistence.exceptions.ValidationException;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.history.AsOfClause;
import org.eclipse.persistence.internal.databaseaccess.Platform;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.eclipse.persistence.platform.database.DatabasePlatform;
import org.eclipse.persistence.platform.server.ServerPlatform;
import org.eclipse.persistence.queries.AttributeGroup;
import org.eclipse.persistence.queries.Call;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.ExternalTransactionController;
import org.eclipse.persistence.sessions.IdentityMapAccessor;
import org.eclipse.persistence.sessions.ObjectCopyingPolicy;
import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.sessions.SessionEventManager;
import org.eclipse.persistence.sessions.SessionProfiler;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.eclipse.persistence.sessions.serializers.Serializer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNumTel;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btninscription;
    @FXML
    private Label sentBoolValue;
    @FXML
    private PasswordField tfConfirmPassword;
    @FXML
    private Label mdpMessage;
    @FXML
    private Button btnToLogin;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    String path = "C:\\Users\\Salma\\Desktop\\3éme\\PI\\FoodApp (2)\\FoodApp\\music\\acceuil.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mediaPlayer.play();
        // TODO
        /* btnToLogin.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/login.fxml")));
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        });*/
    }    

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException, MessagingException {
        String email = tfEmail.getText();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        int numTel = Integer.parseInt(tfNumTel.getText());
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPassword.getText();
        if (!tfEmail.getText().trim().isEmpty()&&!tfNom.getText().trim().isEmpty()&&!tfPrenom.getText().trim().isEmpty()
                &&!tfNumTel.getText().trim().isEmpty()&&!tfPassword.getText().trim().isEmpty()
                &&!tfConfirmPassword.getText().trim().isEmpty()){
            if (password.equals(confirmPassword)){
                Users u = new Users(email,nom,prenom,password,"ROLE_CLIENT",numTel);
                UserService us = new UserService();
                us.ajouter(u);
                
                   Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
        stage.setScene(scene);
        stage.show();
                 sendEmail(tfEmail.getText());
                /* DBUtils dbu = new DBUtils();
                dbu.changeScene(event, "LoggedIn.fxml", "logged in!", u);*/
            } else {
                mdpMessage.setText("Vérifiez la correspondance du mot de passe");
            }}else {
            System.out.println("Veuillez remplir tous les champs");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
        }
    }

    /*@FXML
    private void Login(ActionEvent event) {
    
    DBUtils.changeScene(event, "login.fxml", "Login!", null);
    }*/
public void sendEmail(String recepient) throws MessagingException {
        String toEmail = tfEmail.getText();
        //String from = "imen.hafsaoui@esprit.tn";
        //final String password = "211JFT3711";

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("imen.hafsaoui@esprit.tn", "211JFT3711");
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("imen.hafsaoui@esprit.tn"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Création du compte sur Fast Truck");
        message.setText("Bonjour "+ tfPrenom.getText() +" " + tfNom.getText() +" Votre compte a ete crée avec succées avec le mot de passe:" + tfPassword.getText());
        Transport.send(message);
        //sentBoolValue.setVisible(true);
        System.out.println("Message envoyé");
   }
//
//    @FXML
//    private void ToLogin(ActionEvent event) {
//        
//                        try {
//                    Parent tableViewOpportunity=FXMLLoader.load(getClass().getResource("/com/usercrud/gui/login.fxml"));
//                    Scene tableViewOpportunityScene=new Scene (tableViewOpportunity);
//                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setScene(tableViewOpportunityScene);
//                } catch (IOException ex) {
//                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//    }

     @FXML
    private void ToLogin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/login.fxml")));
        stage.setScene(scene);
        stage.show();
}

    @FXML
    private void Mute(ActionEvent event) {
        mediaPlayer.pause();
    }
    }

    