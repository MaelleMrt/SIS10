/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

import Connexion.ExempleJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Définit une consultation
 * @author Elodie
 */
public class Consultation {
    /**
     * le nom du médecin
     */
    private String medecin;
    /**
     * l'identifiant du patient
     */
    private int id;
    /**
     * le motif de consultation
     */
    private String motif;
    /**
     * la date de consultation
     */
    private String date;
    /**
     * l'identifiant du rdv
     */
    private int rdv;
    
    /**
     * Constructeur Consultation
     * initialise les attributs 
     * @param medecin le nom du médecin
     * @param id l'identifiant du patient
     * @param motif le motif de consultation
     * @param date la date de consultation
     * @param rdv l'identifiant du rdv
     */
    public Consultation(String medecin, int id, String motif, String date, int rdv){
        this.medecin = medecin;
        this.id = id;
        this.motif = motif;
        this.date = date;
        this.rdv = rdv;
    }

    /**
     * @return the medecin
     */
    public String getMedecin() {
        return medecin;
    }

    /**
     * @param medecin the medecin to set
     */
    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * interroge la base de données pour trouver le service
     * @return le service
     */
    public String getService(){
        String service = new String();
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT nomS FROM Médecin WHERE CONCAT(prenom, ' ', nom) LIKE '" + medecin + "'");
            while (rs.next()) {
                service = rs.getString("nomS");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return service;
    }
    
    /**
     * interroge la base de données pour trouver le nom du patient
     * @return le nom du patient
     */
    public String getNomP(){
        String nom = new String();
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT patient FROM RendezVous WHERE idPatient ='" + id + "'");
            while (rs.next()) {
                nom = rs.getString("patient");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return nom;
    }
    
    /**
     * interroge la base de données pour trouver l'heure de la consultation
     * @return l'heure de la consultation
     */
    public String getHeure(){
        // rajouter un idRDV
        String heure = new String();
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT heure FROM RendezVous WHERE idPatient ='" + id + "' AND idRdv ='" + rdv + "'");
            while (rs.next()) {
                heure = rs.getString("heure");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return heure;
    }
}
