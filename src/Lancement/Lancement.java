/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lancement;

import HL7.FrameServeur;
import PageConnexion.InterfaceConnexion;

/**
 *
 * @author Maelle
 */
public class Lancement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrameServeur fs=new FrameServeur(6558);
        Thread t=new Thread(fs);
        t.start();
        fs.ajouterThread(t);
        new InterfaceConnexion();
    }
    
}
