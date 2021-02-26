/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import library.interfaces.ServeurHL7;

/**
 *
 * @author Maelle
 */
public class TestHL7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServeurHL7 serveur = new ServeurHL7();
        serveur.connection(6558);
        serveur.ecoute();
    }
    
}
