package services.comment;

import IServices.commentaire.IServiceCommentaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import model.Commentaire;
import model.Users;
import services.user.ServiceUsers;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import IServices.user.IServiceUser;

public class ServiceCommentaire implements IServiceCommentaire<Commentaire> {
    private Connection cnx = MyDb.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire commentaire) {


            String query = "INSERT INTO commentaire (produits_id, nom,email, description,rating,created_at) VALUES (?, ?, ?, ?, ?, ?);";
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
               stm.setInt(1,commentaire.getIdProduit());
                stm.setString(2, commentaire.getNomUser());
                stm.setString(3, commentaire.getEmailUser());
                stm.setString(4, commentaire.getDescription());
                stm.setInt(5,commentaire.getRating());
                stm.setDate(6, MyDb.getSqlDate(commentaire.getCreatedAt()));


                stm.executeUpdate();
                System.out.println(" ==> ajouter avec avec succés");
            } catch(SQLException se) {
                se.printStackTrace();
            }

    }

    @Override
    public List<Commentaire> afficher() {


        ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();


        try {
            String qery="SELECT * FROM `commentaire` ";
            Statement stm =cnx.createStatement();
            ResultSet rs=stm.executeQuery(qery);

            while (rs.next()){
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setIdProduit(rs.getInt("produits_id"));
                c.setNomUser(rs.getString("nom"));
                c.setEmailUser(rs.getString("email"));
                c.setDescription(rs.getString("description"));
                c.setRating(rs.getInt("rating"));
                c.setCreatedAt(rs.getDate("created_at"));


                commentaires.add(c);
            }

            return  commentaires;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaires;

    }

    @Override
    public void modifier(Commentaire commentaire) {


        String query = "UPDATE commentaire SET produits_id=?, nom=?, email=?, description=? ,rating=?, created_at=? WHERE id=?;";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {

            stm.setInt(1, commentaire.getIdProduit());
            stm.setString(2, commentaire.getNomUser());
            stm.setString(3, commentaire.getEmailUser());
            stm.setString(4, commentaire.getDescription());
            stm.setInt(5, commentaire.getRating());
            stm.setDate(6, MyDb.getSqlDate(commentaire.getCreatedAt()));

            stm.setInt(7, commentaire.getId());

            stm.executeUpdate();
            System.out.println("Commentaire modifier avec succés  ");
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    @Override
    public void supprimer(Commentaire commentaire) {


        String query = "DELETE FROM commentaire WHERE id=?;";

        try(PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, commentaire.getId());

            preparedStatement.executeUpdate();
            System.out.println("commentaire avec l'id :" +commentaire.getId() + " est supprimer avec succés");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ObservableList<Commentaire> RechercheCommentaireAvance(String t) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM commentaire WHERE  (description like '" + t + "%')";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<Commentaire> ListR = FXCollections.observableArrayList();
        while (rs.next()) {
            Commentaire c = new Commentaire();
            c.setId(rs.getInt("id"));
            c.setNomUser(rs.getString("nom"));
            c.setEmailUser(rs.getString("email"));

            c.setDescription(rs.getString("description"));
            c.setRating(rs.getInt("rating"));
            c.setIdProduit(rs.getInt("produits_id"));
c.setCreatedAt(rs.getDate("created_at"));
            ListR.add(c);
        }
        return ListR;
    }

    public void SupprimerById(int id) {
        try {

            String query = "DELETE FROM `commentaire` WHERE id=" + id + ";";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public int commentCount() {
        int x=0;
        try {
            Statement stm=cnx.createStatement();
            String query = "SELECT count(*) as total FROM `commentaire`";
            ResultSet rst=stm.executeQuery(query);

            while (rst.next()){
                x=rst.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;

    }


}
