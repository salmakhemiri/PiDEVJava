/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Users;
import GUI.Front.User.InscriptionController;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class UserService {
    
     Connection con = DataBase.getInstance().getConnection();
    private Statement stmt;

    
    public void ajouter(Users t) {
          PreparedStatement psCheckUserExists = null;
          PreparedStatement pst = null;
          ResultSet resultSet = null;
          
          try{
          psCheckUserExists = con.prepareStatement("SELECT * FROM user WHERE email =?");
          psCheckUserExists.setString(1, t.getEmail());
          resultSet = psCheckUserExists.executeQuery();
          if (resultSet.isBeforeFirst()){
          // the user already exists
          System.out.println("user already exists");
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setContentText("Cette adresse email est deja utilisée");
          alert.show();
          }else {
          String requete = "INSERT INTO user (email,nom,prenom,password,roles,num_tel) VALUES (?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, t.getEmail());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getPassword());
            pst.setString(5, t.getRoles());
            pst.setInt(6, t.getNum_tel());
            
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");
            InscriptionController IC = new InscriptionController();
            IC.sendEmail(t.getEmail());
            //DBUtils.changeScene(event, "LoggedIn.fxml", "welcome", t);
            //DBUtils.changeScene(event, "LoggedIn.fxml", "welcome", t);
          }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (MessagingException ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }finally {
          if(resultSet != null){
          try{
          resultSet.close();
          }catch (SQLException e){
          e.printStackTrace();
          }
          }
          if (psCheckUserExists != null){
           try{
          psCheckUserExists.close();
          }catch (SQLException e){
          e.printStackTrace();
          }
          }
          if (pst != null){
           try{
          pst.close();
          }catch (SQLException e){
          e.printStackTrace();
          }
          }
          }
    }
    
    
    // Login function
    /*public void Login (ActionEvent event, String email, String password){
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    try{
    ps = con.prepareStatement("SELECT * FROM user WHERE email = ? ");
    ps.setString(1, email);
    resultSet = ps.executeQuery();
    
    if (!resultSet.isBeforeFirst()){
    System.out.println("user not found");
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("provided credentials are incorrect");
    alert.show();
    }else {
    while (resultSet.next()){
    String rPassword = resultSet.getString("password");
    String rNom = resultSet.getString("nom");
    String rPrenom = resultSet.getString("prenom");
    String rRoles = resultSet.getString("roles");
    int rNumTel = resultSet.getInt("num_tel");
    if (rPassword.equals(password)){
    Users user = new Users(email, rNom, rPrenom, password, rRoles, rNumTel);
    System.out.println("user logged in");
    //DBUtils.changeScene(event, "LoggedIn.fxml", "logged in!", user);
    } else {
    System.out.println("passwords did not match");
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("provided credentials not correct!");
    alert.show();
    }}}
    }catch (SQLException e){
    e.printStackTrace();}
    finally {
    if(resultSet != null){
    try{
    resultSet.close();
    }catch (SQLException e){
    e.printStackTrace();
    }
    }
    if (ps != null){
    try{
    ps.close();
    }catch (SQLException e){
    e.printStackTrace();
    }}
    }
    }*/
    
   /* public Boolean validerLogin(String email, String password) {
        Boolean valid = null;
       String verifyLogin = "SELECT count(1) FROM user WHERE email='"+ email +"' AND password = '"+ password +"'";
       try {
           Statement statement = con.createStatement();
          
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           while(queryResult.next()){
           if (queryResult.getInt(1)== 1){
           valid= true;
           } else {
           valid= false;
           }
           }  
       }catch (Exception e){
       e.printStackTrace();
       e.getCause();
       }
       return valid;
    }*/
    
        public boolean chercherUser(String email, String password) throws SQLException {

        boolean exist=false;
        Statement stm = con.createStatement();
        String req = "select * from user ";
        ResultSet rs = stm.executeQuery(req);
        while(rs.next()){
            if((rs.getString("email").equals(email))&&rs.getString("password").equals(password)){
                exist=true;
        //user u =new user(rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("roles"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),rs.getInt("id"),rs.getInt("phone"),rs.getDate("birth_date"));
        }}
            
       return exist;
    }
        
        public Users donnerUser(String email, String password) throws SQLException {

       Users u =new Users();
        Statement stm = con.createStatement();
        String req = "select * from user where email='" + email + "'and password='" + password + "'";
        ResultSet rs = stm.executeQuery(req);
        if(rs.next()){
            
         u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setNum_tel(rs.getInt("num_tel"));
         }   
      return u;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement prd = con.prepareStatement("DELETE FROM user WHERE id='" + id + "' ;");
        prd.executeUpdate();
        JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succées");
        return true;
    }

    public boolean updatePersonnel(int id ,String nom, String prenom, String email, int num) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE user SET nom= '" + nom + "' , prenom= '" + prenom + "' ,email= '" 
                + email + "'  ,num_tel= '" + num + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Personnel modifié avec succées");
        pre.executeUpdate();

        return true;
    }

    public ArrayList<Users> afficherClient() {
         ArrayList<Users> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user where roles='ROLE_CLIENT'";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            Users user;
            while (rs.next()) {
            
                user=new Users(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7));
                list.add(user);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<Users> afficherPersonnel() throws SQLException {
        ObservableList<Users> oblist = FXCollections.observableArrayList();
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from user where roles='ROLE_PERSONNEL'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            int numTel = rs.getInt("num_tel");
            String pass = rs.getString("password");
            String role = rs.getString("roles");
            Users u = new Users(id, email, nom, prenom, pass,role, numTel);

            oblist.add(u);
        }
        return oblist;
    }
    
        
        public List<Users> afficherAdmin() {
         List<Users> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user where roles=ROLE_ADMIN";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }         

}
