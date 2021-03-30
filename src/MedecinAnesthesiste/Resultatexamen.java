/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedecinAnesthesiste;

import Medecin.*;
import java.util.Date;

/**
 *
 * @author Maelle
 */
public class Resultatexamen {
    /**
     * service
     */
    private String service;
    /**
     * nom du médecin
     */
    private String nomMedecin;
    /**
     * date du résultat
     */
    private String date;
    /**
     * contenu
     */
    private String cont;
    
    /**
     * Constructeur
     * @param s service
     * @param m nom du médecin
     * @param d date
     * @param contenu contenu
     */
   public Resultatexamen(String s,String m, String d,String contenu){
       service =s;
       nomMedecin =m;
       date=d;
       cont=contenu;
        
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
     * @return le contenu
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
