/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Reclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import utils.MyDb;

/**
 *
 * @author mustapha
 */
public class ServiceReclamation implements IService<Reclamation>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Reclamation t) {
    try {
        String querry= "INSERT INTO reclamation(`titre`, `description` ,`date` ,`type`) VALUES ('"+t.getTitre()+"','"+t.getDescription()+"', '"+t.getDate()+"' ,'"+t.getType()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Reclamation> afficher() {
     List<Reclamation> reclamations = FXCollections.observableArrayList();//new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reclamation`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reclamation p = new Reclamation();
            
            p.setId(rs.getInt(1));
            p.setTitre(rs.getString("titre"));
 p.setDescription(rs.getString("description"));
p.setDate(rs.getString("date"));
            p.setType(rs.getString("type"));
            reclamations.add(p);
        }
        
        
        
        return reclamations;
    } catch (SQLException ex) {
        }
    return reclamations;
    }

    @Override
    public void modifier(Reclamation t) {
     
   try {
        String querry= "UPDATE reclamation SET `titre`='"+t.getTitre()+"', `description`='"+t.getDescription()+"' ,`date`='"+t.getDate()+"' ,`type`='"+t.getType()+"' where id='"+t.getId()+"'";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }

  }

    @Override
    public void supprimer(Reclamation t) {
       
try {
        String querry= "DELETE FROM reclamation where id='"+t.getId()+"'";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }


  }
   public List<Reclamation> Recherche(String nom) {

        return afficher().stream().filter(a -> a.getTitre().equals(nom)).collect(Collectors.toList());

    }
 

public List<Reclamation> sortByDescription(){
         List<Reclamation> reclamations=afficher();
         List<Reclamation> resultat=reclamations.stream().sorted(Comparator.comparing(Reclamation::getDescription)).collect(Collectors.toList());
         return resultat;
     }

}
