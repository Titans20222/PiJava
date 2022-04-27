/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animatefx.animation.ZoomIn;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.comment.ServiceCommentaire;
import services.user.ServiceUsers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardPageController implements Initializable {

    @FXML
    private FontAwesomeIconView close;
    @FXML
    private AnchorPane dashboardPane;


    @FXML
    private Label totalComment;

    
    @FXML
    private Label totalUsers;


    @FXML
    private Label noUrgReportsLabel;

    ServiceUsers su =new ServiceUsers();
    ServiceCommentaire commentaire =new ServiceCommentaire();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(dashboardPane).play();


        totalUsers.setText(String.valueOf(su.userCount()));
        totalComment.setText(String.valueOf(commentaire.commentCount()));

    }
    
       @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }

}
