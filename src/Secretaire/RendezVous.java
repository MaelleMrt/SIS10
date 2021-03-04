/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

/**
 *
 * @author Elodie
 */
public class RendezVous {
    private String date;
    private String patient;
    private String motif;
    private String localisation;
    private String categorie;
    private String médecin;
    private int id;
    
    public RendezVous(String date, String patient, String localisation, String motif, String categorie, String médecin, int id){
        this.date = date;
        this.patient = patient;
        this.localisation = localisation;
        this.motif = motif;
        this.categorie = categorie;
        this.médecin = médecin;
        this.id = id;
    }   

    public String getDate() {
        return date;
    }

    public String getId() {
        return String.valueOf(id) ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMédecin() {
        return médecin;
    }

    public void setMédecin(String médecin) {
        this.médecin = médecin;
    }
    
    
}
