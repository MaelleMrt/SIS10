/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

/**
 *
 * @author clara
 */
import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LirePDF {

    PdfReader reader;

    String document = "C:/Users/clara/Desktop/TIS4/S8/Projet SIS/Code/SIS10/src/PDF/LettreSortie";

    public LirePDF(String doc){
        
        this.document += doc + ".pdf";
        try {
            reader = new PdfReader(document);
                    System.out.println(PdfTextExtractor.getTextFromPage(reader,  reader.getNumberOfPages()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
}
