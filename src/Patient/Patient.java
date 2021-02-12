/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import Connexion.ExempleJdbc;
import Medecin.Prescription;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kalma
 */
public class Patient {

    private String nom;
    private String prenom;
    private Date naissance;
    private int id;



    //, Date naissance, Integer numSecu
    public Patient(String nom, String prenom, Date dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateDeNaissance;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT id FROM Patient WHERE nomusuel='"+nom+"'' AND prenom='"+prenom+"' AND dateDeNaissance='"+dateDeNaissance+"'" );
                while(rs.next()){
                    id=rs.getInt("id");
                }   

            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);
        }
        
    }

    public int getId() {
        return id;
    }

   

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getNaissance() {
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