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
    public int poids;
    public int temp;
    public int pa;
    public int fc;
    public int saturation;
    public int gly;
    public String observation;
    public String date;

    public Soins(int poids, int temp, int pa, int fc, int saturation,int gly ,String observation,String date) {
        this.poids = poids;
        this.temp = temp;
        this.pa = pa;
        this.fc = fc;
        this.gly=gly;
        this.saturation = saturation;
        this.observation = observation;
        this.date=date;
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
