/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infirmieres;

import Medecin.*;

/**
 *
 * @author Maelle
 */
public class Infirmier {
    /**
     * le service dans lequel travaille l'infirmière
     */
    String service ;
    /**
     * nom de l'infirmière
     */
    String nom;
    /**
     * prénom de l'infirmière
     */
    String prenom;
    /**
     * tableau contenant les patients du service
    */
    TableauPatient tb;
    /**
     * le login de l'infirmière
     */
    String login;
    
    /**
     * Constructeur Infirmier
     * 
     * @param n nom de l'infirmière
     * @param p prénom de l'infirmière
     * @param s service de l'infirmière
     * @param log login de l'infirmière
     */
    public Infirmier(String n,String p, String s,String log){
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
     * met à jour la valeur du service
     * @param service 
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 
     * @return le nom de l'infirmière
     */
    public String getNom() {
        return nom;
    }


    /**
     * 
     * @return le prénom de l'infirmière
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * 
     * @return le tableau de patients du service
     */
    public TableauPatient getTb() {
        return tb;
    }


    /**
     * 
     * @return le login de l'infirmière
     */
    public String getLogin() {
        return login;
    }

}
