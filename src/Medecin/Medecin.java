/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 *
 * @author Maelle
 */
public class Medecin {
    String service ;
    String nom;
    String prenom;
    TableauPatient tb;
    String login;
    
    public Medecin(String n,String p, String s,String login){
        nom=n;
        prenom=p;
        service=s;
        
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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

    public TableauPatient getTb() {
        return tb;
    }

    public void setTb(TableauPatient tb) {
        this.tb = tb;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
    public TableauPatient getTB(){
        return tb;
    }
}
