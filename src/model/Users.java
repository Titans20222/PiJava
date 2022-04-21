package model;

public class Users {
    private  int id;
    private  String email,roles,password,mobile,nom,prenom,adresse;
    private boolean isVerified;

    public Users() {
    }

    public Users(int id, String email, String roles, String password, String mobile, String nom, String prenom, String adresse, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.mobile = mobile;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.isVerified = isVerified;
    }

    public Users(int id, String email, String password, String mobile, String nom, String prenom, String adresse, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.isVerified = isVerified;
    }

    public Users(String email, String roles, String password, String mobile, String nom, String prenom, String adresse, boolean isVerified) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.mobile = mobile;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", isVerified=" + isVerified +
                "\n";
    }
}
