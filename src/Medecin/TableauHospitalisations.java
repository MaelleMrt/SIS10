/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import Connexion.ExempleJdbc;
import Patient.PatientHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 * crée un modèle de tableau avec la liste des hospitalisations d'un patient
 *
 * @author Maelle
 */
public class TableauHospitalisations extends AbstractTableModel {

    /**
     * liste des hospitalisations du patient
     */
    private ArrayList<Hospitalisations> listHospi = new ArrayList<Hospitalisations>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Service ", "Medecin", "Date ", "motif"};

    /**
     * Constructeur TableauHospitalisations
     * remplit la liste des hospitalisations en interrogeant la base de données
     * @param p le patient
     */
    public TableauHospitalisations(PatientHop p) {
        String date;
        String contenu;
        String login = null;
        String nomM;
        String nomS;
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs1 = s.executeQuery("SELECT date,motif,login FROM Acte WHERE idP ='" + p.getId() + "'AND type='Hospitalisation'");
                while (rs1.next()) {
                    date = rs1.getString("date");
                    contenu = rs1.getString("motif");
                    login = rs1.getString("login");
                    ResultSet rs2 = s.executeQuery("SELECT nom,nomS FROM Médecin WHERE login ='" + login + "'");
                    while (rs2.next()) {
                        nomM = rs2.getString("nom");
                        nomS = rs2.getString("nomS");
                        Hospitalisations pres = new Hospitalisations(nomS, nomM, date, contenu);
                        System.out.println("ajout" + contenu);
                        listHospi.add(pres);
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
     * @return le nombre de lignes du tableau, c'est-à-dire le nombre d'hospitalisations
     */
    public int getRowCount() {
        return listHospi.size();
    }

    /**
     * 
     * @return le nombre de colonnes
     */
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:

                return listHospi.get(rowIndex).getService();
            case 1:

                return listHospi.get(rowIndex).getNomMedecin();
            case 2:

                return listHospi.get(rowIndex).getDate();
            case 3:

                return listHospi.get(rowIndex).getMotif();
            default:
                return null; //Ne devrait jamais arriver
        }

    }

    /**
     * 
     * @return la liste des hospitalisation
     */
    public ArrayList<Hospitalisations> getListHospitalisations() {
        return listHospi;
    }
}
