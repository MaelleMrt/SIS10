/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import java.util.Date;

/**
 * Définit un résultat
 * hérite de la classe ResultatPrescription
 * @author Maelle
 */
public class Resultat extends ResultatPrescription{

    /**
     * Constructeur Resultat
     * initialise les attributs
     * @param s le service
     * @param m le nom du médecin
     * @param d la date
     * @param contenu le contenu
     */
    public Resultat(String s, String m, String d, String contenu) {
        super(s, m, d, contenu);
    }
    
}
