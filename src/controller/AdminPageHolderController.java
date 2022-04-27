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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    private Label label;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label time;

    private   volatile boolean stop =false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/backoffice/DashboardPage.fxml")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Timenow();
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

    private void Timenow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
          //  sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

            while(!stop){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow); // This is the label
                });
            }
        });
        thread.start();
    }
   









}