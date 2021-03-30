/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;
import javafx.scene.text.Text;

/**
 * Définit une prescription
 * hérite de la classe ResultatPrescription
 * @author Maelle
 */
public class Prescription extends ResultatPrescription{
  
    /**
     * Constructeur Prescription
     * @param s le service
     * @param m le nom du médecin
     * @param d la date
     * @param contenu le contenu
     */
   public Prescription(String s,String m, String d,String contenu){
            super(s,m,d,contenu);
    }
}
