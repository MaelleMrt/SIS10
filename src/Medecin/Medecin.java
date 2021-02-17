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
    
    public Medecin(String n,String p, String s,String log){
        nom=n;
        prenom=p;
        service=s;
        login=log;
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



    public String getPrenom() {
        return prenom;
    }



    public TableauPatient getTb() {
        return tb;
    }



    public String getLogin() {
        return login;
    }

    
    
    
    public TableauPatient getTB(){
        return tb;
    }
}
