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
    /**
     * Le nom du praticien hospitalier du Centre d'Investigation Clinique
     */
    String nom;
    /**
     * Le prénom du praticien hospitalier du CIC
     */
    String prenom;
    /**
     * Le login du praticien hospitalier du CIC
     */
    String login;
    
    /**
     * 
     * @param nom
     * @param prenom
     * @param login 
     */
    public Cic(String nom, String prenom, String login){
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }

    /**
     * 
     * @return un String qui correspond au login du PH
     */
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @return un String qui correspond au nom du PH
     */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return un String qui correspond au prénom du PH
     */
    public String getPrenom() {
        return prenom;
    }
    
    
}
