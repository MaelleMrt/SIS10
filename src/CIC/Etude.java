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
    /**
     * nom de l'étude
     */
    private String nom;
    /**
     * Praticien hospitalier porteur
     */
    private String PH;
    /**
     * date de démarrage de l'étude
     */
    private String date;
    /**
     * durée de l'étude
     */
    private int duree;
    
    /**
     * Constructeur Etude 
     * 
     * @param nom nom de l'étude
     * @param PH Praticien hospitalier porteur
     * @param date date de démarrage de l'étude
     * @param duree durée de l'étude
     */
    public Etude(String nom, String PH, String date, int duree ){
        this.nom = nom;
        this.PH = PH;
        this.date = date;
        this.duree = duree;
    }
    
    /**
     * 
     * @return le nom de l'étude
     */
    public String getNom(){
        return this.nom;
    }
    
    /**
     * 
     * @return le praticien hospitalier porteur
     */
    public String getPH(){
        return this.PH;
    }
    
    /**
     * 
     * @return la date de démarrage de l'étude
     */
    public String getDate(){
        return this.date;
    }
    
    /**
     * 
     * @return la durée de l'étude
     */
    public int getDuree(){
        return this.duree;
    }
}
