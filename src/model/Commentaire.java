package model;


import java.util.Date;

public class Commentaire {

    private int id,idProduit;
    private String nomUser,emailUser,description;
    private int rating;
    private Date createdAt;

    public Commentaire() {

    }

    public Commentaire(int idProduit, String nomUser, String emailUser, String description, int rating) {
        this.idProduit = idProduit;
        this.nomUser = nomUser;
        this.emailUser = emailUser;
        this.description = description;
        this.rating = rating;
    }

    public Commentaire(int id, int idProduit, String nomUser, String emailUser, String description, int rating, Date createdAt) {
        this.id = id;
        this.idProduit = idProduit;
        this.nomUser = nomUser;
        this.emailUser = emailUser;
        this.description = description;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", idProduit=" + idProduit +
                ", nomUser='" + nomUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                "\n";
    }
}
