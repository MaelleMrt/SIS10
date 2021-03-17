/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import Medecin.LettreDeSortie;
import Patient.PatientHop;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author clara
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        LettreDeSortie lettre = new LettreDeSortie("3645278193","coordonnées du service","médecin","nom prénom","date1","date2","date3","motif","exam","ph","traitement","synthèse","suivi");
//        LettreDeSortiePDF l = new LettreDeSortiePDF(lettre);
//        LirePDF lecture = new LirePDF("3645278193");
//        Desktop.getDesktop().open(new File("C:/Users/clara/Desktop/TIS4/S8/Projet SIS/Code/SIS10/src/PDF/LettreSortie123456789.pdf"));
        PatientHop p = new PatientHop("Oster","Oster","Clara","1999-05-22",123456789);
        ArrayList<Boolean> l = new ArrayList<>();
        l.add(Boolean.TRUE);
        l.add(Boolean.TRUE);
        l.add(Boolean.TRUE);
        l.add(Boolean.FALSE);
        l.add(Boolean.TRUE);
        l.add(Boolean.TRUE);
        l.add(Boolean.TRUE);
        new QuestionnaireCovidPDF(l,p);
        
    }
    
}
