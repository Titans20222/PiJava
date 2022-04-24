/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.jws.Oneway;

/**
 *
 * @author user1
 */

public class Evenement implements Serializable{
private int nbplacesreserver;
private int id;
private String nom;
private Date date;
private float prix;
private String lieux;
private int nbrplaces;
private String d ;


    public Evenement() {
    }

    public Evenement(int id, String nom, Date date, float prix, String lieux, int nbrplaces) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.prix = prix;
        this.lieux = lieux;
        this.nbrplaces = nbrplaces;
    }

    public int getNbplacesreserver() {
        return nbplacesreserver;
    }

    public void setNbplacesreserver(int nbplacesreserver) {
        this.nbplacesreserver = nbplacesreserver;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public int getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(int nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String toString() {
   return 
	  "  Nom evenement" +" "+ this.nom+
	  ",  place ou se deroule " +" "+ this.lieux
+ ", date "+ " "+this.date
+
 ", nombre des places disponible"+" "+this.nbrplaces 
+ ", prix "+" "+this.prix  
;
}
}
