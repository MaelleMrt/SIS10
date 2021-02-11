/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kalma
 */
public class Patient {

    private String nom;
    private String prenom;
    private String naissance;
   



    //, Date naissance, Integer numSecu
    public Patient(String nom, String prenom, String dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateDeNaissance;
       
    }

   

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNaissance() {
        return naissance;
    }
   

   
   
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    
 

    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

}