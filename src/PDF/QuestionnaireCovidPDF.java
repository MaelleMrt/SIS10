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
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import java.io.FileOutputStream;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author clara
 */
public class QuestionnaireCovidPDF {

    private static String FILE;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);
    private static Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    char checked = '\u00FE';
    char unchecked = '\u00A8';

    private ArrayList<Boolean> reponses = new ArrayList<>();
    private PatientHop patient;

    public QuestionnaireCovidPDF(ArrayList<Boolean> reponses, PatientHop patient) {
        try {
            this.reponses = reponses;
            this.patient = patient;
            this.FILE = "src/PDF/QuestionnaireCovid" + this.patient.getId() + ".pdf";

            //On crée un nouveau document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            //On ouvre le document
            document.open();
            //On ajoute un paragraphe
            Paragraph paragraphe = new Paragraph();
            //On ajoute un titre
            Paragraph titre = new Paragraph("Questionnaire Covid", catFont);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            //On saute une ligne
            addEmptyLine(paragraphe, 2);

            paragraphe.add(new Paragraph("Patient : " + this.patient.getNomUsuel() + " " + this.patient.getPrenom(), subFont));
            addEmptyLine(paragraphe, 1);
            paragraphe.add(new Paragraph("Avez-vous présenté un ou plusieurs des symptômes suivants : ", subFont));

            addEmptyLine(paragraphe, 1);

            Paragraph p1 = new Paragraph();
            Chunk c = new Chunk("   Fièvre (>38°):", font);
            p1.add(c);
            if (this.reponses.get(0).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p1.add(c1);
                p1.add(c2);
                p1.add(c3);
                p1.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p1.add(c1);
                p1.add(c2);
                p1.add(c3);
                p1.add(c4);
            }
            paragraphe.add(p1);
            addEmptyLine(paragraphe, 1);

            Paragraph p2 = new Paragraph();
            Chunk a = new Chunk("   Toux :", font);
            p2.add(a);
            if (this.reponses.get(1).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p2.add(c1);
                p2.add(c2);
                p2.add(c3);
                p2.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p2.add(c1);
                p2.add(c2);
                p2.add(c3);
                p2.add(c4);
            }
            paragraphe.add(p2);
            addEmptyLine(paragraphe, 1);

            Paragraph p3 = new Paragraph();
            Chunk z = new Chunk("   Difficultés à respirer :", font);
            p3.add(z);
            if (this.reponses.get(2).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p3.add(c1);
                p3.add(c2);
                p3.add(c3);
                p3.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p3.add(c1);
                p3.add(c2);
                p3.add(c3);
                p3.add(c4);
            }
            paragraphe.add(p3);
            addEmptyLine(paragraphe, 1);

            Paragraph p4 = new Paragraph();
            Chunk e = new Chunk("   Perte du goût ou de l'odorat :", font);
            p4.add(e);
            if (this.reponses.get(3).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p4.add(c1);
                p4.add(c2);
                p4.add(c3);
                p4.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p4.add(c1);
                p4.add(c2);
                p4.add(c3);
                p4.add(c4);
            }
            paragraphe.add(p4);
            addEmptyLine(paragraphe, 1);

            Paragraph p5 = new Paragraph();
            Chunk r = new Chunk("   Syndrome grippal :", font);
            p5.add(r);
            if (this.reponses.get(4).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p5.add(c1);
                p5.add(c2);
                p5.add(c3);
                p5.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p5.add(c1);
                p5.add(c2);
                p5.add(c3);
                p5.add(c4);
            }
            paragraphe.add(p5);
            addEmptyLine(paragraphe, 1);

            Paragraph p6 = new Paragraph();
            Chunk t = new Chunk("   Contact sans masque avec une personne porteuse de la covid :", font);
            p6.add(t);
            if (this.reponses.get(5).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p6.add(c1);
                p6.add(c2);
                p6.add(c3);
                p6.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p6.add(c1);
                p6.add(c2);
                p6.add(c3);
                p6.add(c4);
            }
            paragraphe.add(p6);
            addEmptyLine(paragraphe, 1);

            Paragraph p7 = new Paragraph();
            Chunk y = new Chunk("   Etes-vous revenu d’un voyage à l’étranger ou d’un rassemblement de nombreuses personnes :", font);
            p7.add(y);
            if (this.reponses.get(6).equals(Boolean.TRUE)) {
                Chunk c1 = new Chunk(String.valueOf(checked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(unchecked));
                Chunk c4 = new Chunk("non", font);
                p7.add(c1);
                p7.add(c2);
                p7.add(c3);
                p7.add(c4);
            } else {
                Chunk c1 = new Chunk(String.valueOf(unchecked));
                Chunk c2 = new Chunk("oui     ", font);
                Chunk c3 = new Chunk(String.valueOf(checked));
                Chunk c4 = new Chunk("non", font);
                p7.add(c1);
                p7.add(c2);
                p7.add(c3);
                p7.add(c4);
            }
            paragraphe.add(p7);

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
