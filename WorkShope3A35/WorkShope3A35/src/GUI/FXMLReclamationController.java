/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Reclamation;
import Services.ServiceReclamation;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.Mailapi;
//import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLReclamationController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Reclamation> tvReclamations;
    @FXML
    private TableColumn<Reclamation, String> colTitre;
    @FXML
    private TableColumn<Reclamation, String> colDescription;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnSHOW;
    @FXML
    private Button btnUPDATE;
    @FXML
    private Button btnDELETE;
    @FXML
    private TextField tfId;
    private Spinner<?> spId;
    @FXML
    private TextField rech;
    @FXML
    private Label erreur_titre;
    private boolean verificationUsernom ;

        ServiceReclamation sr = new ServiceReclamation();


          int  index= -1; 
     List<Reclamation> reclamations = FXCollections.observableArrayList();//new ArrayList();
ObservableList<Reclamation> reclamations1=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//tvReclamations.setItems(reclamations);
//  tablereclamation.refresh();
        afficher();
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        //
        if(verifUserChampsajouter() && verificationUsernom){
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(new Reclamation(0, tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText()));
        Mailapi.send("sanaatyTn@gmail.com", "sanewbar", "bazdeh.mustapha@esprit.tn" , "nouvelle reclamation", "votre réclamation a ete envoyé");
    }
    }
    @FXML
    private void afficher() {
       ServiceReclamation sr = new ServiceReclamation();
//colTitre.setText(sr.afficher().toString());
//colDescription.setText(sr.afficher().toString());
//colType.setText(sr.afficher().toString());
//colDate.setText(sr.afficher().toString());

             List<Reclamation> list = sr.afficher();
                   //  colTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));

        colTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("date"));
tvReclamations.setItems((ObservableList<Reclamation>) list);
        //System.out.println(list);
       
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        ServiceReclamation sr = new ServiceReclamation();
         sr.modifier(new Reclamation(Integer.parseInt(tfId.getText()), tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText()));
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();
         sr.supprimer(new Reclamation(Integer.parseInt(tfId.getText()), tfTitre.getText(), taDescription.getText(), tfDate.getText(), tfType.getText())); 
    }

    @FXML
    private void recherche(ActionEvent event) {
    
    ServiceReclamation ps = new ServiceReclamation();
    List<Reclamation> Reservation = ps.Recherche (rech.getText());
    tvReclamations.getItems().clear();
    tvReclamations.getItems().removeAll(Reservation);
    tvReclamations.getItems().addAll(Reservation);
    

    }

    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        tfTitre.setStyle(styledefault);
        taDescription.setStyle(styledefault);
        tfType.setStyle(styledefault);
        tfDate.setStyle(styledefault);
        if (tfDate.getText().equals("")) {
            tfDate.setStyle(style);
            verif = 1;
        }

       if (tfTitre.getText().equals("")) {
            tfTitre.setStyle(style);
            verif = 1;
        }
        if (taDescription.getText().equals("")) {
            taDescription.setStyle(style);
            verif = 1;
        }
        if (tfType.getText().equals("")) {
            tfType.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;
    }

    @FXML
    private void test_titre(KeyEvent event) {
    int nbNonChar = 0;
            for (int i = 1; i < tfTitre.getText().trim().length(); i++) {
                char ch = tfTitre.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfTitre.getText().trim().length() >=3) {
            
            erreur_titre.setText("Nom valide");
            
            verificationUsernom = true;
            } else {
              
              erreur_titre.setText("Il faut au moins 3 caracteres");
              verificationUsernom = false;

            }


    }

@FXML
    private void Selected(MouseEvent event) {
         index=tvReclamations.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfType.setText(colType.getCellData(index).toString());
                tfDate.setText(colDate.getCellData(index).toString());
                tfTitre.setText(colTitre.getCellData(index).toString());
                taDescription.setText(colDescription.getCellData(index).toString());
    }


@FXML
    void tri(ActionEvent event) {
  recherche_avance(sr.sortByDescription());
    }
 
public void recherche_avance(List<Reclamation> visites){
        refresh(visites);
        ObservableList<Reclamation> reclamationss1=FXCollections.observableArrayList(reclamations1);
        
        FilteredList<Reclamation> filterddata=new FilteredList<>(reclamationss1,b->true);
        rech.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filterddata.setPredicate(
                        u->{
                            if(u.getDescription().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getDate().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getTitre().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getType().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                          
                            else{
                                return false;
                            }
                            
                        }
                            
                    );
                    tvReclamations.setItems(filterddata);
                }
                
        );
        
    }

public void refresh(List<Reclamation> data){
        reclamations1.clear();
        reclamations1=FXCollections.observableArrayList(data);
        tvReclamations.setItems(reclamations1);
    }
 
//@FXML
//void pdf(ActionEvent event){
//try{
//Document doc = new Document();
//PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\mustapha\\Desktop\\Nouveau dossier (4)\\pdf.pdf"));
//doc.open();
//doc.addTitle("xx");
//doc.add(new )
//}
//}
  
}
