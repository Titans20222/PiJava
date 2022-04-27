package controller.user;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Commentaire;
import model.Users;
import services.comment.ServiceCommentaire;
import services.user.ServiceUsers;

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
public class UsersListPageController implements Initializable {
    public boolean canModify=false;
    private int selectedId;
    @FXML
    private AnchorPane moderationPane;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private TableView<Users> userTable;

    @FXML
    private TableColumn<Commentaire, String> nomCol;
    @FXML
    private TableColumn<Commentaire, String> prenomCol;

    @FXML
    private TableColumn<Commentaire, String> emailCol;

    @FXML
    private TableColumn<Commentaire, String> adresseCol;

    @FXML
    private TableColumn<Commentaire, Integer> mobileCol;
    @FXML
    private TableColumn<Commentaire, Integer> roleCol;





    @FXML
    private JFXTextField RechercheTF;




   // ServiceUsers su = new ServiceUsers();

    ServiceUsers su = new ServiceUsers();
    double xOffset, yOffset;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Users> users = su.afficher();
        ServiceUsers rc = new ServiceUsers();

        ObservableList<Users> list = FXCollections.observableList((List<Users>) users);


        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("roles"));

System.out.println(list);
        userTable.setItems(list);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) userTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) userTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        Users selected = userTable.getSelectionModel().getSelectedItem();
        selectedId = selected.getId();

       su.supprimer(selectedId);
        userTable.setItems((ObservableList<Users>) su.afficher());

    }
    @FXML
    private void RechercheUser(KeyEvent event) throws SQLException {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("roles"));


        String n = RechercheTF.getText();
        userTable.setItems(su.RechercheUserAvance(n));
    }

}
