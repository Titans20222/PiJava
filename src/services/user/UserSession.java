package services.user;

import java.util.Set;

public final class UserSession {
    private static UserSession instance;
    private static int id;
    private static String nom;
    private static String prenom;
    private static String email;
    private static String mobile;
    private static String password;
    private static String adresse;

    private static String roles;

    private static boolean isVerfied;
    private UserSession(int id, String email, String roles,String password,String mobile, String nom, String prenom,String adresse,boolean isVerfied) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mobile=mobile;
        this.password=password;
        this.adresse = adresse;
        this.roles = roles;
        this.isVerfied=isVerfied;
    }

    public static UserSession getInstance(int id, String email, String roles,String password,String mobile, String nom, String prenom,String adresse,boolean isVerfied) {
        if (instance==null){
            instance =new UserSession(id, email, roles, password,mobile,nom,prenom, adresse,isVerfied);
        }
        return instance;
    }



    public static int getId() {
        return id;
    }



    public static String getNom() {
        return nom;
    }



    public static String getPrenom() {
        return prenom;
    }



    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAdresse() {
        return adresse;
    }
    public static String getMobile() {
        return adresse;
    }



    public static String getRoles() {
        return roles;
    }

    public static void cleanUserSession() {
        instance = null;



    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adresse='" + adresse + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }


    /*
    * Pour la connexion on utilise UserSession.getInstace(id, nom, prenom, email,password,adresse, roles)
    *Deconnexion UserSession.cleanUserSession()
    *
    * */
}
