/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Fournisseurs;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Salma
 */
public class ServiceFournisseur {

    private Connection con;
    private Statement ste;

    public ServiceFournisseur() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouter(Fournisseurs f) throws SQLException {
        ste = con.createStatement();

        String requeteInsert = "INSERT INTO `food`.`fournisseur` (`id`, `nom`, `numero`, `designation`,`email` , `statu`) VALUES (NULL, '" + f.getNom() + "' ,  '" + f.getNumero() + "' , '" + f.getDesignation() + "' , '" + f.getEmail() + "',0);";
        JOptionPane.showMessageDialog(null, "Fournisseur ajoutée avec succées");
        ste.executeUpdate(requeteInsert);
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement prd = con.prepareStatement("DELETE FROM stocks WHERE fournisseur_id='" + id + "' ;");

        PreparedStatement pre = con.prepareStatement("DELETE FROM fournisseur WHERE id='" + id + "' ;");
        prd.executeUpdate();
        pre.executeUpdate();
        JOptionPane.showMessageDialog(null, "Fournisseur supprimée avec succées");
        return true;
    }

    public boolean update(int id, String nom, int numero, String designation, String email) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE fournisseur SET nom= '" + nom + "' , numero= '" + numero + "' ,designation= '" + designation + "'  ,email= '" + email + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Fournisseur modifié avec succées");
        pre.executeUpdate();

        return true;
    }

    public List<Fournisseurs> readAll() throws SQLException {
        List<Fournisseurs> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fournisseur");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String numero = rs.getString("numero");
            String designation = rs.getString("designation");
            String email = rs.getString("email");

            Fournisseurs f = new Fournisseurs(id, nom, numero, designation, 0, email);
            arr.add(f);
        }
        return arr;
    }

    public ObservableList<Fournisseurs> afficheFournisseur() throws SQLException {
        ObservableList<Fournisseurs> oblist = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fournisseur");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String numero = rs.getString("numero");
            String designation = rs.getString("designation");
            String email = rs.getString("email");
            Fournisseurs f = new Fournisseurs(id, nom, numero, designation, 0, email);

            oblist.add(f);
        }
        return oblist;
    }

    public boolean envoyerMail(String nom, String prénom, String email, String details) {

        try {
            String host = "smtp.gmail.com";
            String user = "salma.khemiri@esprit.tn";
            String pass = "191JFT4590";
            String to = "karray.gargouri@esprit.tn";
            String from = "Offre_fournisseur";
            String subject = "Offre fournisseur";
            String messageText = "Une nouvelle offre de fournisseur :" + nom + " " + prénom + " \n  son email : '" + email + "' \n  il a propossé  \n " + details + "   ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);

            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("message send successfully");
            JOptionPane.showMessageDialog(null, "Email envoyé avec succees ");

            //    transport.close();
            return transport.isConnected();

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return false;

    }

}
