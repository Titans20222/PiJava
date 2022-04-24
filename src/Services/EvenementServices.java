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
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user1
 */
public class EvenementServices  implements IService<Evenement> {
 private Connection cnx = MyDb.getInstance().getCnx() ;   


   @Override
    public void ajouter(Evenement t) {
    try {
        String querry= "INSERT INTO evenement(`nom_evenement`,`date`,`nbrplacedispo`,`nom_lieu`,`prix` ) VALUES ('"+t.getNom()+"','"+t.getD()+"','"+t.getNbrplaces()+"','"+t.getLieux()+"','"+t.getPrix()+"')";
        Statement stm = cnx.createStatement();
        System.out.println("un evenement est ajouter avec success");

    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public ObservableList<Evenement> afficher() {
     ObservableList<Evenement> evenements = FXCollections.observableArrayList(); 
        try {
       
        String querry ="SELECT * FROM `evenement`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Evenement e = new Evenement();
            
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setDate(rs.getDate(3));
e.setNbplacesreserver(7);

//e.setDate(rs.getS);
e.setNbrplaces(rs.getInt(4));
e.setLieux(rs.getString(5));
e.setPrix(rs.getFloat(6));
evenements.add(e);
        }
        
        
        
        return evenements;
    } catch (SQLException ex) {
        }
    return evenements;
    }

    @Override
    public void modifier(Evenement t) {
try {
        String querry= "UPDATE evenement SET `nom_evenement`='"+t.getNom()+"',`date`='"+t.getD()+"',`nbrplacedispo`='"+t.getNbrplaces()+"',`nom_lieu`='"+t.getLieux()+"',`prix`='"+t.getPrix()+"' where id='"+t.getId()+"'"; 
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
        System.out.println("mise a jour avec success");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
            
   }
 public void modifiernbp(int id,int nb) {
try {
        String querry= "UPDATE evenement SET `nbrplacedispo`=nbrplacedispo-'"+nb+"', `nbplacesreserver`=nbplacesreserver+'"+nb+"' where id="+id; 
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
        System.out.println("mise a jour avec success");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
            
   }

    @Override
    public void supprimer(Evenement t) {
try {
        String querry= "DELETE FROM evenement   where id='"+t.getId()+"'"; 
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
        System.out.println("suppression avec success");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
         }
     @Override
public ObservableList<Evenement> chercher(int id){
return null;
}

public ObservableList<Evenement> afficherparNom(String nom) {
     ObservableList<Evenement> evenements = FXCollections.observableArrayList(); 
        try {
            System.out.println(nom);
        String querry ="SELECT * FROM `evenement` where nom_evenement like'"+'%'+nom+'%'+"'";
        Statement stm = cnx.createStatement();                  
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Evenement e = new Evenement();
            System.out.println(rs.getInt(1));
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setDate(rs.getDate(3));

//e.setDate(rs.getS);
e.setNbrplaces(rs.getInt(4));
e.setLieux(rs.getString(5));
e.setPrix(rs.getFloat(6));
evenements.add(e);
            System.out.println(e);
        }
        
        
        
        return evenements;
    } catch (SQLException ex) {
        }
    return evenements;
    }
}