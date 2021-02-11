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
    private String nomPH;
    private String prenomPH;
    private String date;
    private int duree;
    
    public Etude(String nom, String nomPH, String prenomPH, String date, int duree ){
        this.nom = nom;
        this.nomPH = nomPH;
        this.prenomPH = prenomPH;
        this.date = date;
        this.duree = duree;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public String getNomPH(){
        return this.nomPH;
    }
    
    public String getPrenomPH(){
        return this.prenomPH;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public int getDuree(){
        return this.duree;
    }
}
