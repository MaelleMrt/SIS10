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
  public boolean valider;
   public PrescriptionInf(String s,String m, String d,String contenu,boolean valider){
            super(s,m,d,contenu);
            this.valider=valider;
    }
   public boolean getValider(){
       return this.valider;
   }
}
