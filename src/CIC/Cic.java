/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

/**
 *
 * @author clara
 */
public class Cic {
    String nom;
    String prenom;
    String login;
    
    public Cic(String nom, String prenom, String login){
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    
}
