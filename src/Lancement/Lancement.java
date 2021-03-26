/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lancement;

import HL7.FrameServeur;
import PageConnexion.InterfaceConnexion;

/**
 * Lancement de l'interface de connexion
 * et du serveur qui écoute les messages HL7
 * @author Maelle
 */
public class Lancement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // on attend un message HL7 en permanence
        FrameServeur fs=new FrameServeur(6558);
        // on cree une pile d'execution
        Thread t=new Thread(fs);
        t.start();
        fs.ajouterThread(t);
        // on lance l'interface de connexion
        new InterfaceConnexion();
    }
    
}
