/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.user.UserSession;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminPageHolderController implements Initializable {

    @FXML
    private AnchorPane pageHolder;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label time;

    private   volatile boolean stop =false;
    @FXML
    private AnchorPane produit;
    @FXML
    private AnchorPane commande;
    @FXML
    private AnchorPane evennement;
    @FXML
    private AnchorPane profileSlider;
    @FXML
    private AnchorPane reclamation;
    @FXML
    private AnchorPane produit1;

    @FXML
    private AnchorPane produit11;
    @FXML
    private AnchorPane homeSideBar;
    @FXML
    private Label userName;
    @FXML
    private Label userEmail;
    @FXML
    private Label userName1;
    @FXML
    private AnchorPane profileSideBar;
    double xOffset, yOffset;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



        profileSideBar.getStyleClass().add("unselectedMenu");

        userName1.setText(UserSession.getNom() + " " + UserSession.getPrenom());
        userName.setText(UserSession.getNom() + " " + UserSession.getPrenom());
        userEmail.setText(UserSession.getEmail());
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/backoffice/DashboardPage.fxml")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    private void signOutAction(MouseEvent event) throws IOException {
        UserSession.cleanUserSession();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/frontoffice/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        Stage stage1 = (Stage) slider.getScene().getWindow();
        stage1.close();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void backAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(profileSlider);
        slide.setToX(0);
        slide.play();
    }
    @FXML
    private void userPorfileAction(MouseEvent event) throws IOException {
        profileMenu();
        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(0.4));
        slide2.setNode(profileSlider);
        slide2.setToX(0);
        slide2.play();
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(491);
        slide.play();
        profileMenu();
    }


    public void profileMenu() throws IOException {


        //  taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        // taskSideBar.getStyleClass().add("menu");
        // taskSideBar.getStyleClass().add("unselectedMenu");





        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("selectedMenu");

        // bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        //  bookSideBar.getStyleClass().add("menu");
        //  bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI//user/ProfileUser.fxml")));
    }
    @FXML
    private void profileAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(profileSlider);
        slide.setToX(-324);
        slide.play();
    }
    @FXML
    private void dashboardPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(0);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/backoffice/DashboardPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void usersPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(67);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/allUsers.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
   /* @FXML
    private void commentPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(67);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/backoffice/commentaire/CommentaireListPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }*/



    @FXML
    private void produitPageAction(MouseEvent event) {
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/gui_yasmine/produit/ProduitPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    private void commentPageAction(MouseEvent event) {
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/backoffice/commentaire/CommentaireListPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void commandePageAction(MouseEvent event) {
        
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/gui_triki/FXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    private void profilePageAction(MouseEvent event) {

//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI//user/ProfileUser.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void evennementPageAction(MouseEvent event) {
        
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/gui_warrad/EvenementFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void reclamationPageAction(MouseEvent event) {
        
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI_bazdeh/FXMLReclamation.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void categoryPageAction(MouseEvent event) {
        
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/gui_yasmine/gestion_categorie/Category.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void reponsePageAction(MouseEvent event) {
        
//        TranslateTransition slide = new TranslateTransition();
//        slide.setDuration(Duration.seconds(0.4));
//        slide.setNode(produit);
//        slide.setToY(67);
//        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI_bazdeh/FXMLReponse.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   









}
