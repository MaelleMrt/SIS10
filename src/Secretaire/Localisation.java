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

    /**
     * le patient
     */
    private String patient;
    /**
     * la chambre
     */
    private String chambre;
    /**
     * le lit
     */
    private String lit;
    /**
     * le statut de la chambre : occupée ou non occupée
     */
    private String statut;
    /**
     * identifiant de la localisation
     */
    private String idLocalisation;
    /**
     * service
     */
    private String service;

    /**
     * Constructeur Localisation
     * @param patient le patient
     * @param chambre la chambre
     * @param lit le lit
     * @param service le service
     * @param id l'identifiant de la localisation
     */
    public Localisation(String patient, String chambre, String lit, String service, String id) {
        this.chambre = chambre;
        this.lit = lit;
        this.patient = patient;
        this.service = service;
        idLocalisation = id;
    }
    
    /**
     * 2e constructeur Localisation
     * @param id l'identifiant de la localisation
     */
    public Localisation(String id){
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

    /**
     * vérifie si la chambre est déjà occupée
     * @return true si elle est occupée, false sinon
     */
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
    
    /**
     * 
     * @return la localisation
     */
    public String getLocalisation(){
        String localisation = new String();
        String s = idLocalisation.substring(0,1);
        String c = idLocalisation.substring(1,2);
        String l = idLocalisation.substring(2);
        localisation = "Le patient se trouve dans le service " + s + ", dans la chambre " + c + ", dans le lit " + l;
        return localisation;
    }
}
