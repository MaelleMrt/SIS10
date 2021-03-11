/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 *
 * @author clara
 */
public class LettreDeSortie {
    private String id;
    private String service;
    private String medecinTraitant;
    private String patient;
    private String dateNaissance;
    private String dateEntree;
    private String dateSortie;
    private String motif;
    private String exam;
    private String PH;
    private String traitement;
    private String synthese;
    private String suivi;
    
    public LettreDeSortie(String id, String service,String medecinTraitant,String patient, String dateNaissance, String dateEntree, String dateSortie, String motif, String exam, String PH, String traitement, String synthese, String suivi){
        this.id = id;
        this.service = service;
        this.medecinTraitant = medecinTraitant;
        this.patient = patient;
        this.dateNaissance = dateNaissance;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.motif = motif;
        this.exam = exam;
        this.PH = PH;
        this.traitement = traitement;
        this.synthese = synthese;
        this.suivi = suivi;
    }

    public String getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public String getMedecinTraitant() {
        return medecinTraitant;
    }

    public String getPatient() {
        return patient;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getDateEntree() {
        return dateEntree;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public String getMotif() {
        return motif;
    }

    public String getExam() {
        return exam;
    }

    public String getPH() {
        return PH;
    }

    public String getTraitement() {
        return traitement;
    }

    public String getSynthese() {
        return synthese;
    }

    public String getSuivi() {
        return suivi;
    }
    
}
