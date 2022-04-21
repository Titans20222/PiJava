package IServices.user;

import java.util.List;
import java.sql.SQLException;

public interface IServiceUser <T>{
    public void ajouter(T t);
    public List<T> afficher();
    public  void modifier(T t);
    public  void supprimer(int id);
    public  void  searchByName(String nom);


    void orderByName() throws SQLException;

public boolean Validate_Login(String email , String password);

}
