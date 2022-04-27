/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author yasmi
 */
public class Commande {
     private int id,idUser;
    private String numc,ville,adresselivraison,prix_total;
    private String datecommande; 

    public Commande() {
    }

    public Commande(String numc, String datecommande, String ville, String adresselivraison) {
 
        this.numc = numc;
        this.ville = ville;
        this.adresselivraison = adresselivraison;
        this.datecommande = datecommande;
    }

    public Commande(int id, int idUser, String numc, String ville, String adresselivraison, String prix_total, String datecommande) {
        this.id = id;
        this.idUser = idUser;
        this.numc = numc;
        this.ville = ville;
        this.adresselivraison = adresselivraison;
        this.prix_total = prix_total;
        this.datecommande = datecommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNumc() {
        return numc;
    }

    public void setNumc(String Numc) {
        this.numc = Numc;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresselivraison() {
        return adresselivraison;
    }

    public void setAdresselivraison(String adresselivraison) {
        this.adresselivraison = adresselivraison;
    }

    public String getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(String prix_total) {
        this.prix_total = prix_total;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idUser=" + idUser + ", Numc=" + numc + ", ville=" + ville + ", adresselivraison=" + adresselivraison + ", prix_total=" + prix_total + ", datecommande=" + datecommande + '}';
    }
    
    
}
