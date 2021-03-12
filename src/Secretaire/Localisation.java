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
import java.util.ArrayList;

/**
 *
 * @author Elodie
 */
public class Localisation {

    private String patient;
    private String chambre;
    private String lit;
    private String statut;
    private String idLocalisation;
    private String service;

    public Localisation(String patient, String chambre, String lit, String service, String id) {
        this.chambre = chambre;
        this.lit = lit;
        this.patient = patient;
        this.service = service;
        idLocalisation = id;
    }

    /**
     * @return the patient
     */
    public String getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }

    /**
     * @return the chambre
     */
    public String getChambre() {
        return chambre;
    }

    /**
     * @param chambre the chambre to set
     */
    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    /**
     * @return the lit
     */
    public String getLit() {
        return lit;
    }

    /**
     * @param lit the lit to set
     */
    public void setLit(String lit) {
        this.lit = lit;
    }

    /**
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the idLocalisation
     */
    public String getIdLocalisation() {
        return idLocalisation;
    }

    /**
     * @param idLocalisation the idLocalisation to set
     */
    public void setIdLocalisation(String idLocalisation) {
        this.idLocalisation = idLocalisation;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    public boolean dejaOccupee() {
        boolean result = false;
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT statut FROM Localisation WHERE idLocalisation ='" + idLocalisation + "'");
            while (rs.next()) {
                statut = rs.getString("statut");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if(statut.equals("Occupée")){
            result = true;
        }
        return result;
    }
}