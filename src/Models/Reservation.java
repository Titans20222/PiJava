/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author user1
 */
public class Reservation {
    private int id ;
private int nbrplaces;
private String adresse;
private int numtel;
private int idevenement ;

    public Reservation(int id, int nbrplaces, String adresse, int numtel, int idevenement) {
        this.id = id;
        this.nbrplaces = nbrplaces;
        this.adresse = adresse;
        this.numtel = numtel;
        this.idevenement = idevenement;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(int nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }
  public String toString() {
   return "Reservation numero " + this.id +
	  " : Nbrplaces " + this.nbrplaces+
	  ",  adresse email " + this.adresse;
}

}
