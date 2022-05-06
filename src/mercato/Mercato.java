/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato;

import java.sql.Connection;
import mercato.DAO.Classe.ReclamationService;
import mercato.Model.Reclamation;
import mercato.Technique.DataBase;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author ASUS
 */
public class Mercato {

    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) throws SQLException {
        
        /*/ TODO code application logic here
        Connection cnx = DataBase.getInstance().getConnection();
        Timestamp d;
        d = new Timestamp(System.currentTimeMillis());
        Reclamation r1 = new Reclamation("Reclammmmmm", "Prob", "Bug", 1, d);
        Reclamation r2 = new Reclamation("Reclamation", "Probleme", "Bug", 2, d);
        Reclamation r3 = new Reclamation("validation", "pidev", "Bug", 3, d);
        ReclamationService ps = new ReclamationService();
        List<Reclamation> rt = new ArrayList<>();
        List<Reclamation> rr = new ArrayList<>();
        Reclamation r = new Reclamation(2,1);
        //ps.ajouter(r3);
        //ps.ajouter(r2);
        //ps.afficher().forEach(System.out::println);
        
        //System.out.println("\n                          ******************************************             \n");
        
        //ps.supprimer(new Reclamation(42));
        //ps.modifier(new Reclamation (51,"Reclam","Probbb","le systeme bug",3,d));
        //ps.afficher().forEach(System.out::println); 
        
       // System.out.println("\n                          ******************************************             \n");
        
        //rr = ps.rechercher(r);  
        //System.out.println(rr);
        
        //System.out.println("\n                          ******************************************             \n");
        
        //rt = ps.trier();
        //System.out.println(rt);*/
        
    //}
    
}
