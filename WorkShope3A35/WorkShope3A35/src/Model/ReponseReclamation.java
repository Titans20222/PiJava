/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author mustapha
 */
public class ReponseReclamation {

private int id , reclamationId;
 private String  description  ;

    public ReponseReclamation() {
    }

    public ReponseReclamation(int id , String description,int reclamationId ) {
        this.id = id;
        this.reclamationId = reclamationId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


public int getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }



    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", description=" + description +", reclamationId=" + reclamationId +  "\n";
    }

    
}
