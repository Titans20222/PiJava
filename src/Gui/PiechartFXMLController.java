/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Models.Evenement;
import Models.Reservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user1
 */
public class PiechartFXMLController implements Initializable {
    @FXML
    private AnchorPane p;
    @FXML
    private PieChart pie;
Evenement e= new Evenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    // TODO
    }    
void chart(){
      //  Scene scene = new Scene(new Group());
       // stage.setTitle("Imported Fruits");
        //stage.setWidth(500);
        //stage.setHeight(500);
 try{
 //Parent loder =FXMLLoader.load(getClass().getResource("PiechartFXML.fxml"));  
 //root =(Parent)loder.load();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("reserver", e.getNbplacesreserver()),
                new PieChart.Data("disponible", e.getNbrplaces()));
               // new PieChart.Data("Plums", 10),
                //new PieChart.Data("Pears", 22),
                //new PieChart.Data("Apples", 30));
        pie.setData(pieChartData);
       pie.setTitle("Disponibilité des places pour l'evenement "+e.getNom());
//Scene scene = new Scene(loder);
          //  Stage s =new Stage();
        //    s.setScene(scene);
          //  s.setTitle("-----facture ------");

  // ((Group) scene.getRoot()).getChildren().add(pie);
//s.show();
     }

catch(Exception e ){
e.getStackTrace();
}

        //stage.setScene(scene);
        //stage.show();

}

public void recuperer(Evenement e){
this.e=e;
chart();
}
}