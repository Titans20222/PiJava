package services.user;

import model.Users;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import IServices.user.IServiceUser;

public class ServiceUsers implements IServiceUser<Users> {

    private Connection cnx =MyDb.getInstance().getCnx();



  /*  @Override
    public void ajouter(Users users) {

        String querry="INSERT INTO `users`( `email`, `roles`, `password`, `photo`, `nom`, `prenom`, `adresse`, `is_verified`) VALUES ('"+users.getEmail()+"','"+users.getRoles()+"','"+users.getPassword()+"','"+users.getPhoto()+"','"+users.getNom()+"','"+users.getPrenom()+"','"+users+ users.getAdresse()+"','"+users.isVerified()+"')";
        try {
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

   
    @Override
    public void ajouter(Users users) {
        String query = "INSERT INTO users (email, roles, password,mobile, nom,prenom,adresse,is_verified) VALUES (?, ?, ?, ?, ?, ?, ?,?);";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {

            stm.setString(1, users.getEmail());
            String t = users.getRoles().toString().toUpperCase();
            if (t.contains("ADMIN")) {
                t = "ADMIN";
            }
            String tt = "ROLE_" + t;
            String roles = "[" + "\"" + tt + "\"]";
            stm.setString(2, users.getRoles());

            stm.setString(3, users.getPassword());
            stm.setString(4, users.getMobile());
            stm.setString(5, users.getNom());
            stm.setString(6, users.getPrenom());
            stm.setString(7, users.getAdresse());
            stm.setBoolean(8, users.isVerified());

            stm.executeUpdate();
            System.out.println("utilisateur ajouter avec succés");
        } catch(SQLException se) {
            se.printStackTrace();
        }
         /*finally {
            try {
                cnx.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }*/
    }

    @Override
    public List<Users> afficher() {
List<Users> users = new ArrayList<>();


        try {
            String qery="SELECT * FROM `users` ";
            Statement stm =cnx.createStatement();
            ResultSet rs=stm.executeQuery(qery);

            while (rs.next()){
 Users u = new Users();
 u.setId(rs.getInt(1));
 u.setNom(rs.getString("nom"));
 u.setPrenom(rs.getString("prenom"));
 u.setEmail(rs.getString("email"));
 u.setAdresse(rs.getString("adresse"));
 u.setRoles(rs.getString("roles"));
                u.setPassword(rs.getString("password"));
                u.setMobile(rs.getString("mobile"));


 users.add(u);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void modifier(Users users) {

            String query = "UPDATE users SET email=?, roles=?, password=?, mobile=?, nom=?, prenom=?, adresse=?, is_verified=? WHERE id=?;";
            try (PreparedStatement stm = cnx.prepareStatement(query)) {

                stm.setString(1, users.getEmail());
                stm.setString(2, users.getRoles());
                stm.setString(3, users.getPassword());
                stm.setString(4, users.getMobile());
                stm.setString(5, users.getNom());
                stm.setString(6, users.getPrenom());
                stm.setString(7, users.getAdresse());
                stm.setBoolean(8, users.isVerified());
                stm.setInt(9, users.getId());

                stm.executeUpdate();
                System.out.println("modifier avec succés  ");
            } catch (SQLException se) {
                se.printStackTrace();
            }
            /*finally {
                try {
                    cnx.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }*/


    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM users WHERE id=?;";

        try(PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            System.out.println("user avec l'id :" +id + " est supprimer avec succés");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void searchByName(String nom) {
       Users u = null;
        String query ="SELECT * FROM `users` WHERE nom = '" + nom + "'";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {

                u = new Users(rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("password"), rs.getString("mobile"), nom, rs.getString("prenom"),
                        rs.getString("adresse"), rs.getBoolean("is_verified"));

          System.out.println("%-15s%-20s%5s\n"+u);
               // System.out.println("%-15s%20s%5s" + u);
               // System.out.println("\n" + u);
            }
else System.out.println("Ce nom n'existe pas  ......!");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void orderByName() throws SQLException {
        Users u=null;

            String query ="SELECT * FROM users ORDER BY NOM ASC";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                u = new Users(rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("password"), rs.getString("mobile"),rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("adresse"), rs.getBoolean("is_verified"));

                System.out.println("%-15s%-20s%5s\n"+u);
            }


    }

    
    //---------login----------------------------------------------------
    @Override
    public boolean Validate_Login(String email , String password){           
        try {
            String query = "SELECT * FROM users WHERE email = ? and password = ? and is_verified=0 ";
            // Step 2:Create a statement using connection object
            PreparedStatement pS = cnx.prepareStatement(query);
            pS.setString(1, email);
            pS.setString(2, password);            
            ResultSet resultSet = pS.executeQuery();
            
            if (resultSet.next()) {
                //recuperation d'id de user loged in
                int ID = resultSet.getInt("id") ;
                String EMAIL =resultSet.getString("email");
                String ROLES =resultSet.getString("roles");
                String PASSWORD =resultSet.getString("password");
                String MOBILE =resultSet.getString("mobile");
                String NOM =resultSet.getString("nom");
                String PRENOM =resultSet.getString("prenom");
                String ADRESSE =resultSet.getString("adresse");
                String ROLE =resultSet.getString("roles");

Boolean ISVERIFIED=resultSet.getBoolean("is_verified");
         
                //user session created with user id when logging in
                //
                String req="select roles from users where id="+ID+"";
                Statement stm=cnx.createStatement();
                ResultSet resultSet2 = stm.executeQuery(req);

                if(resultSet2.next()){
                   String role =resultSet2.getString("roles");
                   UserSession.getInstance(ID,EMAIL,ROLE,PASSWORD,MOBILE,NOM,PRENOM,ADRESSE,ISVERIFIED);
                }
                
                return true;
            }
            

        } catch (SQLException ex) {
            // print SQL exception information
            printSQLException(ex);
        }
        return false;
    }
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void modifierUser(int id) {

        String query = "UPDATE users SET email=?, roles=?, password=?, mobile=?, nom=?, prenom=?, adresse=?, is_verified=? WHERE id=?;";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
Users users=null;
            stm.setString(1, users.getEmail());
            stm.setString(2, users.getRoles());
            stm.setString(3, users.getPassword());
            stm.setString(4, users.getMobile());
            stm.setString(5, users.getNom());
            stm.setString(6, users.getPrenom());
            stm.setString(7, users.getAdresse());
            stm.setBoolean(8, users.isVerified());
            stm.setInt(9, id);

            stm.executeUpdate();
            System.out.println("modifier avec succés  ");
        } catch (SQLException se) {
            se.printStackTrace();
        }
            /*finally {
                try {
                    cnx.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }*/


    }
    public void ModifierUser(Users u ,int id) {
        try {
            String query = "UPDATE `users` SET `email`= '"+u.getEmail()+"' ,"
                    + "`nom`= '"+u.getNom()+"' ,`prenom`= '"
                    +u.getPrenom()+"', `adresse` ='"+u.getAdresse()+"',`mobile`= '"+u.getMobile()+"' "
                    + " WHERE id = "+id+" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User updated succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //---------------------------------------------
    public void ModifierUserPassword(String pw ,int id) {
        try {
            String query = "UPDATE `users` SET `password`='"+pw+"' WHERE id = "+id+" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User password updated succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    //---------------------------------------------

    public void DeleteUser(int idU) {
        try {
            String query = "UPDATE  user set  is_verified=1  where user_id=" + idU +" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User deleted succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
