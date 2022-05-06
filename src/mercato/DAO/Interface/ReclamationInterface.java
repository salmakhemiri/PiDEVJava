/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.DAO.Interface;

import java.util.List;
import mercato.Model.Reclamation;

/**
 *
 * @author ASUS
 
 */
public interface ReclamationInterface <T > {
  public void Ajouter(T t);
    public void Supprimer(T t);
    public void Modifier(T t);
    public List<T> Afficher();
 public  List <T> Rechercher(T t);
 public Reclamation getById(int id);
 public List<Reclamation> getByUserId(int user_id);
 public List<Reclamation> findByType(String type);
 public List<Reclamation> getByEtat(Reclamation t,String etatProbleme);
 public List<T> Trier();
}
