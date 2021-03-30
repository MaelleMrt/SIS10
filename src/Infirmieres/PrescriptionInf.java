/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infirmieres;

import Medecin.*;
import java.util.Date;
import javafx.scene.text.Text;

/**
 *
 * @author Maelle
 */
public class PrescriptionInf extends ResultatPrescription {
    /**
     * true si la prescription est validée, false sinon
     */
    private boolean valider;
    /**
     * date de validation de la prescription
     */
    private String dateVal;
    /**
     * nom de l'infimière qui a validé la prescription
     */
    private String nomInf;

    /**
     * Constructeur PrescriptionInf
     * @param s
     * @param m
     * @param d
     * @param contenu contenu de la prescription
     * @param valider
     * @param dateVal
     * @param loginInf 
     */
    public PrescriptionInf(String s, String m, String d, String contenu, boolean valider, String dateVal, String loginInf) {
        super(s, m, d, contenu);
        this.valider = valider;
        this.dateVal = dateVal;
        this.nomInf = loginInf;
    }

    
    

    /**
     * 
     * @return le nom de l'infirmière
     */
    public String getNomInf() {
        return nomInf;
    }
    
    /**
     * 
     * @return la date de validation de la prescription
     */
    public String getDateVal() {
        return dateVal;
    }

    /**
     * 
     * @return true si la prescription est validée, false sinon
     */
    public boolean getValider() {
        return this.valider;
    }
}
