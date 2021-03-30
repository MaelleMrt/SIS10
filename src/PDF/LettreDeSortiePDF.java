/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import Medecin.LettreDeSortie;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * crée une lettre de sortie sous format PDF
 * @author clara
 */
public class LettreDeSortiePDF {

    /**
     * le chemin du fichier PDF que l'on va générer
     */
    private static String FILE;
    /**
     * une police de caractère : en gras, taille 18 et police Times Roman
     */
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    /**
     * une police de caractère : en gras, taille 14 et police Times Roman
     */
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);
    
    /**
     * la lettre de sortie que l'on veut mettre sous PDF
     */
    private LettreDeSortie lettre;

    /**
     * Constructeur LettreDeSortie
     * génère un PDF avec les informations de la lettre de sortie
     * @param lettre la lettre de sortie
     */
    public LettreDeSortiePDF(LettreDeSortie lettre) {
        try {
            this.lettre = lettre;
            this.FILE = "src/PDF/LettreSortie"+this.lettre.getId()+".pdf";
            
            //On crée un nouveau document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            //On ouvre le document
            document.open();
            //On ajoute un paragraphe
            Paragraph paragraphe1 = new Paragraph();
            //On ajoute un titre
            Paragraph titre = new Paragraph("Lettre de sortie", catFont);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            //On saute une ligne
            addEmptyLine(paragraphe1, 2);

            
            
            paragraphe1.add(new Paragraph("Coordonnées de l'établissement :",subFont));
            paragraphe1.add(new Paragraph("320 Prospect Street, Princeton, NJ 08540"));
            paragraphe1.add(new Paragraph("973-206-1088"));
            
            
            addEmptyLine(paragraphe1, 1);
            
           
            paragraphe1.add(new Paragraph("Coordonnées du service :",subFont));
            paragraphe1.add(new Paragraph("Service de : "+this.lettre.getService()));
            
            addEmptyLine(paragraphe1, 1);
            
            Paragraph pa = new Paragraph();
            pa.add(new Chunk("Date :",subFont));
            Date date = new Date();
            String dateToStr = date.toInstant().atOffset(ZoneOffset.UTC).format( DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Chunk d = new Chunk(dateToStr);
            pa.add(d);
            paragraphe1.add(pa);
            
            
            Paragraph p1 = new Paragraph();
            Chunk med = new Chunk("Médecin traitant : ",subFont);
            Chunk m = new Chunk(this.lettre.getMedecinTraitant());
            p1.add(med);
            p1.add(m);
            paragraphe1.add(p1);
            
            addEmptyLine(paragraphe1, 3);
            
            Paragraph p2 = new Paragraph();
            Chunk pat = new Chunk("Concerne M, Mme, Mlle ", subFont);
            Chunk nom = new Chunk(this.lettre.getPatient());
            p2.add(pat);
            p2.add(nom);
            paragraphe1.add(p2);
            
            Paragraph p3 = new Paragraph();
            Chunk dn = new Chunk("Date de naissance : ",subFont);
            Chunk ddn = new Chunk(this.lettre.getDateNaissance());
            p3.add(dn);
            p3.add(ddn);
            paragraphe1.add(p3);
            
            Paragraph p4 = new Paragraph();
            Chunk de = new Chunk("Date d'entrée : ",subFont);
            Chunk dde = new Chunk(this.lettre.getDateEntree());
            p4.add(de);
            p4.add(dde);
            paragraphe1.add(p4);
            
            Paragraph p5 = new Paragraph();
            Chunk ds = new Chunk("Date de sortie : ",subFont);
            Chunk dds = new Chunk(this.lettre.getDateSortie());
            p5.add(ds);
            p5.add(dds);
            paragraphe1.add(p5);
            addEmptyLine(paragraphe1, 3);
            
            Paragraph motif1 = new Paragraph("Motif d'hospitalisation : ",subFont);
            Paragraph mot = new Paragraph(this.lettre.getMotif());
            paragraphe1.add(motif1);
            paragraphe1.add(mot);
            
            Paragraph examen = new Paragraph("Examen clinique et démarche diagnostique : ",subFont);
            Paragraph e = new Paragraph(this.lettre.getExam());
            paragraphe1.add(examen);
            paragraphe1.add(e);
            
            Paragraph p6 = new Paragraph();
            Chunk ph = new Chunk("Médecin hospitalier responsable : ",subFont);
            Chunk prat = new Chunk(this.lettre.getPH());
            p6.add(ph);
            p6.add(prat);
            paragraphe1.add(p6);
            
            document.add(paragraphe1);
            document.newPage();
            Paragraph paragraphe2 = new Paragraph();
            
            
            Paragraph trait = new Paragraph("Traitement de sortie proposé : ",subFont);
            Paragraph ttt = new Paragraph(this.lettre.getTraitement());
            paragraphe2.add(trait);
            paragraphe2.add(ttt);
            addEmptyLine(paragraphe2, 1);
            
            Paragraph synth = new Paragraph("Synthèse : ",subFont);
            Paragraph s = new Paragraph(this.lettre.getSynthese());
            paragraphe2.add(synth);
            paragraphe2.add(s);
            addEmptyLine(paragraphe2, 1);
            
            Paragraph sui = new Paragraph("Suivi : ",subFont);
            Paragraph su = new Paragraph(this.lettre.getSuivi());
            paragraphe2.add(sui);
            paragraphe2.add(su);
            
            
            document.add(paragraphe2);
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    /**
     * permet de sauter des lignes
     * @param paragraph un paragraphe
     * @param number le nombre de lignes que l'on veut sauter
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
