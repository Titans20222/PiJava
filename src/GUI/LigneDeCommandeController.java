/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LigneDeCommande;
import services.ServiceLigneCommande;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class LigneDeCommandeController implements Initializable {

           ObservableList list;
    @FXML
    private TableView<LigneDeCommande> tvLigneCommande;
    @FXML
    private TableColumn<LigneDeCommande,Integer> colProduitId;
    @FXML
    private TableColumn<LigneDeCommande, String> colQte;
    @FXML
    private TableColumn<LigneDeCommande, String> colPrix;
    @FXML
    private TableColumn<LigneDeCommande, String> colLigne;
    @FXML
    private Button btnafficherLC;
    @FXML
    private Button btnimprimerLC;
    @FXML
    private TextField tfIdProduit;
    @FXML
    private TextField tfIdCommande;
    @FXML
    private TextField tfQte;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfligne;
    @FXML
    private Button btnajouterLC;
    @FXML
    private Button btnmodifierLC;
    @FXML
    private Button btnsupprimerLC;
    ServiceLigneCommande lc=new ServiceLigneCommande();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherLC();
    } 
    
      @FXML 
      private void handleMouseActionLC(MouseEvent event){
          
        LigneDeCommande lignecommandes = tvLigneCommande.getSelectionModel().getSelectedItem();
      
        tfIdProduit.setText(Integer.toString(lignecommandes.getProduit_id()));
        tfligne.setText(lignecommandes.getLigne());
        tfIdCommande.setText(Integer.toString(lignecommandes.getCommande_id()));
        tfQte.setText(lignecommandes.getQte());
        tfPrix.setText(lignecommandes.getPrix());
    }
      
        @FXML
    private void AfficherLC() {
   
    ServiceLigneCommande lc = new ServiceLigneCommande();
    ObservableList<LigneDeCommande> lignecommandes = (ObservableList<LigneDeCommande>) lc.afficher();
    ObservableList<LigneDeCommande> list = FXCollections.observableList(lignecommandes);
    tvLigneCommande.setItems(list);
    colProduitId.setCellValueFactory(new PropertyValueFactory<LigneDeCommande,Integer>("produit_id"));
   // colNum.setCellValueFactory(new PropertyValueFactory<LigneDeCommande,String>("numcommande"));
    colQte.setCellValueFactory(new PropertyValueFactory<LigneDeCommande,String>("qte"));
    colPrix.setCellValueFactory(new PropertyValueFactory<LigneDeCommande,String>("prix"));
    colLigne.setCellValueFactory(new PropertyValueFactory<LigneDeCommande,String>("ligne"));
    }
    
       @FXML
     void SupprimerLC(ActionEvent event) {
         LigneDeCommande L= tvLigneCommande.getSelectionModel().getSelectedItem();
        ServiceLigneCommande sl = new ServiceLigneCommande();
                //Commande c = rS.GetById(getId());

        if (sl.supprimer(L)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("La suppression a été effectuée avec succès");
        alert.showAndWait();
        AfficherLC();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec de suppression");
        alert.showAndWait();   
        AfficherLC();
        }
    }
   
    
   
    
     @FXML
      private void AjouterLC(ActionEvent event) {
        LigneDeCommande lc = new LigneDeCommande();
        
        lc.setCommande_id(Integer.parseInt(tfIdCommande.getText()));
        lc.setProduit_id(Integer.parseInt(tfIdProduit.getText()));

       // c.setNum(tfNumc.getText());
        lc.setPrix(tfPrix.getText());
        lc.setQte(tfQte.getText());
        lc.setLigne(tfligne.getText());
        ServiceLigneCommande cs = new ServiceLigneCommande();
        cs.ajouter(lc);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Commande ajoutée");
        a.show();
    }
    
     @FXML
    private void ModifierLC(ActionEvent event) {
        
        final LigneDeCommande selectedItem = tvLigneCommande.getSelectionModel().getSelectedItem();
        LigneDeCommande l = lc.GetById(selectedItem.getId());
        l.setQte(tfQte.getText());
        l.setPrix(tfPrix.getText());
        l.setNumcommande(tfIdCommande.getText());
        //l.setProduit_id(tfIdProduit.getText(Integer.toString()));
        l.setLigne(tfligne.getText());
        lc.modifier(l);
        tvLigneCommande.setItems(FXCollections.observableArrayList(lc.afficher()));
        tvLigneCommande.refresh();
    }
    
     @FXML
    private void ImprimerLC(ActionEvent event) {
        
        btnimprimerLC.setOnAction(new EventHandler<ActionEvent>() {
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
							boolean success = job.printPage(pageLayout, tvLigneCommande);
							if (success) {
								job.endJob();
								Stage stage = (Stage) btnimprimerLC.getScene().getWindow();
								stage.close();
                                                                				}
							
						}
					}
				}
                                });
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
