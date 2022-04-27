/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author yasmi
 */
public class LigneDeCommande {
   private int id;
   private Commande commande_id;
   private Produit produit_id;
   private String numcommande,qte,prix,ligne;

    public LigneDeCommande() {
    }

    public LigneDeCommande(Commande commande_id, Produit produit_id, String numcommande, String qte, String prix, String ligne) {
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.numcommande = numcommande;
        this.qte = qte;
        this.prix = prix;
        this.ligne = ligne;
    }

    public LigneDeCommande(int id, Commande commande_id, Produit produit_id, String numcommande, String qte, String prix, String ligne) {
        this.id = id;
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.numcommande = numcommande;
        this.qte = qte;
        this.prix = prix;
        this.ligne = ligne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(Commande commande_id) {
        this.commande_id = commande_id;
    }

    public Produit getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Produit produit_id) {
        this.produit_id = produit_id;
    }

    public String getNumcommande() {
        return numcommande;
    }

    public void setNumcommande(String numcommande) {
        this.numcommande = numcommande;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "LigneDeCommande{" + "id=" + id + ", commande_id=" + commande_id + ", produit_id=" + produit_id + ", numcommande=" + numcommande + ", qte=" + qte + ", prix=" + prix + ", ligne=" + ligne + '}';
    }
   
}
