/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 *
 * @author Maelle
 */
public class testTabPatient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DateChecker();
        System.out.println(DateChecker.isValid("1999-08-12"));
    }
    
}
