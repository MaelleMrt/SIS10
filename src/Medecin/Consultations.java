/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;

/**
 * Définit une consultation
 * hérite de la classe Acte
 * @author Maelle
 */
public class Consultations extends Acte {

    /**
     * Constructeur Consultations
     * @param s le service
     * @param m le nom du médecin
     * @param d la date
     * @param motif le motif
     */
    public Consultations(String s, String m, String d, String motif) {
        super(s, m, d, motif);
    }
   
}
