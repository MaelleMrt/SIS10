/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

/**
 * Définit un rendez-vous
 * @author Elodie
 */
public class RendezVous {
    /**
     * la date du rendez-vous
     */
    private String date;
    /**
     * le patient
     */
    private String patient;
    /**
     * le motif du rendez-vous
     */
    private String motif;
    /**
     * la localisation
     */
    private String localisation;
    /**
     * la catégorie du rendez-vous : hospitalisation ou consultation
     */
    private String categorie;
    /**
     * le médecin
     */
    private String médecin;
    /**
     * l'identifiant du rdv
     */
    private int id;
    /**
     * l'heure du rdv
     */
    private String heure;
    
    /**
     * Constructeur RendezVous
     * @param date date du rdv
     * @param patient patient
     * @param localisation localisation
     * @param motif motif du rdv
     * @param categorie categorie du rdv
     * @param médecin médecin
     * @param id identifiant du rdv
     * @param heure heure du rdv
     */
    public RendezVous(String date, String patient, String localisation, String motif, String categorie, String médecin, int id, String heure){
        this.date = date;
        this.patient = patient;
        this.localisation = localisation;
        this.motif = motif;
        this.categorie = categorie;
        this.médecin = médecin;
        this.id = id;
        this.heure = heure;
    }   

    /**
     * 
     * @return la date du rdv
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @return l'identifiant du rdv
     */
    public String getId() {
        return String.valueOf(id) ;
    }

    /**
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return le patient
     */
    public String getPatient() {
        return patient;
    }

    /**
     * 
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }

    /**
     * 
     * @return le motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * 
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * 
     * @return la localisation
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * 
     * @param localisation the localisation to set
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    /**
     * 
     * @return la categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * 
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * 
     * @return le médecin
     */
    public String getMédecin() {
        return médecin;
    }

    /**
     * 
     * @param médecin the médecin to set
     */
    public void setMédecin(String médecin) {
        this.médecin = médecin;
    }

    /**
     * @return the heure
     */
    public String getHeure() {
        return heure;
    }

    /**
     * @param heure the heure to set
     */
    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    
}
