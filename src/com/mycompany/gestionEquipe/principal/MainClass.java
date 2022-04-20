/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionEquipe.Principal;


import com.mycompany.gestionEquipe.entities.Equipe;
import com.mycompany.gestionEquipe.entities.Equippement;
import com.mycompany.gestionEquipe.services.EquippementService;
import com.mycompany.gestionEquipe.services.EquipeService;
import com.mycompany.gestionEquipe.utils.MyConnection;
/**Equipe
 *
 * @author 21626
 */
public class MainClass {
    
public static void main (String [] args){
//MyConnection mc =  MyConnection.getInstance();

MyConnection mc =  MyConnection.getInstance();


EquipeService e = new EquipeService(); 
Equipe E = new Equipe (42,"salma","khemiri",74,"tabekha");
//e.ajouterEquipe(E);
//e.afficher().forEach(System.out::println);
//e.supprimer(E); 
//e.Modifier(new Equipe (42,"fedi","jouini",74,"hjjk"));

EquippementService eq = new EquippementService(); 
Equippement Eq = new Equippement (13,"feklf","dkls");
//eq.ajouterEquippement(Eq);
//eq.afficher().forEach(System.out::println);
//eq.supprimer(Eq); 
//eq.Modifier(new Equippement (13,"torrtiw","Chaffeur"));    

}
}

