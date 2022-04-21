/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commentaire;


import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Commentaire;
import model.Users;
import services.ServiceCommentaire;
import services.user.ServiceUsers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentaireListPageController implements Initializable {
    public boolean canModify=false;
    private int selectedId;
    @FXML
    private AnchorPane moderationPane;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private TableView<Commentaire> commentaireTable;

    @FXML
    private TableColumn<Commentaire, String> nomCol;

    @FXML
    private TableColumn<Commentaire, String> emailCol;

    @FXML
    private TableColumn<Commentaire, String> DescriptionCol;

    @FXML
    private TableColumn<Commentaire, Integer> ratingCol;

    @FXML
    private TableColumn<Commentaire, Date> dateCol;

    @FXML
    private TableColumn<Commentaire, Integer> produitCol;

    @FXML
    private TableColumn<Commentaire, Integer> idCol;



    @FXML
    private JFXTextField RechercheTF;




    ServiceCommentaire sc = new ServiceCommentaire();

    ServiceUsers rcs = new ServiceUsers();
    double xOffset, yOffset;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Commentaire> commentaires = sc.afficher();
        ServiceUsers rc = new ServiceUsers();

        ObservableList<Commentaire> list = FXCollections.observableList((List<Commentaire>) commentaires);


        nomCol.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("nom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("email"));

       DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("created_at"));
produitCol.setCellValueFactory(new PropertyValueFactory<>("produits_id"));
         commentaireTable.setItems(list);

        //PieChart


    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) commentaireTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) commentaireTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        Commentaire selected = commentaireTable.getSelectionModel().getSelectedItem();
        selectedId = selected.getId();

            sc.SupprimerById(selectedId);
            commentaireTable.setItems((ObservableList<Commentaire>) sc.afficher());

    }
    @FXML
    private void RechercheCommetaire(KeyEvent event) throws SQLException {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        String n = RechercheTF.getText();
        commentaireTable.setItems(sc.RechercheCommentaireAvance(n));
    }

}
