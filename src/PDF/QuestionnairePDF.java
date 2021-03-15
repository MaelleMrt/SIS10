/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import Patient.PatientHop;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author clara
 */


public class QuestionnairePDF {
    private static String FILE;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);
    private static Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    
    private ArrayList<String> reponses;
    private PatientHop patient;
    
    public QuestionnairePDF(ArrayList<String> l, PatientHop p){
        this.reponses = l;
        this.patient = p;
        
        this.FILE = "src/PDF/QuestionnaireAnesthésie" + this.patient.getId() + ".pdf";
        try{
            //On crée un nouveau document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            //On ouvre le document
            document.open();
            //On ajoute un paragraphe
            Paragraph paragraphe = new Paragraph();
            //On ajoute un titre
            Paragraph titre = new Paragraph("Questionnaire Anesthésie", catFont);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            //On saute une ligne
            addEmptyLine(paragraphe, 2);

            paragraphe.add(new Paragraph("Patient : " + this.patient.getNomUsuel() + " " + this.patient.getPrenom(), subFont));
            addEmptyLine(paragraphe, 1);


            Paragraph p1 = new Paragraph();
            Chunk c = new Chunk("   Taille : ", font);
            p1.add(c);
            Chunk c1 = new Chunk(reponses.get(0));
            Chunk c2 = new Chunk(" cm");
            p1.add(c1);
            p1.add(c2);
            paragraphe.add(p1);
            addEmptyLine(paragraphe, 1);

            Paragraph p2 = new Paragraph();
            Chunk c3 = new Chunk("   Poids : ", font);
            p2.add(c3);
            Chunk c4 = new Chunk(reponses.get(1));
            Chunk c5 = new Chunk(" kg");
            p2.add(c4);
            p2.add(c5);
            paragraphe.add(p2);
            addEmptyLine(paragraphe, 1);

            Paragraph p3 = new Paragraph();
            Chunk c6 = new Chunk("   Fumeur : ", font);
            p3.add(c6);
            Chunk c7 = new Chunk(reponses.get(2));
            p3.add(c7);
            paragraphe.add(p3);
            addEmptyLine(paragraphe, 1);

            Paragraph p4 = new Paragraph();
            Chunk c8 = new Chunk("   Vit seul : ", font);
            p4.add(c8);
            Chunk c9 = new Chunk(reponses.get(3));
            p4.add(c9);
            paragraphe.add(p4);
            addEmptyLine(paragraphe, 1);

            Paragraph p5 = new Paragraph();
            Chunk c10 = new Chunk("   Allergies : ", font);
            p5.add(c10);
            Chunk c11 = new Chunk(reponses.get(4));
            p5.add(c11);
            paragraphe.add(p5);
            addEmptyLine(paragraphe, 1);

            Paragraph p8 = new Paragraph();
            Chunk c16 = new Chunk("   Ouverture de bouche : ", font);
            p8.add(c16);
            Chunk c17 = new Chunk(reponses.get(5));
            p8.add(c17);
            paragraphe.add(p8);
            addEmptyLine(paragraphe, 1);
            
            paragraphe.add(new Paragraph("Antécédents : ", subFont));

            addEmptyLine(paragraphe, 1);
            
            Paragraph p6 = new Paragraph();
            Chunk c12 = new Chunk("   Maladie respiratoire : ", font);
            p6.add(c12);
            Chunk c13 = new Chunk(reponses.get(6));
            p6.add(c13);
            paragraphe.add(p6);
            addEmptyLine(paragraphe, 1);

            Paragraph p7 = new Paragraph();
            Chunk c14 = new Chunk("   Maladie cardiaque : ", font);
            p7.add(c14);
            Chunk c15 = new Chunk(reponses.get(7));
            p7.add(c15);
            paragraphe.add(p7);
            addEmptyLine(paragraphe, 2);
            
            
            Paragraph p9 = new Paragraph();
            Chunk c18 = new Chunk("   Autre maladie/ Remarques : ", font);
            p9.add(c18);
            Chunk c19 = new Chunk(reponses.get(8));
            p9.add(c19);
            paragraphe.add(p9);
            addEmptyLine(paragraphe, 1);

            document.add(paragraphe);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
