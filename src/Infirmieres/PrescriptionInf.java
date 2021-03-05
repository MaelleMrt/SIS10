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
public class PrescriptionInf extends ResultatPrescription{
  private boolean valider;
  private String dateVal;
  private String nomInf;
  
   public PrescriptionInf(String s,String m, String d,String contenu,boolean valider,String dateVal,String loginInf){
            super(s,m,d,contenu);
            this.valider=valider;
            this.dateVal=dateVal;
            this.nomInf=loginInf;
    }

    public boolean isValider() {
        return valider;
    }

    public String getNomInf() {
        return nomInf;
    }


    public String getDateVal() {
        return dateVal;
    }
   public boolean getValider(){
       return this.valider;
   }
}
