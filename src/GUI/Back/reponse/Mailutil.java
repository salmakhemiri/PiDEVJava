/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.reponse;

import GUI.Back.reclamation.AfficherreclamationController;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DH Farouk
 */
public class Mailutil {
     @FXML
   public   static void sendmail(String recepient) 
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
        
        
        
        
    }
    
    


    
