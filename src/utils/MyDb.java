package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDb {

  final   String URL="jdbc:mysql://127.0.0.1:3306/pide8";
    final String LOGIN="root";
    final String PASSSWORD="";

    static private Connection cnx;
  static  private MyDb instance;

    private   MyDb(){

      try {
        cnx= DriverManager.getConnection(URL,LOGIN,PASSSWORD);
        System.out.println("Connexion reussie");
      } catch (SQLException e) {
        System.out.println(  e.getMessage());

      }
    }
   static public MyDb getInstance(){
      if (instance==null)
      instance= new MyDb();
      return instance;
    }
    static public Connection getCnx(){
return  cnx;
    }


    public static java.sql.Date getSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
