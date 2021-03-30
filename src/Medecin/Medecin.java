/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 * Définit un médecin
 * @author Maelle
 */
public class Medecin {
    /**
     * nom du service
     */
    private String service ;
    /**
     * nom du médecin
     */
    private String nom;
    /**
     * prénom du médecin
     */
    private String prenom;
    /**
     * modèle permettant de remplir le tableau avec la liste des patients du médecin
     */
    private TableauPatient tb;
    /**
     * login du médecin
     */
    private String login;
    
    /**
     * Constructeur Medecin
     * initialise les attributs
     * @param n nom du médecin
     * @param p prénom du médecin
     * @param s service
     * @param log login du médecin
     */
    public Medecin(String n,String p, String s,String log){
        nom=n;
        prenom=p;
        service=s;
        login=log;
    }

    /**
     * 
     * @return le service
     */
    public String getService() {
        return service;
    }

    /**
     * initialise le service avec la valeur 'service'
     * @param service le service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 
     * @return le nom du médecin
     */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return le prénom du patient
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * 
     * @return le modèle de tableau avec la liste des patients
     */
    public TableauPatient getTb() {
        return tb;
    }

    /**
     * 
     * @return le login du médecin
     */
    public String getLogin() {
        return login;
    }

    
    
    
    public TableauPatient getTB(){
        return tb;
    }
}
