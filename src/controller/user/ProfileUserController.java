package controller.user;

import animatefx.animation.ZoomIn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.HomePageHolderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Users;
import services.user.ServiceUsers;
import services.user.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class ProfileUserController implements Initializable {

    @FXML
    private ScrollPane AnchorPane;
    @FXML
    private JFXTextField TFAdresse;

    @FXML
    private JFXTextField TFNom;

    @FXML
    private JFXTextField TFPrenom;

    @FXML
    private JFXTextField TFEmail;

    @FXML
    private JFXTextField TFMobile;



    @FXML
    private JFXPasswordField TFPassword;
    @FXML
    private JFXPasswordField TFConfirmPassword;
    @FXML
    private JFXPasswordField TFOldPassword;
    @FXML
    private ImageView imagedefaultuser;
    @FXML
    private JFXButton BTNUpdateProfile;
    @FXML
    private JFXButton BTNUpdatePassword;
    @FXML
    private JFXButton BTNDeactivate;
    private double xOffset, yOffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(AnchorPane).play();

        TFNom.setText(UserSession.getNom());
        TFPrenom.setText(UserSession.getPrenom());
        TFEmail.setText(UserSession.getEmail());
        TFAdresse.setText(UserSession.getAdresse());
        TFMobile.setText(UserSession.getMobile());

        TFEmail.setText(UserSession.getEmail());
    }

    @FXML
    private void UpdateProfile(ActionEvent event) {
        ServiceUsers su = new ServiceUsers();
        Users u = new Users();
        //sets
        u.setEmail(TFEmail.getText());
        u.setNom(TFNom.getText());
        u.setPrenom(TFPrenom.getText());
        u.setAdresse(TFAdresse.getText());
        u.setMobile(TFMobile.getText());

        su.ModifierUser(u,UserSession.getId());
        //-------------notification--------------------------------------------
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle("Profile Detail Updated SUCCESSFULLY");
        tray.setMessage("User detail updated succesfully");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        //---------------------------------------------------------------------
    }

    @FXML
    private void UpdatePassword(ActionEvent event) {
        ServiceUsers su = new ServiceUsers();
        String pw = UserSession.getPassword();
        Window owner = BTNUpdatePassword.getScene().getWindow();

        if (TFOldPassword.getText().equals(pw)) {
            if (TFPassword.getText().isEmpty() && TFConfirmPassword.getText().isEmpty()) {
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Password Update Warning");
                tray.setMessage("password and/or confirm password fields is/are empty");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
            } else {
                if (TFPassword.getText().equals(TFConfirmPassword.getText())) {
                    su.ModifierUserPassword(TFPassword.getText(), UserSession.getId());
                    //-------------notification--------------------------------------------
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle("Password Update SUCCESS");
                    tray.setMessage("User password updated succesfully");
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
                    //---------------------------------------------------------------------

                } else {
                    //-------------notification--------------------------------------------
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle("Password Update Warning");
                    tray.setMessage("password diffrent than the confirmpassword");
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(3000));
                    //---------------------------------------------------------------------
                }
            }
        } else if (TFOldPassword.getText().isEmpty()) {
            //-------------notification--------------------------------------------
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle("Password Update Warning");
            tray.setMessage("Old password field is empty");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
            //---------------------------------------------------------------------
        } else {
            //-------------notification--------------------------------------------
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle("Password Update Warning");
            tray.setMessage("enter your old password correctly");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
            //---------------------------------------------------------------------
        }

    }

    @FXML
    private void DeactivateAccount(ActionEvent event) throws IOException {

        ServiceUsers su = new ServiceUsers();
        System.err.println(UserSession.getId());
        su.supprimer(UserSession.getId());

        UserSession.cleanUserSession();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        Stage stage1 = (Stage) TFEmail.getScene().getWindow();
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
}
