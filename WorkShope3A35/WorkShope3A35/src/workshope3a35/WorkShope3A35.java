/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshope3a35;

import Model.Reclamation;
import Model.ReponseReclamation;
import Services.ServiceReclamation; 
import Services.ServiceReponseReclamation;
import java.time.LocalDate;
import java.util.Comparator;
import utils.MyDb;

/**
 *
 * @author mustapha
 */
public class WorkShope3A35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
ServiceReclamation sp =  new ServiceReclamation();  

//date
java.util.Date dt = new java.util.Date();
           java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String currentTime = sdf.format(dt);
           java.time.LocalDate.now();
//

//Reclamation p2 = new Reclamation(2, "currentTime", "currentTime", java.time.LocalDate.of(2017, 7, 11).toString(), "currentTime");
  Reclamation p2 = new Reclamation(90,"azertyui" , "azerty" , java.time.LocalDate.now().toString() , "stou");
  ServiceReponseReclamation rp = new ServiceReponseReclamation();
        ReponseReclamation r1 = new ReponseReclamation(42, "123", 92);
 
  
//sp.ajouter(p2);
//sp.modifier(p2);
//sp.supprimer(p2);


//rp.ajouter(r1);
//rp.modifier(r1);
//rp.supprimer(r1);


   

        System.out.println(sp.afficher().toString());
System.out.println(rp.afficher().toString());
    
    
    }
   
}
