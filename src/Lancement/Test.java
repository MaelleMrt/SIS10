/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lancement;

import Patient.PatientHop;

/**
 *
 * @author Maelle
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PatientHop p=new PatientHop("Martinet","Olivier","1971-02-27");
        System.out.println(p.getSexe());
    }
    
}
