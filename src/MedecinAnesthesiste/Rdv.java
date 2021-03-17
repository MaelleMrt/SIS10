/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedecinAnesthesiste;

/**
 *
 * @author amira
 */
 public class Rdv {
    String motif;
    String date;
    String heure;
    
    
   public Rdv(String m,String d, String h){
       
       motif=m;
       date=d;
       heure=h;
        
    }
   public String getMotif() {
        return motif;
    }

    public String getDate() {
        return date;
    }
    
    public String getHeure() {
        return heure;
    }
    
}
