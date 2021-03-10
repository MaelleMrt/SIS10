/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tri;

import CIC.Etude;
import CIC.Participant;
import java.util.ArrayList;

/**
 *
 * @author clara
 */
public class Tri {
    
    public Tri(){
        
    }
    
    public ArrayList<Etude> trierEtudesParDates(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getDate().compareTo(e1.getDate()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(e1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Participant> trierParticipantsParDates(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getDateN().compareTo(p1.getDateN()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(p1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Etude> trierEtudesParNom(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getNom().toUpperCase().compareTo(e1.getNom().toUpperCase()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(e1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Etude> trierEtudesParPH(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getPH().toUpperCase().compareTo(e1.getPH().toUpperCase()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(e1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Participant> trierParticipantsParNom(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getNomU().toUpperCase().compareTo(p1.getNomU().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(p1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Participant> trierParticipantsParPrenom(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getPrenom().toUpperCase().compareTo(p1.getPrenom().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(p1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    public ArrayList<Participant> trierParticipantsParType(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getType().toUpperCase().compareTo(p1.getType().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            // on ajoute la fiche de soins trouvee :
            listeTriee.add(p1);
            //on la supprime de la liste :
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
}
