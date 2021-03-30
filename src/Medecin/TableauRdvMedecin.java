/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import MedecinAnesthesiste.Rdv;
import Connexion.ExempleJdbc;
import Secretaire.RDV;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * crée un modèle de tableau avec la liste des rendez-vous d'un médecin
 *
 * @author amira
 */
public class TableauRdvMedecin extends AbstractTableModel {

    /**
     * liste des rendez-vous d'un médecin
     *
     * @see RdvMedecin
     */
    private ArrayList<RdvMedecin> listRdv = new ArrayList<RdvMedecin>();

    /**
     * le médecin
     *
     * @see Medecin
     */
    private Medecin medecin;

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Motif ", "Date", "Heure", "Nom", "Prenom", "Date de Naissance",};

    /**
     * Constructeur TableauRdvMedecin
     * initialise les attributs et remplit la liste des rdv en interrogeant la base de données
     * @param m le médecin
     */
    public TableauRdvMedecin(Medecin m) {

        medecin = m;
        String motif;
        String date;
        String heure;
        String nomPrenomPat;
        String nomPrenomMed = this.medecin.getPrenom() + " " + this.medecin.getNom();
        int idPat;
        String prenom = null;
        String nom = null;
        String dateN = null;
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rdv = s.executeQuery("SELECT Motif, Date, Heure, Patient, idPatient FROM RendezVous WHERE Médecin ='" + nomPrenomMed + "'");
                while (rdv.next()) {
                    idPat = rdv.getInt("idPatient");
                    date = rdv.getString("Date");
                    motif = rdv.getString("Motif");
                    System.out.println(motif);
                    heure = rdv.getString("Heure");
                    try {
                        ResultSet id = s.executeQuery("SELECT nomusuel, prenom, datedenaissance from Patient WHERE id ='" + idPat + "'");
                        while (id.next()) {
                            prenom = id.getString("prenom");
                            nom = id.getString("nomusuel");
                            dateN = id.getString("datedenaissance");
                            RdvMedecin rd = new RdvMedecin(motif, date, heure, nom, prenom, dateN);
                            listRdv.add(rd);
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     * @return le nombre de ligne dans le tableau, c'est-à-dire le nombre de rdv
     */
    @Override
    public int getRowCount() {
        return listRdv.size();
    }

    /**
     * 
     * @return le nombre de colonnes dans le tableau
     */
    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    /**
     * 
     * @param columnIndex l'indice de la colonne
     * @return le nom de la colonne
     */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    /**
     * 
     * @param rowIndex l'indice de la ligne
     * @param columnIndex l'indice de la colonne
     * @return la valeur de la case à la ligne n°rowIndex et à la colonne n°columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listRdv.get(rowIndex).getMotif();
            case 1:
                return listRdv.get(rowIndex).getDate();
            case 2:
                return listRdv.get(rowIndex).getHeure();
            case 3:
                return listRdv.get(rowIndex).getNom();
            case 4:
                return listRdv.get(rowIndex).getPrenom();
            case 5:
                return listRdv.get(rowIndex).getDateN();

            default:
                return null; //Ne devrait jamais arriver
        }
    }

    /**
     * 
     * @return la liste des rdv
     */
    public ArrayList<RdvMedecin> getListRdv() {
        return listRdv;
    }

}
