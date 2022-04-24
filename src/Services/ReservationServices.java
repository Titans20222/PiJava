/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.Evenement;
import Models.Reservation;
import Utils.MyDb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user1
 */
public class ReservationServices implements IService<Reservation>{
    private Connection cnx = MyDb.getInstance().getCnx() ;   


   @Override
    public void ajouter(Reservation t) {
    try {
        String querry= "INSERT INTO reservation(`idevenement_id`,`nbrplace`,`adresseemail`,`numtel` ) VALUES ('"+t.getIdevenement()+"','"+t.getNbrplaces()+"','"+t.getAdresse()+"','"+t.getNumtel()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
        System.out.println("une reservation est ajoutée avec success");
EvenementServices es=new EvenementServices();
es.modifiernbp(t.getIdevenement(), t.getNbrplaces());
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

 public  ObservableList<Reservation> chercher(String email, int id) {
     ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try {
       
        String querry ="SELECT * FROM `reservation` where idevenement_id='"+id+"' and adresseemail like'"+'%'+email+'%'+"'";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reservation r = new Reservation();
            
r.setId(rs.getInt(1));
r.setIdevenement(rs.getInt(2));
r.setNbrplaces(rs.getInt(3));
r.setAdresse(rs.getString(4));
r.setNumtel(rs.getInt(5));
//  e.setId(rs.getInt(1));
            //e.setNom(rs.getString(2));
           // e.setPrenom(rs.getString(3));

//e.setDate(rs.getS);
//e.setNbrplaces(rs.getInt(4));
//e.setLieux(rs.getString(5));
//e.setPrix(rs.getFloat(6));
//evenements.add(e);
reservations.add(r);
   }
        
        
        
        return reservations;
    } catch (SQLException ex) {
        }
    return reservations;
    }


    @Override
    public List<Reservation> afficher() {
     List<Reservation> reservations = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reservation`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reservation r = new Reservation();
            
r.setId(rs.getInt(1));
r.setIdevenement(rs.getInt(2));
r.setNbrplaces(rs.getInt(3));
r.setAdresse(rs.getString(4));
r.setNumtel(rs.getInt(5));
//  e.setId(rs.getInt(1));
            //e.setNom(rs.getString(2));
           // e.setPrenom(rs.getString(3));

//e.setDate(rs.getS);
//e.setNbrplaces(rs.getInt(4));
//e.setLieux(rs.getString(5));
//e.setPrix(rs.getFloat(6));
//evenements.add(e);
reservations.add(r);
   }
        
        
        
        return reservations;
    } catch (SQLException ex) {
        }
    return reservations;
    }

    @Override
    public void modifier(Reservation t) {
try {
        String querry= "UPDATE reservation SET `nbrplace`='"+t.getNbrplaces()+"',`adresseemail`='"+t.getAdresse()+"',`numtel`='"+t.getNumtel()+"' where id='"+t.getId()+"'"; 
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    System.out.println("mise a jour avec success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
            
   }

    @Override
    public void supprimer(Reservation t) {
try {
        String querry= "DELETE FROM reservation  where id='"+t.getId()+"'"; 
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    System.out.println("suppresion avec success");
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
         }
 @Override
public ObservableList<Reservation> chercher(int id){
 ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try {
       
        String querry ="SELECT * FROM `reservation` where idevenement_id='"+id+"' ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reservation r1 = new Reservation();
            
r1.setId(rs.getInt(1));
r1.setIdevenement(rs.getInt(2));
r1.setNbrplaces(rs.getInt(3));
r1.setAdresse(rs.getString(4));
r1.setNumtel(rs.getInt(5));
//  e.setId(rs.getInt(1));
            //e.setNom(rs.getString(2));
           // e.setPrenom(rs.getString(3));

//e.setDate(rs.getS);
//e.setNbrplaces(rs.getInt(4));
//e.setLieux(rs.getString(5));
//e.setPrix(rs.getFloat(6));
//evenements.add(e);
reservations.add(r1);
            System.out.println(r1.toString());
  }
        
        
        
        return reservations;
    } catch (SQLException ex) {
        }
    return reservations;
    }
}

