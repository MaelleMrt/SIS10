/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tri;

import CIC.Etude;
import CIC.Participant;
import Infirmieres.PrescriptionInf;
import Infirmieres.Soins;
import Medecin.Consultations;
import Medecin.Hospitalisations;
import Medecin.Prescription;
import Medecin.RdvMedecin;
import Medecin.Resultat;
import Patient.PatientHop;
import java.util.ArrayList;

/**
 * 
 * @author clara
 */
public class Tri {
    
    /**
     * Constructeur Tri 
     */
    public Tri(){
    }
    
    /**
     * trie une liste d'études par date
     * @param liste liste d'études
     * @return la liste triée
     */
    public ArrayList<Etude> trierEtudesParDates(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getDate().compareTo(e1.getDate()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            listeTriee.add(e1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de participants par date
     * @param liste liste de participants
     * @return la liste triée
     */
    public ArrayList<Participant> trierParticipantsParDates(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getDateN().compareTo(p1.getDateN()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste d'études par nom
     * @param liste liste d'études
     * @return la liste triée
     */
    public ArrayList<Etude> trierEtudesParNom(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getNom().toUpperCase().compareTo(e1.getNom().toUpperCase()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            listeTriee.add(e1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste d'études par praticien hospitalier
     * @param liste liste d'études
     * @return la liste triée
     */
    public ArrayList<Etude> trierEtudesParPH(ArrayList<Etude> liste) {
        ArrayList<Etude> copieListe = new ArrayList<Etude>(liste);
        ArrayList<Etude> listeTriee = new ArrayList<Etude>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Etude e1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Etude e2 = copieListe.get(i);
                if (e2.getPH().toUpperCase().compareTo(e1.getPH().toUpperCase()) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            listeTriee.add(e1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de participants par nom
     * @param liste liste de participants
     * @return la liste triée
     */
    public ArrayList<Participant> trierParticipantsParNom(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getNomU().toUpperCase().compareTo(p1.getNomU().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de participants par prénom
     * @param liste liste de participants
     * @return la liste triée
     */
    public ArrayList<Participant> trierParticipantsParPrenom(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getPrenom().toUpperCase().compareTo(p1.getPrenom().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de participants par type
     * @param liste liste de participants
     * @return la liste triée
     */
    public ArrayList<Participant> trierParticipantsParType(ArrayList<Participant> liste) {
        ArrayList<Participant> copieListe = new ArrayList<Participant>(liste);
        ArrayList<Participant> listeTriee = new ArrayList<Participant>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Participant p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Participant p2 = copieListe.get(i);
                if (p2.getType().toUpperCase().compareTo(p1.getType().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de patients par nom
     * @param liste liste de patients
     * @return la liste triée
     */
    public ArrayList<PatientHop> trierPatientsParNom(ArrayList<PatientHop> liste) {
        ArrayList<PatientHop> copieListe = new ArrayList<PatientHop>(liste);
        ArrayList<PatientHop> listeTriee = new ArrayList<PatientHop>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PatientHop p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PatientHop p2 = copieListe.get(i);
                if (p2.getNomUsuel().toUpperCase().compareTo(p1.getNomUsuel().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de patients par prénom
     * @param liste liste de patients
     * @return la liste triée
     */
    public ArrayList<PatientHop> trierPatientsParPrenom(ArrayList<PatientHop> liste) {
        ArrayList<PatientHop> copieListe = new ArrayList<PatientHop>(liste);
        ArrayList<PatientHop> listeTriee = new ArrayList<PatientHop>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PatientHop p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PatientHop p2 = copieListe.get(i);
                if (p2.getPrenom().toUpperCase().compareTo(p1.getPrenom().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de patients par date
     * @param liste liste de patients
     * @return la liste triée
     */
    public ArrayList<PatientHop> trierPatientsParDate(ArrayList<PatientHop> liste) {
        ArrayList<PatientHop> copieListe = new ArrayList<PatientHop>(liste);
        ArrayList<PatientHop> listeTriee = new ArrayList<PatientHop>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PatientHop p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PatientHop p2 = copieListe.get(i);
                if (p2.getNaissance().compareTo(p1.getNaissance()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de prescriptions par date
     * @param liste liste de prescriptions
     * @return la liste triée
     */
    public ArrayList<PrescriptionInf> trierPrescriptionParDate(ArrayList<PrescriptionInf> liste) {
        ArrayList<PrescriptionInf> copieListe = new ArrayList<PrescriptionInf>(liste);
        ArrayList<PrescriptionInf> listeTriee = new ArrayList<PrescriptionInf>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PrescriptionInf p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PrescriptionInf p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de prescriptions par service
     * @param liste liste de prescriptions
     * @return la liste triée
     */
    public ArrayList<PrescriptionInf> trierPrescriptionParService(ArrayList<PrescriptionInf> liste) {
        ArrayList<PrescriptionInf> copieListe = new ArrayList<PrescriptionInf>(liste);
        ArrayList<PrescriptionInf> listeTriee = new ArrayList<PrescriptionInf>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PrescriptionInf p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PrescriptionInf p2 = copieListe.get(i);
                if (p2.getService().toUpperCase().compareTo(p1.getService().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de prescriptions par médecin
     * @param liste liste de prescriptions
     * @return la liste triée
     */
    public ArrayList<PrescriptionInf> trierPrescriptionParMedecin(ArrayList<PrescriptionInf> liste) {
        ArrayList<PrescriptionInf> copieListe = new ArrayList<PrescriptionInf>(liste);
        ArrayList<PrescriptionInf> listeTriee = new ArrayList<PrescriptionInf>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PrescriptionInf p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PrescriptionInf p2 = copieListe.get(i);
                if (p2.getMedecin().toUpperCase().compareTo(p1.getMedecin().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de prescriptions par infirmière
     * @param liste liste de prescriptions
     * @return la liste triée
     */
    public ArrayList<PrescriptionInf> trierPrescriptionParInf(ArrayList<PrescriptionInf> liste) {
        ArrayList<PrescriptionInf> copieListe = new ArrayList<PrescriptionInf>(liste);
        ArrayList<PrescriptionInf> listeTriee = new ArrayList<PrescriptionInf>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PrescriptionInf p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PrescriptionInf p2 = copieListe.get(i);
                if (p2.getNomInf().toUpperCase().compareTo(p1.getNomInf().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de prescriptions par date de validation
     * @param liste liste de prescriptions
     * @return la liste triée
     */
    public ArrayList<PrescriptionInf> trierPrescriptionParDateValid(ArrayList<PrescriptionInf> liste) {
        ArrayList<PrescriptionInf> copieListe = new ArrayList<PrescriptionInf>(liste);
        ArrayList<PrescriptionInf> listeTriee = new ArrayList<PrescriptionInf>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            PrescriptionInf p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                PrescriptionInf p2 = copieListe.get(i);
                if (p2.getDateVal().compareTo(p1.getDateVal()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de résultats par date
     * @param liste liste de résultats
     * @return la liste triée
     */
    public ArrayList<Resultat> trierResultatParDate(ArrayList<Resultat> liste) {
        ArrayList<Resultat> copieListe = new ArrayList<Resultat>(liste);
        ArrayList<Resultat> listeTriee = new ArrayList<Resultat>();
        
        while (!copieListe.isEmpty()) {
            
            int imin = 0;
            Resultat p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Resultat p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de résultats par service
     * @param liste liste de résultats
     * @return la liste triée
     */
    public ArrayList<Resultat> trierResultatParService(ArrayList<Resultat> liste) {
        ArrayList<Resultat> copieListe = new ArrayList<Resultat>(liste);
        ArrayList<Resultat> listeTriee = new ArrayList<Resultat>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Resultat p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Resultat p2 = copieListe.get(i);
                if (p2.getService().toUpperCase().compareTo(p1.getService().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de résultats par médecin
     * @param liste liste de résultats
     * @return la liste triée
     */
    public ArrayList<Resultat> trierResultatParMedecin(ArrayList<Resultat> liste) {
        ArrayList<Resultat> copieListe = new ArrayList<Resultat>(liste);
        ArrayList<Resultat> listeTriee = new ArrayList<Resultat>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Resultat p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Resultat p2 = copieListe.get(i);
                if (p2.getMedecin().toUpperCase().compareTo(p1.getMedecin().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste d'hospitalisations par date
     * @param liste liste d'hospitalisations
     * @return la liste triée
     */
    public ArrayList<Hospitalisations> trierHospitParDate(ArrayList<Hospitalisations> liste) {
        ArrayList<Hospitalisations> copieListe = new ArrayList<Hospitalisations>(liste);
        ArrayList<Hospitalisations> listeTriee = new ArrayList<Hospitalisations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Hospitalisations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Hospitalisations p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste d'hospitalisations par service
     * @param liste liste d'hospitalisations
     * @return la liste triée
     */
    public ArrayList<Hospitalisations> trierHospitParService(ArrayList<Hospitalisations> liste) {
        ArrayList<Hospitalisations> copieListe = new ArrayList<Hospitalisations>(liste);
        ArrayList<Hospitalisations> listeTriee = new ArrayList<Hospitalisations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Hospitalisations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Hospitalisations p2 = copieListe.get(i);
                if (p2.getService().toUpperCase().compareTo(p1.getService().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste d'hospitalisations par médecin
     * @param liste liste d'hospitalisations
     * @return la liste triée
     */
    public ArrayList<Hospitalisations> trierHospitParMedecin(ArrayList<Hospitalisations> liste) {
        ArrayList<Hospitalisations> copieListe = new ArrayList<Hospitalisations>(liste);
        ArrayList<Hospitalisations> listeTriee = new ArrayList<Hospitalisations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Hospitalisations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Hospitalisations p2 = copieListe.get(i);
                if (p2.getNomMedecin().toUpperCase().compareTo(p1.getNomMedecin().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de consultations par date
     * @param liste liste de consultations
     * @return la liste triée
     */
    public ArrayList<Consultations> trierConsultParDate(ArrayList<Consultations> liste) {
        ArrayList<Consultations> copieListe = new ArrayList<Consultations>(liste);
        ArrayList<Consultations> listeTriee = new ArrayList<Consultations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Consultations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Consultations p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de consultations par service
     * @param liste liste de consultations
     * @return la liste triée
     */
    public ArrayList<Consultations> trierConsultParService(ArrayList<Consultations> liste) {
        ArrayList<Consultations> copieListe = new ArrayList<Consultations>(liste);
        ArrayList<Consultations> listeTriee = new ArrayList<Consultations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Consultations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Consultations p2 = copieListe.get(i);
                if (p2.getService().toUpperCase().compareTo(p1.getService().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de consultations par médecin
     * @param liste liste de consultations
     * @return la liste triée
     */
    public ArrayList<Consultations> trierConsultParMedecin(ArrayList<Consultations> liste) {
        ArrayList<Consultations> copieListe = new ArrayList<Consultations>(liste);
        ArrayList<Consultations> listeTriee = new ArrayList<Consultations>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Consultations p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Consultations p2 = copieListe.get(i);
                if (p2.getNomMedecin().toUpperCase().compareTo(p1.getNomMedecin().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de soins par infirmière
     * @param liste liste de soins
     * @return la liste triée
     */
    public ArrayList<Soins> trierSoinsParInf(ArrayList<Soins> liste) {
        ArrayList<Soins> copieListe = new ArrayList<Soins>(liste);
        ArrayList<Soins> listeTriee = new ArrayList<Soins>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Soins p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Soins p2 = copieListe.get(i);
                if (p2.getNomInf().toUpperCase().compareTo(p1.getNomInf().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de soins par date
     * @param liste liste de soins
     * @return la liste triée
     */
    public ArrayList<Soins> trierSoinsParDate(ArrayList<Soins> liste) {
        ArrayList<Soins> copieListe = new ArrayList<Soins>(liste);
        ArrayList<Soins> listeTriee = new ArrayList<Soins>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            Soins p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                Soins p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de rdv par nom
     * @param liste liste de rdv
     * @return la liste triée
     */
    public ArrayList<RdvMedecin> trierRDVParNom(ArrayList<RdvMedecin> liste) {
        ArrayList<RdvMedecin> copieListe = new ArrayList<RdvMedecin>(liste);
        ArrayList<RdvMedecin> listeTriee = new ArrayList<RdvMedecin>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            RdvMedecin p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                RdvMedecin p2 = copieListe.get(i);
                if (p2.getNom().toUpperCase().compareTo(p1.getNom().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de rdv par prénom
     * @param liste liste de rdv
     * @return la liste triée
     */
    public ArrayList<RdvMedecin> trierRDVParPrenom(ArrayList<RdvMedecin> liste) {
        ArrayList<RdvMedecin> copieListe = new ArrayList<RdvMedecin>(liste);
        ArrayList<RdvMedecin> listeTriee = new ArrayList<RdvMedecin>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            RdvMedecin p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                RdvMedecin p2 = copieListe.get(i);
                if (p2.getPrenom().toUpperCase().compareTo(p1.getPrenom().toUpperCase()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de rdv par date
     * @param liste liste de rdv
     * @return la liste triée
     */
    public ArrayList<RdvMedecin> trierRDVParDate(ArrayList<RdvMedecin> liste) {
        ArrayList<RdvMedecin> copieListe = new ArrayList<RdvMedecin>(liste);
        ArrayList<RdvMedecin> listeTriee = new ArrayList<RdvMedecin>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            RdvMedecin p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                RdvMedecin p2 = copieListe.get(i);
                if (p2.getDate().compareTo(p1.getDate()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    /**
     * trie une liste de rdv par date de naissance
     * @param liste liste de rdv
     * @return la liste triée
     */
    public ArrayList<RdvMedecin> trierRDVParNaissance(ArrayList<RdvMedecin> liste) {
        ArrayList<RdvMedecin> copieListe = new ArrayList<RdvMedecin>(liste);
        ArrayList<RdvMedecin> listeTriee = new ArrayList<RdvMedecin>();
        
        while (!copieListe.isEmpty()) {
            int imin = 0;
            RdvMedecin p1 = copieListe.get(imin);
            for (int i = 1; i < copieListe.size(); i++) {
                RdvMedecin p2 = copieListe.get(i);
                if (p2.getDateN().compareTo(p1.getDateN()) < 0) {
                    imin = i;
                    p1 = p2;
                }
            }
            listeTriee.add(p1);
            copieListe.remove(imin);
        }
        return listeTriee;
    }
    
    
    
}
