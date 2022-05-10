/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.mysql.jdbc.Connection;
import java.io.IOException;
//import java.awt.event.MouseEvent;
import java.net.URL;
import javafx.scene.Scene;
import static java.nio.file.Files.list;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Commande;
import services.ServiceCommande;
import utils.database;
import static utils.database.getCnx;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLCommandeController implements Initializable {

        
       ObservableList list;
     @FXML   
    private TextField tfIduser;
          @FXML   
    private TextField tfNumc;
          @FXML   
    private DatePicker dpDatecom;
          @FXML
    private TextField tfAdresse;
          @FXML   
    private TextField tfVille;
          @FXML   
    private TextField tfPrix;
          @FXML
    private TableView<Commande> tvCommandes;
          @FXML
    private TableColumn<Commande,String> colNumc;
          @FXML
    private TableColumn<Commande,String>  colAdresse;
          @FXML
    private TableColumn<Commande,String>  colVille;
          @FXML
    private TableColumn<Commande,String>    colPrix;   
          @FXML
    private Button btnAjouter;
          @FXML
    private Button btnModifier;
          @FXML
    private Button btnSupprimer;
          @FXML
    private Button btnimprimer;
    ServiceCommande sc=new ServiceCommande();
    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfSearch;

          
   // @FXML
   // private void handleButtonAction(ActionEvent event){
        //System.out.println("You clicked me !");
        //label.setText("Hello World !");
       // if(event.getSource() == btnAjouter){
           // Ajouter();
        //}
  //  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        Afficher();
        
    }
 
        
     @FXML 
      private void handleMouseAction(MouseEvent event){
          
        Commande commandes = tvCommandes.getSelectionModel().getSelectedItem();
      
        tfIduser.setPrefColumnCount(commandes.getIdUser());
        tfNumc.setText(commandes.getNumc());
        dpDatecom.setDayCellFactory((Callback<DatePicker, DateCell>) commandes.getDatecommande());
        tfAdresse.setText(commandes.getAdresselivraison());
        tfVille.setText(commandes.getVille());
        tfPrix.setText(commandes.getPrix_total());
       
        
    }
      
    private void Afficher() {
   
    ServiceCommande sc = new ServiceCommande();
    ObservableList<Commande> commandes = (ObservableList<Commande>) sc.afficher();
    list = FXCollections.observableList(commandes);
    tvCommandes.setItems(list);
    colNumc.setCellValueFactory(new PropertyValueFactory<Commande,String>("Numc"));
    colAdresse.setCellValueFactory(new PropertyValueFactory<Commande,String>("Adresselivraison"));
    colVille.setCellValueFactory(new PropertyValueFactory<Commande,String>("ville"));
   // colDate.setCellValueFactory(new PropertyValueFactory<Commande,Date>("datecommande"));
    colPrix.setCellValueFactory(new PropertyValueFactory<Commande,String>("prix_total"));


    //tvCommandes.setItems(list);
    
    }
   /* private void Ajout(){
        String query =  "INSERT INTO `commande`(`id`, `users_id`, `numc`, `datecommande`, `Ville`, `adresselivraison`, `prix_total`) VALUES ('"+tfIduser.getText()+"','"+tfNumc.getText()+"','"+dpDatecom.getValue()+"','"+tfVille.getText()+"','"+tfAdresse.getText()+"','"+tfPrix.getText()+"')";
        executeQuery(query);
       
        getListCommandes();
    }
    private void executeQuery(String query){
        ServiceCommande sc = new ServiceCommande();
        Commande c = new Commande();
       sc.ajouter(c);
    }*/
    
    @FXML
      private void Ajouter(ActionEvent event) {
        Commande c = new Commande();
        ServiceCommande cs = new ServiceCommande();
        if((tfIduser.getText().isEmpty() || tfIduser.getText().isEmpty() || tfAdresse.getText().isEmpty() || tfVille.getText().isEmpty() || tfPrix.getText().isEmpty() )){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez remplir les champs");
            alert.showAndWait();  
        }
        
        c.setIdUser(Integer.parseInt(tfIduser.getText()));
        c.setNumc(tfNumc.getText());
        c.setAdresselivraison(tfAdresse.getText());
        c.setVille(tfVille.getText());
        c.setDatecommande(java.sql.Date.valueOf(dpDatecom.getValue()));
        c.setPrix_total(tfPrix.getText());
        cs.ajouter(c);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Commande ajoutée");
        a.show();
    }
      
     /*  @FXML
    private void Supprimer(ActionEvent event) {
        final Commande selectedItem = tvCommandes.getSelectionModel().getSelectedItem();
        Commande c = sc.GetById(selectedItem.getId());
        sc.supprimer(c.getId());
         List<Commande> commande = sc.afficher();
         list = FXCollections.observableList(commande);
        list.remove(selectedItem);
        tvCommandes.setItems(FXCollections.observableArrayList(sc.afficher()));
        tvCommandes.refresh();
        
    }*/
       @FXML
     void Supprimer(ActionEvent event) {
         Commande R= tvCommandes.getSelectionModel().getSelectedItem();
        ServiceCommande rS = new ServiceCommande();
                //Commande c = rS.GetById(getId());

        if (rS.supprimer(R)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("La suppression a été effectuée avec succès");
        alert.showAndWait();
        Afficher();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec de suppression");
        alert.showAndWait();   
        Afficher();
        }
    }
 @FXML
    private void Modifier(ActionEvent event) {
        
        final Commande selectedItem = tvCommandes.getSelectionModel().getSelectedItem();
        Commande c = sc.GetById(selectedItem.getId());
        //c.setIdUser(Integer.parseInt(tfIduser.getText()));
        c.setAdresselivraison(tfAdresse.getText());
        c.setVille(tfVille.getText());
        c.setDatecommande(java.sql.Date.valueOf(dpDatecom.getValue()));
        c.setNumc(tfNumc.getText());
        c.setPrix_total(tfPrix.getText());
     
         try {
                       
        sc.modifier(c);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        tvCommandes.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       
       tvCommandes.setItems((ObservableList<Commande>) sc.afficher());
    }
 
   @FXML
    private void Imprimer(ActionEvent event) {
        btnimprimer.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Printer printer = Printer.getDefaultPrinter();
					PrinterJob job = PrinterJob.createPrinterJob();
					PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE,
							Printer.MarginType.DEFAULT);
					 Stage dialogStage = new Stage(StageStyle.DECORATED);
					job.getJobSettings().setPageLayout(pageLayout);
                                        if (job != null) {
						boolean successPrintDialog = job.showPrintDialog(dialogStage.getOwner());
						if (successPrintDialog) {
							boolean success = job.printPage(pageLayout, tvCommandes);
							if (success) {
								job.endJob();
								Stage stage = (Stage) btnimprimer.getScene().getWindow();
								stage.close();
                                                                				}
							
						}
					}
				}
                                });
    }
                 private java.sql.Connection cnx = database.getInstance().getCnx() ;

    @FXML
    void searchCommande() {

        String query="SELECT * FROM `commande` WHERE Ville ='"+tfSearch.getText()+"'";
          int em=0;
        try {
                    Statement stm = cnx.createStatement();

            ResultSet rs =stm.executeQuery(query);
            if(rs.next()){
                tfNumc.setText(rs.getString("numc"));
                tfVille.setText(rs.getString("Ville"));
                tfAdresse.setText(rs.getString("adresselivraison"));
                tfPrix.setText(rs.getString("prix_total"));
               em=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(em==0){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Aucune commande trouver dans  "+tfSearch.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
     @FXML
    private void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

   
    
    
}
