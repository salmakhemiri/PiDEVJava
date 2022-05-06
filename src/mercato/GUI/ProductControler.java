/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mercato.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mercato.DAO.Classe.MarketPlaceService;
import mercato.DAO.Interface.MarketPlaceInterface;
import mercato.Model.Product;
import static mercato.Technique.ConfigurationJava.productsPath;

/**
 * FXML Controller class
 *
 * @author PC-Yassine
 */
public class ProductControler implements Initializable {

    @FXML
    private AnchorPane GlobalAnchorID;
    @FXML
    private Label nom;
    @FXML
    private ImageView imgv;
    @FXML
    private ImageView next;
    @FXML
    private ImageView back;
    @FXML
    private Button buttonBuy;
    @FXML
    private Label prix;
    @FXML
    private Label descr;
    @FXML
    private Label available;
    @FXML
    private Label Date;
    @FXML
    private Label quantity;
    public static int id1;
    
    public Image image1;
    public Image image2;
    public Image image3;
    public Image image4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         int i;
         i=MarketplaceControler.idl;
        
        Product p= new Product();
        MarketPlaceInterface mps =new MarketPlaceService();
        p = mps.getById(i);
       
        //Image image1;
        image1 = new Image("/mercato/GUI/photos/"+p.getImage1());
        
        try{
        image2 = new Image("/mercato/GUI/photos/"+p.getImage2());
        image3 = new Image("/mercato/GUI/photos/"+p.getImage3());
        image4 = new Image("/mercato/GUI/photos/"+p.getImage4());
        }catch(Exception e){
            
        }
        nom.setText(p.getName());
        imgv.setImage(image1);
        descr.setText(p.getDescription());
        available.setText(p.getState());
        quantity.setText(String.valueOf(p.getQuantity()));
        prix.setText(String.valueOf(p.getPrice()));
        Date.setText(String.valueOf(p.getDate()));
        
        
    }    

    @FXML
    private void nextAction(MouseEvent event) {
        if (imgv.getImage().equals(image1)){
           if (image2!=null) {
               imgv.setImage(image2);
           }else if(image3!=null){
               imgv.setImage(image3);
           }else if(image4!=null){
               imgv.setImage(image4);
           }
        }else if(imgv.getImage().equals(image2)){
            if (image3!=null) {
               imgv.setImage(image3);
           }else if(image4!=null){
               imgv.setImage(image4);
           }
        }else if(imgv.getImage().equals(image3)){
            if (image4!=null) {
               imgv.setImage(image4);
           }
        }else{
            imgv.setImage(image1);
        }
    }

    @FXML
    private void backAction(MouseEvent event) {
        if (imgv.getImage().equals(image1)){
           if (image4!=null) {
               imgv.setImage(image4);
           }else if(image3!=null){
               imgv.setImage(image3);
           }else if(image2!=null){
               imgv.setImage(image2);
           }
        }else if(imgv.getImage().equals(image4)){
            if (image3!=null) {
               imgv.setImage(image3);
           }else if(image2!=null){
               imgv.setImage(image2);
           }
            }else if(imgv.getImage().equals(image3)){
            if (image2!=null) {
               imgv.setImage(image2);
           }
        }else{
            imgv.setImage(image1);
        }

    }

    @FXML
    private void BuyAction(ActionEvent event) {
        
        
    }

    
}
