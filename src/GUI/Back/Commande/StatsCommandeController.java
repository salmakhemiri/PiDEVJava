/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Back.Commande;

import Service.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author oubei
 */
public class StatsCommandeController implements Initializable {

    @FXML
    private PieChart Piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        {
            CommandeService l = new CommandeService();
            Piechart.setTitle("etat: \n           (0 non livrée) \n    (1 livrée)");
            try {
                Piechart.getData().setAll(new PieChart.Data("non livrée", l.Recherche2()), new PieChart.Data("livrée", l.Recherche3())
                );
                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(StatsCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
