/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Models.Evenement;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Services.ReservationServices;
import Models.Reservation;
import Services.EvenementServices;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;


import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user1
 */
public class ReservationController implements Initializable {
int id ;
Evenement e;
int idreservation;
  String  from;
 String to ;
String host;
 String sub;
 String content ;
    Reservation r1=new Reservation();
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane AnchorPane;  
@FXML
    private Button reserver;

    @FXML
    private  TextField tfemail;

    @FXML
    private TextField tfnbplaces;

    @FXML
    private TextField chercher;

    @FXML
    private TextField tfnumtel;
 @FXML
    private Button retour;

   @FXML
    private TableColumn<Reservation ,String> emailtabvue;

    @FXML
    private Label lbevent;

    @FXML
    private TableColumn<Reservation, Integer> numteltabvue;

    @FXML
    private TableColumn<Reservation,Integer> placetabvue;

    

    @FXML
    private TableView<Reservation> tabvueR;



   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //recuperer(e);

        // TODO
    } 
public void recuperer(Evenement e){
lbevent.setText(e.toString());
this.e=e;
id=e.getId();
affreservationParEvenement(id);
}   
     @FXML
    void voirfacture(ActionEvent event) {
try {  
     FXMLLoader loder =new FXMLLoader(getClass().getResource("factureFXML.fxml"));  
Parent root =(Parent)loder.load();
FactureFXMLController controller =loder.getController();
controller.recuperer(e,r1);
Scene scene = new Scene(root);
            Stage s =new Stage();
            s.setScene(scene);
            s.setTitle("-----facture ------");
s.show();
//AnchorPane.getChildren().setAll(pane);
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
/*
            Parent root =FXMLLoader.load(getClass().getResource("reservation.fxml"));
            s.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
 @FXML
    void retour(ActionEvent event) {
try {  
     FXMLLoader loder =new FXMLLoader(getClass().getResource("EvenementFXML.fxml"));  
Parent fxml =(Parent)loder.load();
 //fxml=new FXMLLoader.load(getClass().getResource("reservation.fxml"));
AnchorPane.getChildren().removeAll();
AnchorPane.getChildren().setAll(fxml);
//ReservationController controller =loder.getController();
//controller.recuperer(this.event);
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

    

public  boolean isEmailAdress(){
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)");
    Matcher m = p.matcher(tfemail.getText());
System.out.println("aa");

if(m.find()&&m.group().equals(tfemail.getText()))  
{
    System.out.println("a");
 // System.out.println(m.matches());
return true;
}
else
return false;
    
}



@FXML
    void reserver(ActionEvent event) {

    ReservationServices rs=new ReservationServices();
Reservation r =new Reservation();


int test=0;

try{
    int entier = Integer.parseInt(tfnbplaces.getText());
Integer f =Integer.parseInt(tfnumtel.getText());
        if(entier>0 && f>0 && tfnumtel.getText().length()==8 && isEmailAdress() )
{test=1;
        System.out.println("Est un int positif!");
}
}catch(NumberFormatException parEx){System.out.println("N'est pas un int !");
test=0;
}
if(test==0){
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

r.setAdresse(tfemail.getText());
r.setNbrplaces(Integer.parseInt(tfnbplaces.getText()));
r.setNumtel(Integer.parseInt(tfnumtel.getText()));
r.setIdevenement(id);

rs.ajouter(r);
affreservationParEvenement(id);
     Notifications ntb=Notifications.create()
.title("Reservation")
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
sendMail();
}}  
void affreservationParEvenement(int id){
ReservationServices rs =new ReservationServices();
    ObservableList<Reservation>reservations=rs.chercher(id);
emailtabvue.setCellValueFactory(new PropertyValueFactory<Reservation,String>("adresse"));
numteltabvue.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("numtel"));

placetabvue.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("nbrplaces"));


tabvueR.setItems(reservations);
tabvueR.setOnMouseClicked(e->{
    System.out.println("bien click");
  //  System.out.println((tabvue.getSelectionModel().getSelectedItems().get(0).getNom()));
tfemail.setText(tabvueR.getSelectionModel().getSelectedItems().get(0).getAdresse());
tfnbplaces.setText(Integer.toString(tabvueR.getSelectionModel().getSelectedItems().get(0).getNbrplaces()));
tfnumtel.setText(Integer.toString(tabvueR.getSelectionModel().getSelectedItems().get(0).getNumtel()));

idreservation=tabvueR.getSelectionModel().getSelectedItems().get(0).getId();
r1=tabvueR.getSelectionModel().getSelectedItems().get(0);
 
});

}
    @FXML
    void modifier(ActionEvent event) {
ReservationServices rs=new ReservationServices();
Reservation r =new Reservation();
int test=0;

try{
    int entier = Integer.parseInt(tfnbplaces.getText());
Integer f =Integer.parseInt(tfnumtel.getText());
        if(entier>0 &&f>0 && tfnumtel.getText().length()==8 )
test=1;
        System.out.println("Est un int positif!");

}catch(NumberFormatException parEx){System.out.println("N'est pas un int !");
test=0;
}
if(tfemail.getText().isEmpty()||test==0){
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

r.setAdresse(tfemail.getText());
r.setNumtel(Integer.parseInt(tfnumtel.getText()));
r.setNbrplaces(Integer.parseInt(tfnbplaces.getText()));
r.setId(idreservation);
rs.modifier(r);
affreservationParEvenement(id);
Notifications ntb=Notifications.create()
.title("Modifer")
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
vider();
} 
}
   @FXML
    void supprimer(ActionEvent event) {
ReservationServices rs=new ReservationServices();
Reservation r= new Reservation();
       annulersms("+216"+tfnumtel.getText());
r.setId(idreservation);
      
rs.supprimer(r);
        affreservationParEvenement(id);
     Notifications ntb=Notifications.create()
.title("Supprimer")
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
}
@FXML
    void cherchererservation(KeyEvent event) {
ReservationServices rs =new ReservationServices();
    ObservableList<Reservation>reservations=rs.chercher(chercher.getText(),id);
emailtabvue.setCellValueFactory(new PropertyValueFactory<Reservation,String>("adresse"));
numteltabvue.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("numtel"));
placetabvue.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("nbrplaces"));
tabvueR.setItems(reservations);
tabvueR.setOnMouseClicked(e->{
    System.out.println("bien click");
  //  System.out.println((tabvue.getSelectionModel().getSelectedItems().get(0).getNom()));
tfemail.setText(tabvueR.getSelectionModel().getSelectedItems().get(0).getAdresse());
tfnbplaces.setText(Integer.toString(tabvueR.getSelectionModel().getSelectedItems().get(0).getNbrplaces()));
tfnumtel.setText(Integer.toString(tabvueR.getSelectionModel().getSelectedItems().get(0).getNumtel()));
idreservation=tabvueR.getSelectionModel().getSelectedItems().get(0).getId();
r1=tabvueR.getSelectionModel().getSelectedItems().get(0);
}); }

private  void sendMail(){
from ="mostfa.wrad@gmail.com";

host ="localhost";
sub="Reservation Mail";
content="vous avez reservé " + tfnbplaces.getText()+" places au evenement "+e.getNom()+" du date "+e.getDate()+" qui va se deroulé a "+e.getLieux();
    Properties p =new Properties();
p.put("mail.smtp.auth","true");
p.put("mail.smtp.starttls.enable","true");
p.put("mail.smtp.host","smtp.gmail.com");
p.put("mail.smtp.port","587");
 Session s =Session.getInstance(p,new  Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication(){
return new PasswordAuthentication("mostfa.wrad@gmail.com","taraji19ss4");
}
});
try{
    MimeMessage m =new MimeMessage(s);
m.setFrom("mostfa.wrad@gmail.com");
m.addRecipient(Message.RecipientType.TO, new InternetAddress(tfemail.getText()));
m.setSubject(sub);
m.setText(content);
Transport.send(m);
System.out.println("send");
}catch(Exception ex){
    ex.printStackTrace();
}
}
 public void annulersms (String num){
  // Find your Account Sid and Token at twilio.com/user/account
  //String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  //String AUTH_TOKEN = "your_auth_token";
    Twilio.init("AC4e747cdc9de3b0741739beebb334962c","2f4079cf1db9100cdf150fb951d02c5e");
    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(num),
        new PhoneNumber("+13606851303"), 
        "levenement est annuler").create();
    System.out.println(message.getSid()); 
}
public void vider(){
tfemail.setText("");
tfnbplaces.setText("");
tfnumtel.setText("");
}
}
