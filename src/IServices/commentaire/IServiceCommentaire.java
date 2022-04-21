/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IServices.commentaire;

import java.util.List;

/**
 *
 * @author lenovo
 */
public interface IServiceCommentaire  <T>{
    public void ajouter(T t);
    public List<T> afficher();
    public  void modifier(T t);
    public  void supprimer(T t);



}