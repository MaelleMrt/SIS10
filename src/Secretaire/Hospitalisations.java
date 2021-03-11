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
 *
 * @author Elodie
 */
public class Hospitalisations {

    private String medecin;
    private int id;
    private String motif;
    private String date;

    public Hospitalisations(String medecin, int id, String motif, String date) {
        this.medecin = medecin;
        this.id = id;
        this.motif = motif;
        this.date = date;
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

    public String getService() {
        String service = new String();
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT nomS FROM Médecin WHERE  CONCAT(prenom, ' ', nom) LIKE '" + medecin + "'");
            while (rs.next()) {
                service = rs.getString("nomS");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return service;
    }
    public String getLocalisation() {
        String loc = new String();
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT Localisation FROM RendezVous WHERE Médecin ='" + medecin + "' AND Date ='" + date + "' AND idPatient ='" + id + "' AND Motif ='" + motif + "'");
            while (rs.next()) {
                loc = rs.getString("Localisation");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return loc;
    }
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
}


