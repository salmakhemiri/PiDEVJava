/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.GUI;

 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;
import javax.swing.SwingUtilities;
import mercato.DAO.Classe.ReclamationService;
import mercato.Model.Reclamation;


/**
 *
 * @author PC-Yassine
 */
public class Mercato extends Application {

    /**
     * @param args the command line arguments
     */
    
   


        
        // TODO code application logic here
    

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/mercato/GUI/AcceuilUser.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("/mercato/GUI/admin_reclamation.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/
    }
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

