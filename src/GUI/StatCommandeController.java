/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import com.sun.scenario.effect.ImageData;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utils.database;
import  com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfReader;
//import java.text.SimpleDateFormat;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.image.Image;
//import javafx.scene.image.WritableImage;
//import javax.imageio.ImageIO;
//import java.awt.Desktop;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class StatCommandeController implements Initializable {
    
    @FXML
     private AnchorPane chartNode;
    @FXML
     private HBox chartHBox;
    
     public static int numeroPDF = 0;
     Document doc = new Document();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        detailleCommande();
    } 
    
     public ObservableList buildDataCommande() {
//     public  ObservableList<PieChart.Data> buildData() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;
             
        try {

            String requete = "SELECT commande.ville ,COUNT(commande.id) as nbr FROM commande group by commande.ville ";
            Connection cnx = database.getInstance().getCnx();
            java.sql.Statement pst = cnx.createStatement();
    
            rs = pst.executeQuery(requete);
            
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }

                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }
    
    private void detailleCommande() {
        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Les commandes ");
        stage.setWidth(600);
        stage.setHeight(600);

        final PieChart chart = new PieChart(buildDataCommande());
        final Label caption = new Label("");
        caption.setTextFill(Color.BLUEVIOLET);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            total = total + data.getPieValue();
        }
        final double totalFinal = total;

        for (final PieChart.Data data : chart.getData()) {
            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
                        ((Group) scene.getRoot()).getChildren().add(caption);
                    }
                }
            });
        }

        chart.setTitle("Les commandes par ville");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(chart);

    }
    
    
     public XYChart.Series buildDataLineChart() {
         XYChart.Series series = new XYChart.Series();
        series.setName("Nombre de commande par ville");

        ResultSet rs = null;
        XYChart.Series d;
        try {
            String requete = "SELECT commande.ville,COUNT(commande.id) as nbr FROM commande group by (commande.ville)";
             
            Connection cnx = database.getInstance().getCnx();
            java.sql.Statement pst = cnx.createStatement();
           // Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) 
            {
                    series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }
            
            return series;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataLineChart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return series;
    }
     
     @FXML
    private void globalChart(ActionEvent event) {

        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
            String requete = "SELECT commande.ville,COUNT(commande.id) as nbr FROM commande group by (commande.ville)";

             Connection cnx = database.getInstance().getCnx();
            java.sql.Statement pst = cnx.createStatement();
    
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }

                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);
     
        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataCommande");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    }
  @FXML
    private void lineChart(ActionEvent event) {
        
      double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nombre de commande par ville");
        stage.setWidth(600);
        stage.setHeight(600);


        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Ville");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                

        lineChart.getData().add(buildDataLineChart());
       ((Group) scene.getRoot()).getChildren().add(lineChart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(lineChart);
        
    }

    
     @FXML
    private void convertirPDF(ActionEvent event)  {  //throws FileNotFoundException, IOException
                String nom = "Graph statistique " + numeroPDF + ".pdf";

        try{ 
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\OneDrive\\Bureau\\Graphe\\"+ nom));
        doc.open();
        doc.addTitle("");
        doc.add(new Paragraph(""));
        doc.close();
        
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
     /*   numeroPDF = numeroPDF + 1;
        String nom = "Graph statistique " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            WritableImage wimg = chartNode.snapshot(new SnapshotParameters(), null);
            File file = new File("ChartSnapshot.png");
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);
//C:\Users\yasmi\OneDrive\Bureau\Graphe
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\OneDrive\\Bureau\\Graphe\\"+ nom));
          
            
           Desktop.getDesktop().open(new File(System.getProperty("user.home")+ "\\OneDrive\\Bureau\\Graphe\\" + nom));
//            Desktop.getDesktop().open(new File(System.getProperty() + "\\Desktop\\" + nom));
            writer.close();
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }*/
    }
        @FXML
    private void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
}
