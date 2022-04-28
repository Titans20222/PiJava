/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mustapha
 */
public class MyDb {
   private  String URL ="jdbc:mysql://127.0.0.1:3306/pidev9999";
   private  String LOGIN ="root";
   private  String PASSWORD ="";
  static   private Connection cnx;
  static  private MyDb instanceconnection ;
    
   private MyDb(){
       
       
       try {
           cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
           System.out.println("Connexion mrigla ......");
       
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());      
       
       }
      
   }
   
  static public MyDb getInstance(){
       if(instanceconnection==null){
       instanceconnection= new MyDb();
       }
       return instanceconnection;//null
   }
  
  static public Connection getCnx (){
      return cnx;
  }
   
}
