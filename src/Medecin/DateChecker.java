/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Vérifie si une date donnée en String est bien dans le bon format
 * @author Maelle
 */
public class DateChecker {
    /**
     * 
     * @param strdate la date donnée en String
     * @return true si la date est valide et false sinon
     */
    public static boolean isValid(String strdate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            Date date = df.parse(strdate);
            return true;
        } catch (ParseException ex) {
            //Logger.getLogger(DateChecker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
