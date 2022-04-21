/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceCommentaire;


public class NewFXMain extends Application {
    double xOffset,yOffset;
    @Override
    public void start(Stage stage) throws IOException {
         Parent root =FXMLLoader.load(getClass().getResource("/GUI/allUsers.fxml"));
        //   Parent root =FXMLLoader.load(getClass().getResource("/GUI/commentaire/CommentaireListPage.fxml"));
     // Parent root =FXMLLoader.load(getClass().getResource("/GUI/commentaire/CommentaireListPage.fxml"));
        ServiceCommentaire sc =new ServiceCommentaire();
      System.out.println( sc.afficher());
System.out.println();
       //     Parent root =FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));

            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            root.setOnMousePressed((MouseEvent event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
                stage.setOpacity(0.85f);
            });
            root.setOnMouseReleased((MouseEvent event) -> {
                stage.setOpacity(1.0f);
            });
        }


        /**
         * @param args the command line arguments
         */
    public static void main(String[] args) {
        launch(args);
    }
    
}
