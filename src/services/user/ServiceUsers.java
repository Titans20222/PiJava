package services.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Commentaire;
import model.Users;
import utils.BCrypt;
import utils.MyDb;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import IServices.user.IServiceUser;

import javax.xml.bind.DatatypeConverter;

public class ServiceUsers implements IServiceUser<Users> {

    private Connection cnx =MyDb.getInstance().getCnx();


    Connection connexion;
    PreparedStatement ps;

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
        String query = "INSERT INTO users (email, roles, password,mobile, nom,prenom,adresse,is_verified,is_deleted,token) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?);";
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
            stm.setBoolean(9, users.isDeleted());
            stm.setString(10, users.getToken());


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

       // ObservableList<Users> users = FXCollections.observableArrayList();


        try {
            String qery="SELECT * FROM `users` ";
            Statement stm =cnx.createStatement();
            ResultSet rs=stm.executeQuery(qery);

            while (rs.next()){
 Users u = new Users();
 u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles"));
                u.setPassword(rs.getString("password"));
                u.setMobile(rs.getString("mobile"));
 u.setNom(rs.getString("nom"));
 u.setPrenom(rs.getString("prenom"));

 u.setAdresse(rs.getString("adresse"));
u.setVerified(rs.getBoolean(8));

                u.setDeleted(rs.getBoolean(9));
                u.setToken(rs.getString(10));




 users.add(u);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int userCount() {
        int x=0;
        try {
            Statement stm=cnx.createStatement();
            String query = "SELECT count(*) as total FROM `users` where is_deleted=0";
            ResultSet rst=stm.executeQuery(query);

            while (rst.next()){
                x=rst.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;

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
    public boolean Validate_Login(String email , String password,String roles){
        Users u=null;
        try {



            String query = "SELECT * FROM users WHERE email = ? and roles = ? and password = ? and is_deleted=0 ";
            // Step 2:Create a statement using connection object
            PreparedStatement pS = cnx.prepareStatement(query);
            pS.setString(1, email);
            pS.setString(3, password);
            pS.setString(2, roles);
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
             //   BCrypt.checkpw(PASSWORD, u.getPassword().replace("$2y$", "$2a$"));
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
            String query = "UPDATE  user set  is_deleted=1  where user_id=" + idU +" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User deleted succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean SearchUser_Role(Users u, String r) {
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `users` WHERE `id`= '" + u.getId() + "' and `roles`='" + r + "' ";
            ResultSet rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Users> UserByRole() {
        ArrayList<Users> R = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT users.roles , COUNT(users.roles) AS nbruserbytype FROM users  GROUP BY roles";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Users users = new Users();

                users = new Users(rs.getInt("nbruserbytype"), rs.getString("roles"));
                R.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return R;
    }


    public ObservableList<Users> GetListPersonnes() {
        ObservableList<Users> Users = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM `users` ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            Users user;
            while (rst.next()) {
                user = new Users(rst.getString("email"), rst.getString("roles"), rst.getString("nom"), rst.getString("prenom"));
                Users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Users;
    }

    public Users getUserByEmail(String email) {
        Users u = null;
        try {
            String req = "SELECT * FROM `users` WHERE email = '" + email + "'";
            ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                u = new Users(rs.getInt("id"), email,
                        rs.getString("roles"), rs.getString("password"),  rs.getString("mobile"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"),  rs.getString("token"));

//                System.out.println("User retrieved");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec get User");
            return u;
        }
        return u;
    }


    public boolean changerToken(String code, String email) {
        try {
            String req = "UPDATE users  SET token = ?  WHERE email = '" + email + "'";
            ps = connexion.prepareStatement(req);
            ps.setString(1, code);
            ps.executeUpdate();
            //System.out.println("token changé avec succes");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur changement token");
            Logger.getLogger(ServiceUsers.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean changerMdp(int idUser, String new_mdp) {
        try {
            //String mdp = BCrypt.hashpw(new_mdp, BCrypt.gensalt(10)).replace("$2a$", "$2y$"); // cost in security.yml
            String mdp =crypter_password(new_mdp);

            String req = "UPDATE users  SET password = ?   WHERE id = '" + idUser + "'";
            ps = cnx.prepareStatement(req);
            ps.setString(1, mdp);
            ps.executeUpdate();
    System.out.println("mot de passe changé avec succes");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUsers.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur changement mdp");
            return false;
        }
    }
    public ObservableList<Users> RechercheUserAvance(String t) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM users WHERE  (email like '" + t + "%')";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<Users> ListU = FXCollections.observableArrayList();
        while (rs.next()) {
            Users u = new Users();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setAdresse(rs.getString("adresse"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setMobile(rs.getString("mobile"));

            ListU.add(u);
        }
        return ListU;
    }


    public String crypter_password(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        //   return hashValue;
        return hashValue;
    }

    public boolean login(String email, String password){

        try {

            String querry ="SELECT * FROM `users` where email ='"+email+"' and password ='"+password+"'";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);

            if(!rs.isBeforeFirst()){
                System.out.println("user not found !!!!");
                return false;
            }
            else{
                System.out.println("user is logged");
                while(rs.next()){
                    LoginSession.UID=rs.getInt("id");
                    LoginSession.Roles=rs.getString("roles");
                    LoginSession.firstName=rs.getString("nom");
                    LoginSession.email=rs.getString("email");
                    LoginSession.password=rs.getString("password");
                    //LoginSession.avatar=rs.getString("avatar");
                    LoginSession.IsLogged=true;
                }
                System.out.println(LoginSession.firstName+" is connected");
                return true;
            }
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
        return false;
    }
}
