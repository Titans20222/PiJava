package controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.user.UserSession;

/**
 * FXML Controller class
 *

 */
public class HomePageHolderController implements Initializable {

    private Label label;
    @FXML
    private AnchorPane slider;
    double xOffset, yOffset;
    private Stage stage;
    //@FXML
    // private AnchorPane taskSideBar;
    @FXML
    private AnchorPane eventSideBar;
    @FXML
    private AnchorPane sessionSideBar;
    @FXML
    private AnchorPane recipeSideBar;
    @FXML
    private AnchorPane bookSideBar;
    @FXML
    private AnchorPane homeSideBar;
    @FXML
    private AnchorPane profileSlider;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label userName;
    @FXML
    private Label userEmail;
    @FXML
    private Label userName1;
    @FXML
    private AnchorPane profileSideBar;
    @FXML
    private GridPane taskGrid;
    @FXML
    private AnchorPane reportsSideBar;
    @FXML
    private FontAwesomeIconView minIcon;
    @FXML
    private FontAwesomeIconView closeIcon;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeSideBar.getStyleClass().add("selectedMenu");


        profileSideBar.getStyleClass().add("unselectedMenu");

        userName1.setText(UserSession.getNom() + " " + UserSession.getPrenom());
        userEmail.setText(UserSession.getEmail());
     /*   if (UserSession.getRoles().equals("[\"ROLE_ARTISAN\"]")) {
            reportsSideBar.setVisible(true);
        } else {
            reportsSideBar.setVisible(false);
        }*/
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/Home.fxml")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    private void profilePageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(490);
        slide.play();
        profileMenu();
    }
    public void profileMenu() throws IOException {
//        reportsSideBar.getStyleClass().removeAll(reportsSideBar.getStyleClass());
        reportsSideBar.getStyleClass().add("menu");
        reportsSideBar.getStyleClass().add("unselectedMenu");

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
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/user/ProfileUser.fxml")));
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
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) label.getScene().getWindow();
        stage.close();
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

    @FXML
    private void homePageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(0);
        slide.play();
        homeMenu();
    }

    public void homeMenu() throws IOException {
        //  taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        // taskSideBar.getStyleClass().add("menu");
        // taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");



        // bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        // bookSideBar.getStyleClass().add("menu");
        // bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("selectedMenu");


        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/GUI/allUsers.fxml")));
    }
    @FXML
    private void signOutAction(MouseEvent event) throws IOException {
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


}
