/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

/**
 * Fait la corresondance entre le nom du service et un numéro
 * @author Elodie
 */
public class CorrespondanceService {

    /**
     * le nom du service
     */
    private String service;
    /**
     * le numéro associé au service
     */
    private int numero;

    /**
     * Constructeur CorrespondanceService
     * @param service le service
     */
    public CorrespondanceService(String service) {
        this.service = service;
    }

    /**
     * associe un numéro en fonction du nom du service
     */
    public void trouverCorrespondance() {
        switch(service){
            case "Oncologie" :
                numero = 1;
                break;
            case "Gynécologie" :
                numero = 2;
                break;
            case "Cardiologie" :
                numero = 3;
                break;
            case "Urgence" :
                numero = 4;
                break;
        }
        
        
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }
}
