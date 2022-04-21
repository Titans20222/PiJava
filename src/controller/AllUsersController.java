/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Users;
import services.user.ServiceUsers;
import utils.MyDb;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AllUsersController implements Initializable {
    private Connection cnx =MyDb.getInstance().getCnx();
public PreparedStatement st;
public ResultSet resultSet;
    private Connection connection =null;
    @FXML
    private JFXTextField txt_searchEmail;

    @FXML
    private JFXTextField txt_Nom;

    @FXML
    private JFXTextField txt_Prenom;

    @FXML
    private JFXTextField txt_Email;

    @FXML
    private JFXTextField txt_Mobile;
    @FXML
    private JFXTextField txt_id;
    @FXML
    private JFXTextField txt_Adresse;

    @FXML
    private JFXComboBox<String> rolesChoiceBox;;
    ObservableList ChoiceBoxlist = FXCollections.observableArrayList();
    @FXML
    private JFXPasswordField txt_Password;

    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, String> nomCol;

    @FXML
    private TableColumn<Users, String> prenomCol;

    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private TableColumn<Users, String> mobileCol;
    @FXML
    private TableColumn<Users, String> adresseCol;

    @FXML
    private TableColumn<Users, String> roleCol;
    @FXML
    private TableColumn<Users, Integer> idCol;
    @FXML
    private TableColumn<Users, String> passwordCol;


    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_edit;

    public ObservableList<Users> data= FXCollections.observableArrayList();
    private int id;

    @FXML
    void addUser() {
String nom=txt_Nom.getText();
String prenom=txt_Prenom.getText();
String email=txt_Email.getText();
String password=txt_Password.getText();
String mobile =txt_Mobile.getText();
String adresse =txt_Adresse.getText();
        boolean isVarified=true;
        String t = rolesChoiceBox.getValue().toString().toUpperCase();
        if (t.contains("ADMIN")) {
            t = "ADMIN";
        }
        String tt = "ROLE_" + t;
        String role = "[" + "\"" + tt + "\"]";
        if(!nom.equals("")&&!prenom.equals("")&&!email.equals("")&&!password.equals("")&&!mobile.equals("")&&!adresse.equals("")&&!role.equals("")) {
            try {


                ServiceUsers su = new ServiceUsers();
                su.ajouter(new Users(email, role, password, mobile, nom, prenom, adresse,isVarified));

                showUsers();
                txt_Nom.setText("");
                txt_Prenom.setText("");
                txt_Email.setText("");
                txt_Mobile.setText("");
                txt_Adresse.setText("");
                txt_Password.setText("");
                rolesChoiceBox.setValue("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Utilisateur ajouter avec succés", ButtonType.OK);
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {

            Alert alert =new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs !",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void deleteUser() {
        Users selected = table_users.getSelectionModel().getSelectedItem();
  id = selected.getId();




     ServiceUsers su = new ServiceUsers();
     su.supprimer(id);

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION," Utilisateur supprimé avec succés ",ButtonType.OK);
        alert.showAndWait();
     showUsers();



    }

    private void  setValueFromTable(){
        table_users.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Users u =table_users.getItems().get(table_users.getSelectionModel().getSelectedIndex());
                txt_Nom.setText(u.getNom());
                txt_Prenom.setText(u.getPrenom());
                txt_Email.setText(u.getEmail());
                txt_Adresse.setText(u.getAdresse());
                txt_Mobile.setText(u.getMobile());
                txt_Password.setText(u.getPassword());

                rolesChoiceBox.setValue(u.getRoles());
            }
        });

    }

    @FXML
    void editUser() {
        Users selected = table_users.getSelectionModel().getSelectedItem();
        id = selected.getId();
        txt_Nom.setText(selected.getNom());







txt_id.setText(Integer.toString(id));
        txt_Email.setText(selected.getEmail());
        txt_Prenom.setText(selected.getPrenom());
        txt_Adresse.setText(selected.getAdresse());
        txt_Mobile.setText(selected.getMobile());
        txt_Password.setText(selected.getPassword());

        rolesChoiceBox.getSelectionModel().select(selected.getRoles());
        ServiceUsers su = new ServiceUsers();

        String tt = "ROLE_" + rolesChoiceBox.getValue();
        String roles = "[" + "\"" + tt + "\"]";

boolean isVeriied=true;
        su.modifier(new Users(Integer.parseInt(txt_id.getText()),txt_Email.getText(),roles, txt_Password.getText(),txt_Mobile.getText(),txt_Nom.getText(),txt_Prenom.getText(),txt_Adresse.getText(),isVeriied));
showUsers();

     /*   String nom =txt_Nom.getText();
        String prenom =txt_Prenom.getText();
        String email=txt_Email.getText();
        String adresse=txt_Adresse.getText();
        String mobile =txt_Mobile.getText();
        String tt = "ROLE_" + rolesChoiceBox.getValue();
        String roles = "[" + "\"" + tt + "\"]";

       */
    }

    @FXML
    void searchUser() {
        String query="SELECT * FROM `users` WHERE email ='"+txt_searchEmail.getText()+"'";
          int em=0;
        try {
            st=cnx.prepareStatement(query);
            resultSet=st.executeQuery();
            if(resultSet.next()){
                txt_Nom.setText(resultSet.getString("nom"));
                txt_Prenom.setText(resultSet.getString("prenom"));
                txt_Mobile.setText(resultSet.getString("mobile"));
                txt_Adresse.setText(resultSet.getString("adresse"));
                txt_Email.setText(resultSet.getString("email"));
                txt_Password.setText(resultSet.getString("password"));
               rolesChoiceBox.setValue(resultSet.getString("roles").toString());
               em=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(em==0){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Aucun utilisateur trouver avec  "+txt_searchEmail.getText()+"", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    private void loadRolesChoiceBox() {
        ChoiceBoxlist.removeAll(ChoiceBoxlist);

        String client = "client";
        String artisan = "artisan";



        ChoiceBoxlist.addAll(client,artisan);
        rolesChoiceBox.getItems().addAll(ChoiceBoxlist);
        //subTotalView();
    }
    private void showUsers(){
        table_users.getItems().clear();
        String query="SELECT * FROM `users`";
        try {
            st= cnx.prepareStatement(query);
            resultSet=st.executeQuery();
            while (resultSet.next()){
                data.add(new Users(resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("roles"),
                        resultSet.getString("password"),
                        resultSet.getString("mobile"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),resultSet.getString("adresse"),

                        resultSet.getBoolean(8)));

            //
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

 nomCol.setCellValueFactory(new PropertyValueFactory<Users,String>("nom"));
     prenomCol.setCellValueFactory(new PropertyValueFactory<Users,String>("prenom"));

        emailCol.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<Users,String>("mobile"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Users,String>("adresse"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Users,String>("roles"));
        idCol.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        table_users.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        connection= MyDb.getInstance().getCnx();
        showUsers();
        loadRolesChoiceBox();
        setValueFromTable();
    }
    @FXML
    void mobile(KeyEvent event) {
        try {
            Integer.parseInt(event.getCharacter());
        }catch (NumberFormatException ex){
event.consume();
        }


    }

}
