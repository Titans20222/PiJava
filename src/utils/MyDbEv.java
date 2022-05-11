package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbEv {

  final   String URL="jdbc:mysql://127.0.0.1:3306/pidev4";
    final String LOGIN="root";
    final String PASSSWORD="";

    static private Connection cnx;
  static  private MyDbEv instance;

    private   MyDbEv(){

      try {
        cnx= DriverManager.getConnection(URL,LOGIN,PASSSWORD);
        System.out.println("Connexion reussie");
      } catch (SQLException e) {
        System.out.println(  e.getMessage());

      }
    }
   static public MyDbEv getInstance(){
      if (instance==null)
      instance= new MyDbEv();
      return instance;
    }
    static public Connection getCnx(){
return  cnx;
    }


    public static java.sql.Date getSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
