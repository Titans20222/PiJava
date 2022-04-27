package controller.frontoffcie;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Users;
import services.user.ServiceUsers;
import tray.notification.NotificationType;
import utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class resetPController implements Initializable {
    @FXML
    private AnchorPane layer;

    @FXML
    private Pane signInPane;

    @FXML
    private JFXTextField verif;

    @FXML
    private JFXPasswordField password2;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXButton confirmer;

    @FXML
    private JFXButton annuler;

    @FXML
    private Label email;

    @FXML
    private Label erreur;

    public static String code;
    public static AnchorPane blur;
    public static Users u;

    public static void set(String codeVerif, Users user, AnchorPane blurr) {
        u = user;
        code = codeVerif;
        blur = blurr;
    }

    @FXML
    void annulerClicked(MouseEvent event) {
        Stage s = (Stage) annuler.getScene().getWindow();
        blur.setEffect(null);
        s.close();
    }

    @FXML
    void confirmerClicked(MouseEvent event) {
        if (!verif.getText().equals("") && !password1.getText().equals("") && !password2.getText().equals("")) {
            if (!password1.getText().equals(password2.getText())) {
                erreur.setText("Les mots de passe doivent être identiques");
            } else if (!verif.getText().equals(code)) {
                erreur.setText("Code de vérification incorrect");
            } else {
                ServiceUsers us = new ServiceUsers();
                if (us.changerMdp(u.getId(), password1.getText())) {
                    Utils.showTrayNotification(NotificationType.NOTICE, "Votre mot de passe est changé", null, null, "", 5000);
                }
                Stage s = (Stage) annuler.getScene().getWindow();
                blur.setEffect(null);
                s.close();
            }

        } else {
            erreur.setText("Veuillez renseigner tous les champs");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (u != null) {
            email.setText(u.getEmail());
            erreur.setText("");
            blur.setEffect(new GaussianBlur(5));
        }
    }
}