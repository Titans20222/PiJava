/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.ReponseReclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class ServiceReponseReclamation implements IIService<ReponseReclamation>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
@Override
    public void ajouter(ReponseReclamation r) {
    try {
        String querry= "INSERT INTO reponse_reclamation( `description` , `reclamation_id`   ) VALUES ('"+r.getDescription()+"' , '"+r.getReclamationId()+"' )";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }
@Override
    public List<ReponseReclamation> afficher() {
     List<ReponseReclamation> reponsesreclamations = FXCollections.observableArrayList();//new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reponse_reclamation`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            ReponseReclamation p = new ReponseReclamation();
            
            p.setId(rs.getInt(1));
 p.setDescription(rs.getString("description"));
 p.setReclamationId(rs.getInt("reclamation_id"));
            reponsesreclamations.add(p);
        }
        
        
        
        return reponsesreclamations;
    } catch (SQLException ex) {
        }
    return reponsesreclamations;
    }

@Override
    public void modifier(ReponseReclamation r) {
     
   try {
        String querry= "UPDATE reponse_reclamation SET  `description`='"+r.getDescription()+"' where id='"+r.getId()+"'";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }

  }

@Override
    public void supprimer(ReponseReclamation r) {
       
try {
        String querry= "DELETE FROM reponse_reclamation where id='"+r.getId()+"' ";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }


  }
//     public List<ReponseReclamation> Recherche(int nom) {
//
//        return afficher().stream().filter(a -> a.getReclamationId().equals(nom)).collect(Collectors.toList());
//
//    }
}

    

