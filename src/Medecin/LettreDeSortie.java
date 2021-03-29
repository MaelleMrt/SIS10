/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 * Définit une lettre de sortie
 * @author clara
 */
public class LettreDeSortie {
    /**
     * l'identifiant du patient
     */
    private String id;
    /**
     * le service dans lequel est hospitalisé le patient
     */
    private String service;
    /**
     * le médecin traitant du patient
     */
    private String medecinTraitant;
    /**
     * noms et prénom du patient
     */
    private String patient;
    /**
     * la date de naissance du patient
     */
    private String dateNaissance;
    /**
     * la date d'entrée à l'hôpital
     */
    private String dateEntree;
    /**
     * la date de sortie
     */
    private String dateSortie;
    /**
     * le motif d'hospitalisation
     */
    private String motif;
    /**
     * Examen clinique et démarche diagnostique
     */
    private String exam;
    /**
     * le praticien hospitalier rédigeant la lettre de sortie
     */
    private String PH;
    /**
     * traitement de sortie proposé
     */
    private String traitement;
    /**
     * synthèse
     */
    private String synthese;
    /**
     * Suivi
     */
    private String suivi;
    
    /**
     * Constructeur LettreDeSortie
     * initialise les attributs
     * @param id l'identifiant du patient
     * @param service le service
     * @param medecinTraitant le médecin traitant
     * @param patient noms et prénom du patient
     * @param dateNaissance la date de naissance du patient
     * @param dateEntree la date d'entrée
     * @param dateSortie la date de sortie
     * @param motif le motif d'hospitalisation
     * @param exam examen clinique et démarche diagnostique
     * @param PH praticien hospitalier rédigeant la lettre
     * @param traitement traitement de sortie proposé
     * @param synthese synthèse
     * @param suivi suivi
     */
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

    /**
     * 
     * @return l'identifiant du patient
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @return le service
     */
    public String getService() {
        return service;
    }

    /**
     * 
     * @return le médecin traitant du patient
     */
    public String getMedecinTraitant() {
        return medecinTraitant;
    }

    /**
     * 
     * @return noms et prénom du patient
     */
    public String getPatient() {
        return patient;
    }

    /**
     * 
     * @return la date de naissance du patient
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * 
     * @return la date d'entrée
     */
    public String getDateEntree() {
        return dateEntree;
    }

    /**
     * 
     * @return la date de sortie
     */
    public String getDateSortie() {
        return dateSortie;
    }

    /**
     * 
     * @return le motif d'hospitalisation
     */
    public String getMotif() {
        return motif;
    }

    /**
     * 
     * @return l'examen clinique et démarche diagnostique
     */
    public String getExam() {
        return exam;
    }

    /**
     * 
     * @return le praticien hospitalier rédigeant la lettre
     */
    public String getPH() {
        return PH;
    }

    /**
     * 
     * @return le traitement de sortie proposé
     */
    public String getTraitement() {
        return traitement;
    }

    /**
     * 
     * @return la synthèse
     */
    public String getSynthese() {
        return synthese;
    }

    /**
     * 
     * @return le suivi
     */
    public String getSuivi() {
        return suivi;
    }
    
}
