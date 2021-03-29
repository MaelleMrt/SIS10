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
 * crée un modèle de tableau avec la liste des prescriptions d'un patient
 *
 * @author Maelle
 */
public class TableauPrescriptions extends AbstractTableModel {

    /**
     * liste des prescriptions du patient
     */
    private ArrayList<Prescription> listPrescription = new ArrayList<Prescription>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Service ", "Medecin", "Date", "Contenu"};

    /**
     * Constructeur TableauPrescriptions
     * remplit la liste en interrogeant la base de données
     * @param p le patient
     */
    public TableauPrescriptions(PatientHop p) {
        String date;
        String contenu;
        String login = null;
        String nomM;
        String nomS;
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs1 = s.executeQuery("SELECT date,contenu,login FROM Prescription WHERE idP ='" + p.getId() + "'");
                while (rs1.next()) {
                    date = rs1.getString("date");
                    contenu = rs1.getString("contenu");
                    login = rs1.getString("login");
                    ResultSet rs2 = s.executeQuery("SELECT nom,nomS FROM Médecin WHERE login ='" + login + "'");
                    while (rs2.next()) {
                        nomM = rs2.getString("nom");
                        nomS = rs2.getString("nomS");
                        Prescription pres = new Prescription(nomS, nomM, date, contenu);
                        listPrescription.add(pres);
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
     * @return le nombre de lignes du tableau, c'est-à-dire le nombre de prescriptions
     */
    public int getRowCount() {
        return listPrescription.size();
    }

    /**
     * 
     * @return le nombre de colonnes du tableau
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
                return listPrescription.get(rowIndex).getService();
            case 1:
                return listPrescription.get(rowIndex).getMedecin();
            case 2:
                return listPrescription.get(rowIndex).getDate();
            case 3:
                return listPrescription.get(rowIndex).getContenu();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    /**
     * 
     * @return la liste des prescriptions
     */
    public ArrayList<Prescription> getListPrescription() {
        return listPrescription;
    }
}
