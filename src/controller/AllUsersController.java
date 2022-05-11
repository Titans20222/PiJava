/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Users;

//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.StatistiqueService;
import services.user.ServiceUsers;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
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
    @FXML
    private PieChart pieChart;
    @FXML
    private JFXButton btnExport;
    public ObservableList<Users> data= FXCollections.observableArrayList();
    private int id;
    private int nbrArtisan, nbrClient,nbrAdmin ;
StatistiqueService sa =new StatistiqueService();

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
                txt_id.setText(Integer.toString(u.getId()));
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

      /*  String tt = "" + rolesChoiceBox.getValue();
        String roles = "[" + "\"" + tt + "\"]";*/

boolean isVeriied=true;
        Users users=   new Users(Integer.parseInt(txt_id.getText()),txt_Email.getText(),rolesChoiceBox.getValue(), txt_Password.getText(),txt_Mobile.getText(),txt_Nom.getText(),txt_Prenom.getText(),txt_Adresse.getText(),isVeriied);

        su.modifier(users);
System.out.println(users);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle("Update  SUCCESS");
        tray.setMessage("User  updated succesfully");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
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
        try {
            piechart();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        btnExport.setOnAction(event -> {
//            String query="SELECT * FROM `users`";
//            try {
//                st= cnx.prepareStatement(query);
//                resultSet=st.executeQuery();
//                XSSFWorkbook wb=new XSSFWorkbook();
//                XSSFSheet sheet =wb.createSheet("Users List ");
//                XSSFRow header = sheet.createRow(0);
//                header.createCell(0).setCellValue("id");
//                header.createCell(1).setCellValue("email");
//                header.createCell(2).setCellValue("roles");
//                header.createCell(3).setCellValue("password");
//                header.createCell(4).setCellValue("mobile");
//                header.createCell(5).setCellValue("nom");
//                header.createCell(6).setCellValue("prenom");
//                header.createCell(7).setCellValue("adresse");
//                int index=1;
//                while (resultSet.next()){
//                    XSSFRow row =sheet.createRow(index);
//                    row.createCell(0).setCellValue(resultSet.getInt("id"));
//                    row.createCell(1).setCellValue(resultSet.getString("email"));
//row.createCell(2).setCellValue(resultSet.getString("roles"));
//
//                  row.createCell(3).setCellValue(resultSet.getString("password"));
//
//                  row.createCell(4).setCellValue(resultSet.getString("mobile"));
//
//                  row.createCell(5).setCellValue(resultSet.getString("nom"));
//   row.createCell(6).setCellValue(resultSet.getString("nom"));
//   row.createCell(7).setCellValue(resultSet.getString("prenom"));
// row.createCell(8).setCellValue(resultSet.getString("adresse"));
//
//
//                        index++;
//
//                    //
//                }
//                FileOutputStream fileOut = new FileOutputStream("usersList.xlsx");
//                wb.write(fileOut);
//                fileOut.close();
//                TrayNotification tray = new TrayNotification();
//                AnimationType type = AnimationType.POPUP;
//
//                tray.setAnimationType(type);
//                tray.setTitle("UPLOAD  SUCCESS");
//                tray.setMessage("Users  Has Exported in excel");
//                tray.setNotificationType(NotificationType.SUCCESS);
//                tray.showAndDismiss(Duration.millis(3000));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        });
    }
    @FXML
    void mobile(KeyEvent event) {
        try {
            Integer.parseInt(event.getCharacter());
        }catch (NumberFormatException ex){
event.consume();

        }


    }
    public void piechart() throws SQLException{

        //----------------PieChart----------
        nbrArtisan =sa.getNombreDisabledAccount();
        nbrAdmin=sa.getNombreActivatedAccount();
        nbrClient=sa.getNombreUsers();
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Disabled", nbrArtisan),
                new PieChart.Data("Enabled",nbrClient),
                new PieChart.Data("AllAcount", nbrAdmin)

        );

        pieChart.setTitle("User par role");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() *10));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }
}
