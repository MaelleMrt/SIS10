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
public class Consultations {
    String service;
    String nomMedecin;
    String date;
    String mot;
     public Consultations(String s,String m, String d,String motif){
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

    public String getDate() {
        return date;
    }

    public String getMotif() {
        return mot;
    }
}
