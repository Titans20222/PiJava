/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.util.List;

/**
 *
 * @author mustapha
 */
public interface IIService <R>{

  public void  ajouter(R r);
    public List<R> afficher();
    public void  modifier (R r);
    public void supprimer (R r);
    
}
