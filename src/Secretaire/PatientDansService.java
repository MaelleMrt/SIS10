/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

public class PatientDansService {

    /**
     * l'identifiant du patient
     */
    private int id;
    /**
     * le service
     */
    private String service;

    /**
     * Constructeur PatientDansService
     * @param id identifiant du patient
     * @param service service
     */
    public PatientDansService(int id, String service) {
        this.id = id ;
        this.service = service;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }
}
