/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;
import javafx.scene.text.Text;

/**
 *
 * @author Maelle
 */
public class Prescription {
    String service;
    String nomMedecin;
    Date date;
    String contenu;
   public Prescription(String s,String m, Date d,String contenu){
       service =s;
       nomMedecin =m;
       date=d;
       contenu=contenu;
        
    }

    public Date getDate() {
        return date;
    }

    public String getContenu() {
        return contenu;
    }

    public String getService() {
        return service;
    }

    public String getMedecin() {
        return nomMedecin;
    }
  
}
