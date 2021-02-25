/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

/**
 *
 * @author clara
 */
public class Etude {
    private String nom;
    private String PH;
    private String date;
    private int duree;
    
    public Etude(String nom, String PH, String date, int duree ){
        this.nom = nom;
        this.PH = PH;
        this.date = date;
        this.duree = duree;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public String getPH(){
        return this.PH;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public int getDuree(){
        return this.duree;
    }
}
