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
public class ResultatPrescription {
    String service;
    String nomMedecin;
    String date;
    String cont;
    
   public ResultatPrescription(String s,String m, String d,String contenu){
       service =s;
       nomMedecin =m;
       date=d;
       cont=contenu;
        
    }

    public String getDate() {
        return date;
    }

    public String getContenu() {
        return cont;
    }

    public String getService() {
        return service;
    }

    public String getMedecin() {
        return nomMedecin;
    }
}
