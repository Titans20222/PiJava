package services;

import IServices.IStatistique;
import model.Users;
import utils.MyDb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatistiqueService implements IStatistique {


    Connection connexion;
    PreparedStatement ps;

    public StatistiqueService() {
        connexion = MyDb.getCnx();
    }

    @Override
    public int getNombreClients() {
        String sql = "SELECT count(*) FROM users WHERE roles = '[\"ROLE_CLIENT\"]\n'";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }

    @Override
    public int getNombreArtisans() {
        String sql = "SELECT count(*) FROM users WHERE roles = '[\"ROLE_ARTISAN\"]\n'";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }

    @Override
    public int getNombreAdministrateurs() {
        String sql = "SELECT count(*) FROM users WHERE roles = '[\"ROLE_ADMIN\"]\n'";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }
    public int getNombreDisabledAccount() {
        String sql = "SELECT count(*) FROM users WHERE is_deleted = 'false '";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }

    public int getNombreActivatedAccount() {
        String sql = "SELECT count(*) FROM users WHERE is_deleted = 'true '";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }
    public int getNombreUsers() {
        String sql = "SELECT count(*) FROM users ";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }

}
