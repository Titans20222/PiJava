/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Models.Evenement;
import Models.Reservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user1
 */
public class FactureFXMLController implements Initializable {
Reservation r1=new Reservation();
Evenement e1=new Evenement();
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbemail;

    @FXML
    private Label lbevent;

    @FXML
    private Label lbnbplaces;

    @FXML
    private Label lbnumtel;

    @FXML
    private Label lbprixu;

    @FXML
    private Label lbtotale;    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
public void recuperer(Evenement e, Reservation r){

    System.out.println(r.getAdresse()+r.getId()+r.getNbrplaces()+r.getNumtel());
r1=r;
e1=e;
remplir();
//affreservationParEvenement(id);
System.out.println(e1.toString());
   
}    
public void remplir(){
lbemail.setText(r1.getAdresse());
lbnbplaces.setText(Integer.toString(r1.getNbrplaces()));
lbnumtel.setText(Integer.toString(r1.getNumtel()));
lbprixu.setText(Float.toString(e1.getPrix()));
lbtotale.setText(Float.toString(e1.getPrix()*r1.getNbrplaces()));
lbevent.setText(e1.toString());
}
}
