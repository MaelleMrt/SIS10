/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 * Définit un acte médical
 * @author Maelle
 */
public abstract class Acte {
    /**
     * le service
     */
    String service;
    /**
     * le nom du médecin
     */
    String nomMedecin;
    /**
     * la date de l'acte
     */
    String date;
    /**
     * Le motif de l'acte
     */
    String mot;
    
    /**
     * Constructeur Acte
     * initialise les attributs
     * @param s le service
     * @param m le nom du médecin
     * @param d la date
     * @param motif le motif
     */
    public Acte(String s,String m, String d,String motif){
       service =s;
       nomMedecin =m;
       date=d;
       mot=motif;
        
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
    public String getNomMedecin() {
        return nomMedecin;
    }

    /**
     * 
     * @return la date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @return le motif 
     */
    public String getMotif() {
        return mot;
    }
}
