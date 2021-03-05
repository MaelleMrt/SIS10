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
    private int poids;
    private int temp;
    private int pa;
    private int fc;
    private int saturation;
    private int gly;
    private String observation;
    private String date;
    private String nomInf;

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

    public String getNomInf() {
        return nomInf;
    }

    public int getPoids() {
        return poids;
    }

    public int getTemp() {
        return temp;
    }

    public int getGly() {
        return gly;
    }

    public int getPa() {
        return pa;
    }

    public int getFc() {
        return fc;
    }

    public int getSaturation() {
        return saturation;
    }

    public String getObservation() {
        return observation;
    }
    public String getDate(){
        return date;
    }
    
  
}
