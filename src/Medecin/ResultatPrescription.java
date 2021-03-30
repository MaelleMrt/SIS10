/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;

/**
 * Définit un résultat ou une prescription
 *
 * @author Maelle
 */
public class ResultatPrescription {

    /**
     * le nom du service
     */
    String service;
    /**
     * le nom du médecin
     */
    String nomMedecin;
    /**
     * la date du résultat/prescription
     */
    String date;
    /**
     * le contenu du résultat/prescription
     */
    String cont;

    /**
     * Constructeur ResultatPrescription
     * initialise les attributs
     * @param s le service
     * @param m le nom du médecin
     * @param d la date
     * @param contenu le contenu
     */
    public ResultatPrescription(String s, String m, String d, String contenu) {
        service = s;
        nomMedecin = m;
        date = d;
        cont = contenu;

    }

    /**
     * 
     * @return la date du résultat/prescription
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @return le contenu du résultat/prescription
     */
    public String getContenu() {
        return cont;
    }

    /**
     * 
     * @return le service
     */
    public String getService() {
        return service;
    }

    /**
     * 
     * @return le nom du médecin 
     */
    public String getMedecin() {
        return nomMedecin;
    }
}
