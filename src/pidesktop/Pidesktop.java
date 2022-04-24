/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pidesktop;

import Models.Evenement;
import Models.Reservation;
import Services.EvenementServices;
import Services.ReservationServices;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user1
 */
public class Pidesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  
     Evenement e = new Evenement();
//e.setId(1);
e.setNom("sss");
Date d =new Date();

//d.setTime(System.g);
//e.setDate(d);
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());  
e.setD(dateFormat);
e.setLieux("sss");
e.setNbrplaces(100);
e.setPrix(100);
  
 
     EvenementServices es =  new EvenementServices();
  
es.ajouter(e);
//es.afficher();
   // System.out.println(es.afficher().toString());  
 /*List<Evenement> evenements = new ArrayList();
evenements=es.afficher();
for(int i = 0 ; i < evenements.size(); i++)
{

 System.out.println(evenements.get(i).toString());
}*/
    Evenement e1 = new Evenement();
//e.setId(1);
e1.setId(38);
e1.setNom("aa2222");
//Date d =new Date();

//e.setDate(d);
  //      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
e1.setD(dateFormat);
e1.setLieux("tt22222");
e1.setNbrplaces(56);
e1.setPrix(201);  
es.modifier(e1);
es.supprimer(e1);



        ReservationServices rs =new ReservationServices();
     
Reservation r =new Reservation(0, 0, "adresse", 0, 33);
  // System.out.println(rs.afficher().toString());
rs.ajouter(r);
 List<Reservation> reservations = new ArrayList();
reservations=rs.afficher();
for(int i = 0 ; i < reservations.size(); i++)
{

 System.out.println(reservations.get(i).toString());
}
Reservation r1 =new Reservation(23, 1, "adresse1", 1, 1);
//rs.modifier(r1);
//rs.supprimer(r1);
}
   
}
