/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import java.sql.Date;

/**
 *
 * @author clara
 */
public class Participant {
    /**
     * nom usuel du participant
     */
    private String nomU;
    /**
     * prénom du participant
     */
    private String prenom;
    /**
     * Date de naissance du participant
     */
    private Date dateN;
    /**
     * type du participant : patient ou volontaire sain
     */
    private String type;
    
    /**
     * Constructeur Participant
     * 
     * @param nomU nom usuel du participant
     * @param prenom prénom du participant
     * @param dateN date de naissance du participant
     * @param type type du participant
     */
    public Participant(String nomU,String prenom,Date dateN, String type){
        this.nomU = nomU;
        this.prenom = prenom;
        this.dateN = dateN;
        this.type = type;
    }
    
    /**
     * 
     * @return le nom usuel du participant
     */
    public String getNomU() {
        return nomU;
    }

    /**
     * 
     * @return le prénom du participant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * 
     * @return la date de naissance du participant
     */
    public Date getDateN() {
        return dateN;
    }

    /**
     * 
     * @return le type du participant
     */
    public String getType() {
        return type;
    }
    
    /**
     * Vérifie si les 2 participants sont les mêmes, c'est-à-dire s'ils ont les mêmes nom, prénom, date de naissance et type
     * @param p un participant
     * @return true si ce sont les mêmes, false sinon
     */
    public boolean egal(Participant p){
        return (nomU.equals(p.getNomU()) && prenom.equals(p.getPrenom()) && dateN.equals(p.getDateN()) && type.equals(p.getType()));
    }
}
