/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import Connexion.ExempleJdbc;
import Patient.PatientHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amira
 */
 public class RdvMedecin {
    private String motif;
    private String date;
    private String heure;
    private String nom;
    private String prenom;
    private String dateN;
    private PatientHop patient;
    
    
   public RdvMedecin(String motif,String date ,String heure,String nom, String prenom,String dateN){
       this.motif=motif;
       this.date=date;
       this.heure=heure;
       this.nom=nom;
       this.prenom=prenom;
       this.dateN=dateN;

       try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nomusuel, prenom, datedenaissance,id FROM Patient WHERE nomusuel ='"+ this.nom+"'AND prenom = '"+this.prenom+"' AND datedenaissance ='"+this.dateN+"'");
                while(rs.next()){
                     this.patient=new PatientHop(rs.getString("nomusuel"), rs.getString("prenom"), rs.getString("datedenaissance"));

                }   

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateN() {
        return dateN;
    }

    public String getDataNaissance() {
        return dateN;
    }


    public PatientHop getPatient() {
        return patient;
    }

 
    
    
   public String getMotif() {
        return motif;
    }

    public String getDate() {
        return date;
    }
    
    public String getHeure() {
        return heure;
    }
    
}
