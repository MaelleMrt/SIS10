/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import Patient.PatientHop;
import Secretaire.Localisation;
import Secretaire.Secretaire;

/**
 *
 * @author Maelle
 */
public class testFrameClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PatientHop patient=new PatientHop("Oster","Clara","2018-12-11");
        Secretaire secretaire=new Secretaire("nom","prenom","service");
        secretaire.setVisible(false);
        Localisation loc=new Localisation(" Elodie Collet ","1","201","Gynécologie","211364592");
        new FrameClientAdmi(patient,secretaire);

    }
    
}
