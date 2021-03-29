/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infirmieres;

/**
 *
 * @author Maelle
 */
public class Soins {
    /**
     * poids du patient
     */
    private int poids;
    /**
     * température du patient
     */
    private int temp;
    /**
     * Pression artérielle du patient
     */
    private int pa;
    /**
     * Fréquence cardiaque du patient
     */
    private int fc;
    /**
     * saturation en oxygène du patient
     */
    private int saturation;
    /**
     * glycémie du patient
     */
    private int gly;
    /**
     * observation faite par l'infirmière
     */
    private String observation;
    /**
     * date des soins
     */
    private String date;
    /**
     * nom de l'infirmière ayant réalisé les soins
     */
    private String nomInf;

    /**
     * Constructeur Soins
     * 
     * @param poids le poids du patient
     * @param temp la température du patient
     * @param pa la pression artérielle du patient
     * @param fc la fréquence cardiaque du patient
     * @param saturation la saturation en oxygène du patient
     * @param gly la glycémie du patient
     * @param observation observation faite par l'infirmière
     * @param date la date des soins
     * @param nomInf le nom de l'infirmière ayant réalisé les soins
     */
    public Soins(int poids, int temp, int pa, int fc, int saturation,int gly ,String observation,String date,String nomInf) {
        this.poids = poids;
        this.temp = temp;
        this.pa = pa;
        this.fc = fc;
        this.gly=gly;
        this.saturation = saturation;
        this.observation = observation;
        this.date=date;
        this.nomInf=nomInf;
    }

    /**
     * 
     * @return le nom de l'infirmière ayant réalisé les soins
     */
    public String getNomInf() {
        return nomInf;
    }

    /**
     * 
     * @return le poids du patient
     */
    public int getPoids() {
        return poids;
    }

    /**
     * 
     * @return la température du patient
     */
    public int getTemp() {
        return temp;
    }

    /**
     * 
     * @return la glycémie du patient 
     */
    public int getGly() {
        return gly;
    }

    /**
     * 
     * @return la pression artérielle du patient
     */
    public int getPa() {
        return pa;
    }

    /**
     * 
     * @return la fréquence cardiaque du patient
     */
    public int getFc() {
        return fc;
    }

    /**
     * 
     * @return la saturation en oxygène du patient
     */
    public int getSaturation() {
        return saturation;
    }

    /** 
     * 
     * @return l'observation faite par l'infirmière
     */
    public String getObservation() {
        return observation;
    }
    
    /**
     * 
     * @return la date des soins
     */
    public String getDate(){
        return date;
    }
    
  
}
