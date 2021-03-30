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
 * Définit un rendez-vous
 *
 * @author amira
 */
public class RdvMedecin {

    /**
     * le motif du rdv
     */
    private String motif;
    /**
     * la date du rdv
     */
    private String date;
    /**
     * l'heure du rdv
     */
    private String heure;
    /**
     * nom du patient
     */
    private String nom;
    /**
     * prénom du patient
     */
    private String prenom;
    /**
     * date de naissance du patient
     */
    private String dateN;
    /**
     * patient
     */
    private PatientHop patient;

    /**
     * Constructeur RdvMedecin
     * initialise les attributs
     * @param motif le motif du rdv
     * @param date la date du rdv
     * @param heure l'heure du rdv
     * @param nom le nom du patient
     * @param prenom le prenom du patient
     * @param dateN la date de naissance du patient
     */
    public RdvMedecin(String motif, String date, String heure, String nom, String prenom, String dateN) {
        this.motif = motif;
        this.date = date;
        this.heure = heure;
        this.nom = nom;
        this.prenom = prenom;
        this.dateN = dateN;

        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT nomusuel, prenom, datedenaissance,id FROM Patient WHERE nomusuel ='" + this.nom + "'AND prenom = '" + this.prenom + "' AND datedenaissance ='" + this.dateN + "'");
                while (rs.next()) {
                    this.patient = new PatientHop(rs.getString("nomusuel"), rs.getString("prenom"), rs.getString("datedenaissance"));

                }

            } catch (SQLException e) {
                System.out.println(e);

            }

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    /**
     * 
     * @return le nom du patient
     */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return le prenom du patient
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * 
     * @return la date de naissance du patient
     */
    public String getDateN() {
        return dateN;
    }

    /**
     * 
     * @return le patient
     */
    public PatientHop getPatient() {
        return patient;
    }

    /**
     * 
     * @return le motif du rdv
     */
    public String getMotif() {
        return motif;
    }

    /**
     * 
     * @return la date du rdv
     */
    public String getDate() {
        return date;
    }
    
    /**
     * 
     * @return l'heure du rdv
     */
    public String getHeure() {
        return heure;
    }

}
