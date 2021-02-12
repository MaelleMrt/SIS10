/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;

/**
 *
 * @author Maelle
 */
public class Hospitalisations {
    String service;
    String nomMedecin;
    Date date;
    String mot;
     public Hospitalisations(String s,String m, Date d,String motif){
       service =s;
       nomMedecin =m;
       date=d;
       mot=motif;
        
    }

    public String getService() {
        return service;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public Date getDate() {
        return date;
    }

    public String getMotif() {
        return mot;
    }
}
