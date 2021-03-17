/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

public class CorrespondanceService {

    private String service;
    private int numero;

    public CorrespondanceService(String service) {
        this.service = service;
    }

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
