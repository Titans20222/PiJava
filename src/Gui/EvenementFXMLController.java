/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Models.Evenement;
import Models.Reservation;
import Services.EvenementServices;
import Services.ReservationServices;
import java.io.IOException;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.JSType;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Notifications;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.control.Hyperlink;
/**
 * FXML Controller class
 *
 * @author user1
 */
public class EvenementFXMLController implements Initializable {
int id =0;
Evenement event=new Evenement();
int testClick=0;
    /**
     * Initializes the controller class.
     */
private Parent fxml;
 @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label labelinfo;
     @FXML
    private TextField tflieu;
  @FXML
    private DatePicker datepk;

    @FXML
    private TextField tfnbrp;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprix;
  @FXML
    private Button btnajouter;
    @FXML
    private Button reservation;

    @FXML
    private TextField chercher;


    @FXML
    private TableColumn<Evenement, Date> datetabvue;

    @FXML
    private TableColumn<Evenement, String> lieutabvue;

    @FXML
    private TableColumn<Evenement ,String> nomtabvue;

    @FXML
    private TableColumn<Evenement, Integer> placetabvue;

    @FXML
    private TableColumn<Evenement,Float> prixtabvue;

    @FXML
    private TableView<Evenement> tabvue;
  
 @FXML
    private Hyperlink statlien;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
affEvenement();
//if(testClick==0)
statlien.setVisible(false);

//statlien.setDisable(true);
 }    
     


    @FXML
    void ajouterevenement(ActionEvent event) {
        EvenementServices es=new EvenementServices();   
Evenement e =new Evenement();
//e.setLieux(tflieu.getText());

int test=0;

try{
    int entier = Integer.parseInt(tfnbrp.getText());
  float f =Float.parseFloat(tfprix.getText());
   if(entier>0 && f>0)
test=1;
        System.out.println("Est un int positif!");

}catch(NumberFormatException parEx){System.out.println("N'est pas un int !");
test=0;
}
if(tfnom.getText().isEmpty()||tflieu.getText().isEmpty()|| test==0  )
{

//es.ajouter(new Evenement(0, tfnom.getText(), , Integer.parseInt( tfprix.getText()), tflieu.getText(), Integer.parseInt(tfnbrp.getText()) );


        Notifications ntb1=Notifications.create()
.title("champs invalide")
.text("veuillez remplir les champs correctement")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.CENTER)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");
}


}
);
ntb1.showConfirm();
}else{
e.setNom(tfnom.getText());
e.setNbrplaces(Integer.parseInt(tfnbrp.getText()));
e.setPrix(Float.parseFloat( tfprix.getText()));
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date D =Date.from(datepk.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
e.setD(dateFormat.format(D));
e.setLieux(tflieu.getText());
es.ajouter(e);
Notifications ntb=Notifications.create()
.title("ajouter")
.text("jwk bhy")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.TOP_RIGHT)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");
}


}
);
ntb.showConfirm();

affEvenement();
//Notification

} }
 @FXML
    void modifierevenement(ActionEvent event) {
        EvenementServices es=new EvenementServices();   
Evenement e =new Evenement();

int test=0;

try{
    int entier = Integer.parseInt(tfnbrp.getText());
float f =Float.parseFloat(tfprix.getText());
        if(entier>0 && f>0 )
test=1;
        System.out.println("valid!");

}catch(NumberFormatException parEx){System.out.println("non valid !");
test=0;
}
if(tfnom.getText().isEmpty()||tflieu.getText().isEmpty()|| test==0 )
{
        Notifications ntb1=Notifications.create()
.title("champs invalide")
.text("veuillez remplir les champs correctement")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.CENTER)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");
}


}
);
ntb1.showConfirm();
}else{

//e.setLieux(tflieu.getText());
e.setNom(tfnom.getText());
e.setNbrplaces(Integer.parseInt(tfnbrp.getText()));
e.setPrix(Float.parseFloat( tfprix.getText()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date D =Date.from(datepk.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
e.setD(dateFormat.format(D));
e.setLieux(tflieu.getText());
e.setId(id);
es.modifier(e);
        Notifications ntb=Notifications.create()
.title("ajouter")
.text("jwk bhy")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.TOP_RIGHT)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");
}

}
);
ntb.showConfirm();
viderleschamp();
}
affEvenement();
     System.out.println(e.toString());
}



void affEvenement(){
 EvenementServices es=new EvenementServices();  
    ObservableList<Evenement>evenements=es.afficher();
lieutabvue.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieux"));
nomtabvue.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
prixtabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Float>("prix"));
placetabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("nbrplaces"));

datetabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
tabvue.setItems(evenements);
tabvue.setOnMouseClicked(e->{
statlien.setVisible(true);
    System.out.println("bien click");
  //  System.out.println((tabvue.getSelectionModel().getSelectedItems().get(0).getNom()));
tfnom.setText(tabvue.getSelectionModel().getSelectedItems().get(0).getNom());
tflieu.setText(tabvue.getSelectionModel().getSelectedItems().get(0).getLieux());
tfnbrp.setText(Integer.toString(tabvue.getSelectionModel().getSelectedItems().get(0).getNbrplaces()));
tfprix.setText(Float.toString(tabvue.getSelectionModel().getSelectedItems().get(0).getPrix()));
Date d=tabvue.getSelectionModel().getSelectedItems().get(0).getDate();
id=tabvue.getSelectionModel().getSelectedItems().get(0).getId();
//labelinfo.setText(tabvue.getSelectionModel().getSelectedItems().toString());
event=tabvue.getSelectionModel().getSelectedItems().get(0);
    System.out.println(event.getId());
});

}

   @FXML
    void supprimerevenement(ActionEvent event) {
 EvenementServices es=new EvenementServices();   
Evenement e =new Evenement();
ReservationServices rs =new ReservationServices();
    ObservableList<Reservation>reservations=rs.chercher(id);
if (!reservations.isEmpty()){
  Notifications ntb=Notifications.create()
.title("alert")
.text("il faut supprimer les reservations")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.CENTER)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");

}

}
);
ntb.showConfirm();
}
else{
e.setId(id);
es.supprimer(e);
     Notifications ntb=Notifications.create()
.title("fasakh fasakh")
.text("jwk bhy")
.graphic(null)
.hideAfter(Duration.seconds(5))
.position(Pos.TOP_RIGHT)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent evt){
throw new UnsupportedOperationException("nope");

}

}
);
ntb.showConfirm();
affEvenement();
viderleschamp();
    }}
  @FXML
    void stat(ActionEvent event) {
try {  
  FXMLLoader loder =new FXMLLoader(getClass().getResource("PiechartFXML.fxml"));  
Parent root =(Parent)loder.load();
PiechartFXMLController controller =loder.getController();
controller.recuperer(this.event);
Scene scene = new Scene(root);
            Stage s =new Stage();
            s.setScene(scene);
            s.setTitle("-----pie ------");
s.show();
    }
catch(Exception ex){


}
}
@FXML
    void reservation(ActionEvent event) {
try {  
     FXMLLoader loder =new FXMLLoader(getClass().getResource("reservation.fxml"));  
Parent fxml =(Parent)loder.load();
 //fxml=new FXMLLoader.load(getClass().getResource("reservation.fxml"));
AnchorPane.getChildren().removeAll();
AnchorPane.getChildren().setAll(fxml);
ReservationController controller =loder.getController();
controller.recuperer(this.event);
//Scene scene = new Scene(root);
  //          Stage s =new Stage();
    //        s.setScene(scene);
      //      s.setTitle("-----reserver ------");
//s.show();
//AnchorPane.getChildren().setAll(pane);
} catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
/*
            Parent root =FXMLLoader.load(getClass().getResource("reservation.fxml"));
            s.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
/*@FXML
    void chercherevenementbyNom1(KeyEvent event) {

    }*/
@FXML
    void chercherevenementbyNom1(KeyEvent event) {
if (chercher.getText()==""){
id=tabvue.getSelectionModel().getSelectedItems().get(0).getId();
affEvenement();
}   else{ 
System.out.println(chercher.getText());
EvenementServices es=new EvenementServices();  
    ObservableList<Evenement>evenements=es.afficherparNom(chercher.getText());
lieutabvue.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieux"));
nomtabvue.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
prixtabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Float>("prix"));
placetabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("nbrplaces"));
datetabvue.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
tabvue.setItems(evenements);
tabvue.setOnMouseClicked(e->{
    System.out.println("bien click");
  //  System.out.println((tabvue.getSelectionModel().getSelectedItems().get(0).getNom()));
tfnom.setText(tabvue.getSelectionModel().getSelectedItems().get(0).getNom());
tflieu.setText(tabvue.getSelectionModel().getSelectedItems().get(0).getLieux());
tfnbrp.setText(Integer.toString(tabvue.getSelectionModel().getSelectedItems().get(0).getNbrplaces()));
tfprix.setText(Float.toString(tabvue.getSelectionModel().getSelectedItems().get(0).getPrix()));
Date d=tabvue.getSelectionModel().getSelectedItems().get(0).getDate();
id=tabvue.getSelectionModel().getSelectedItems().get(0).getId();
labelinfo.setText(tabvue.getSelectionModel().getSelectedItems().toString());
//event=tabvue.getSelectionModel().getSelectedItems().get(0);
  //  System.out.println(event.getId());
});
    }}
  @FXML
    void viderleschamps(ActionEvent event) {
tfnom.setText("");
tflieu.setText("");
tfnbrp.setText("");
tfprix.setText("");
    }
    void viderleschamp() {
tfnom.setText("");
tflieu.setText("");
tfnbrp.setText("");
tfprix.setText("");
    }
public void envtsms (){
  // Find your Account Sid and Token at twilio.com/user/account
  //String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  //String AUTH_TOKEN = "your_auth_token";
    Twilio.init("AC4e747cdc9de3b0741739beebb334962c","2f4079cf1db9100cdf150fb951d02c5e");
    Message message = Message.creator(new PhoneNumber("+21651966671"),
        new PhoneNumber("+13606851303"), 
        "This is the ship that made the Kessel Run in fourteen parsecs?").create();
    System.out.println(message.getSid());
}
}
