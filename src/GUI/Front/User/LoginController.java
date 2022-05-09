/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Front.User;

import Entites.Users;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Utils.DataBase;
import java.awt.event.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javax.swing.JOptionPane;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    static Users session;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label forgotPassword;
    @FXML
    private Label LoginMessage;
    @FXML
    private Button btnToInscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        
        UserService us = new UserService();
        if (tfEmail.getText().isEmpty() == false && tfPassword.getText().isEmpty() == false){
        LoginMessage.setText("vous etes en train de se connecter");
        us.Login(event, tfEmail.getText(), tfPassword.getText());
        
        }else {
        LoginMessage.setText("veuillez Remplir tous les champs!");
        }
        }
        });*/

        btnToInscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent tableViewOpportunity = FXMLLoader.load(getClass().getResource("/GUI/Front/User/Inscription.fxml"));
                    Scene tableViewOpportunityScene = new Scene(tableViewOpportunity);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(tableViewOpportunityScene);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

//    private void LoginConnectMouseClicked(MouseEvent event) throws SQLException, IOException {
//        boolean b= false;
//        
//        UserService us= new UserService();
//        Users u =new Users();
//        if(us.chercherUser(tfEmail.getText(), tfPassword.getText()))
//        {
//            
//                u =us.donnerUser(tfEmail.getText(), tfPassword.getText());
//                session=u;
//                
//                Parent tableViewOpportunity=FXMLLoader.load(getClass().getResource("/com/usercrud/gui/ListePersonnel.fxml"));
//                Scene tableViewOpportunityScene=new Scene (tableViewOpportunity);
//                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setScene(tableViewOpportunityScene);
//               
//        
//        }
//        
//        else{
//            System.out.println("saisir vos cordonnées a nouveau");       }
//            /*TrayNotification tray =new TrayNotification();
//            tray.setTitle("Erreur");
//            tray.setMessage("Veuillez saisir vos coordonnées à nouveux");
//            tray.setAnimationType(AnimationType.POPUP);
//            tray.setNotificationType(NotificationType.INFORMATION);
//            tray.showAndWait();}*/
//       
//       
//    }    
    /* @FXML
    private void inscription(ActionEvent event) {
    FirstWindow m = new FirstWindow();
    m.changeScene("Inscription.fxml");
    //m.changeScene("ListePersonnel.fxml");
    }*/
    @FXML
    private void UserLogin(ActionEvent event) throws IOException, SQLException {
        boolean b = false;

        UserService us = new UserService();
        Users u = new Users();
        if (us.chercherUser(tfEmail.getText(), tfPassword.getText())) {
            u = us.donnerUser(tfEmail.getText(), tfPassword.getText());
            session = u;
            JOptionPane.showMessageDialog(null, "Login effectué");

            if (session.getRoles().equals("ROLE_PERSONNEL") || session.getRoles().equals("ROLE_ADMIN")) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Back/User/ListePersonnel.fxml")));
                stage.setScene(scene);
                stage.show();
            } else if (session.getRoles().equals("ROLE_CLIENT")) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/Product/Products.fxml")));
                stage.setScene(scene);
                stage.show();
            }

        } else {
            JOptionPane.showMessageDialog(null, "saisir vos cordonnées a nouveau");
        }

    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front/User/Inscription.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
