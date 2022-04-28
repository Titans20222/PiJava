/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.ReponseReclamation;
import Services.ServiceReponseReclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLReponseController implements Initializable {

    @FXML
    private TextField tfReclamationId;
    @FXML
    private TextArea taDescription;
    @FXML
    private TableColumn<ReponseReclamation, String> colDescription;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnSHOW;
    @FXML
    private Button btnUPDATE;
    @FXML
    private Button btnDELETE;
    @FXML
    private TextField rech;
    @FXML
    private Label erreur_titre;
    @FXML
    private TableView<ReponseReclamation> tvReponses;
    @FXML
    private TableColumn<ReponseReclamation, Integer> colReclamationId;
    private boolean verificationUsernom ;
    @FXML
    private TextField tfId;
    @FXML
    private ComboBox<?> cb_combo;

ResultSet rs = null;
     Connection coonx = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
        selectid();
    }    

//    @FXML
//    private void test_titre(KeyEvent event) {
//         int nbNonChar = 0;
//            for (int i = 1; i < tfReclamationId.getText().trim().length(); i++) {
//                char ch = tfReclamationId.getText().charAt(i);
//                if (!Character.isLetter(ch)) {
//                    nbNonChar++;
//                }
//            }
//
//            if (nbNonChar == 0 && tfReclamationId.getText().trim().length() >=3) {
//            
//            erreur_titre.setText("Nom valide");
//            
//            verificationUsernom = true;
//            } else {
//              
//              erreur_titre.setText("Il faut au moins 3 caracteres");
//              verificationUsernom = false;
//
//            }
//    }

    @FXML
    private void ajouter(ActionEvent event) {
//          if(verifUserChampsajouter() && verificationUsernom){
        ServiceReponseReclamation sr = new ServiceReponseReclamation();
        sr.ajouter(new ReponseReclamation(0, taDescription.getText(), Integer.parseInt(tfReclamationId.getText())));


    }//}
// public boolean verifUserChampsajouter() {
//        int verif = 0;
//        String style = " -fx-border-color: red;";
//
//        String styledefault = "-fx-border-color: green;";
//
//        tfReclamationId.setStyle(styledefault);
//        taDescription.setStyle(styledefault);
//        
//       
//
//       if (tfReclamationId.getText().equals("")) {
//            tfReclamationId.setStyle(style);
//            verif = 1;
//        }
//        if (taDescription.getText().equals("")) {
//            taDescription.setStyle(style);
//            verif = 1;
//        }
//       
//        if (verif == 0) {
//            return true;
//        }
//        
//        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
//        return false;
//    }
    @FXML
    private void afficher() {
          ServiceReponseReclamation sr = new ServiceReponseReclamation();
//colTitre.setText(sr.afficher().toString());
//colDescription.setText(sr.afficher().toString());
//colType.setText(sr.afficher().toString());
//colDate.setText(sr.afficher().toString());

             List<ReponseReclamation> list = sr.afficher();

             colReclamationId.setCellValueFactory(new PropertyValueFactory<ReponseReclamation, Integer>("reclamationid"));
        colDescription.setCellValueFactory(new PropertyValueFactory<ReponseReclamation,String>("description"));

tvReponses.setItems((ObservableList<ReponseReclamation>) list);
       
    }

    @FXML
    private void modifier(ActionEvent event) {
         ServiceReponseReclamation sr = new ServiceReponseReclamation();
         sr.modifier(new ReponseReclamation(Integer.parseInt(tfId.getText()), taDescription.getText(), Integer.parseInt(tfReclamationId.getText())));
    }

    @FXML
    private void supprimer(ActionEvent event) {
         ServiceReponseReclamation sr = new ServiceReponseReclamation();
         sr.supprimer(new ReponseReclamation(Integer.parseInt(tfId.getText()), taDescription.getText(), Integer.parseInt(tfReclamationId.getText()))); 
    }


  private void selectid() {
        try {

             coonx = utils.MyDb.getInstance().getCnx();

              rs = coonx.createStatement().executeQuery("select id from reclamation");
            
            ObservableList List = FXCollections.observableArrayList();
            while (rs.next()) {
            
            List.add(rs.getString(1));
            }
            
           cb_combo.setItems(List);
            
      } catch (Exception ex) {
            System.out.println("error while inserting record. ");
        }
    }
//    @FXML
//    private void recherche(ActionEvent event) {
//         ServiceReponseReclamation ps = new ServiceReponseReclamation();
//    List<ReponseReclamation> Reservation = ps.Recherche (Integer.parseInt(rech.getText()));
//    tvReponses.getItems().clear();
//    tvReponses.getItems().removeAll(Reservation);
//    tvReponses.getItems().addAll(Reservation);
//    }

   
}
