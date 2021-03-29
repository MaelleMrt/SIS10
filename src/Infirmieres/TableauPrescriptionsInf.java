/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infirmieres;

import Medecin.*;
import Connexion.ExempleJdbc;
import Patient.PatientHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 * crée un modèle de tableau contenant la liste des prescriptions d'un patient
 *
 * @author Maelle
 */
public class TableauPrescriptionsInf extends AbstractTableModel {

    /**
     * liste des prescriptions d'un patient
     */
    private ArrayList<PrescriptionInf> listPrescription = new ArrayList<PrescriptionInf>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Service ", "Medecin", "Date", "Contenu", "valider", "Date Validation", "Infirmier"};
    /**
     * service dans lequel est le patient
     */
    private String service;

    /**
     * Constructeur TableauPrescriptionInf initialise les attributs, remplit la
     * liste des prescription du patient en interrogeant la base de données
     *
     * @param p le patient
     * @param nomS le nom du service
     * @see PatientHop
     */
    public TableauPrescriptionsInf(PatientHop p, String nomS) {
        service = nomS;
        String date;
        String contenu;
        String login = null;
        String nomM;
        String dateVal;
        String loginInf;
        String nomInf = null;
        Boolean valider;

        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs1 = s.executeQuery("SELECT date,contenu,login,valider,dateVal,loginInf FROM Prescription WHERE idP ='" + p.getId() + "'");
                while (rs1.next()) {
                    date = rs1.getString("date");
                    contenu = rs1.getString("contenu");
                    login = rs1.getString("login");
                    valider = rs1.getBoolean("valider");
                    dateVal = rs1.getString("dateVal");
                    loginInf = rs1.getString("loginInf");
                    ResultSet rs3 = s.executeQuery("SELECT nom FROM Infirmier WHERE login ='" + loginInf + "'");
                    while (rs3.next()) {
                        nomInf = rs3.getString("nom");
                    }
                    ResultSet rs2 = s.executeQuery("SELECT nom FROM Médecin WHERE login ='" + login + "'AND nomS='" + service + "'");
                    while (rs2.next()) {
                        nomM = rs2.getString("nom");
                        PrescriptionInf pres = new PrescriptionInf(service, nomM, date, contenu, valider, dateVal, loginInf);
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
     * @return le nombre de lignes dans le tableau = le nombre de prescriptions
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
     * @param column l'indice de la colonne
     * @return la classe de la colonne
     */
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Boolean.class;
            case 5:
                return String.class;
            default:
                return String.class;
        }
    }

    /**
     * 
     * @param rowIndex l'indice de la ligne
     * @param columnIndex l'indice de la colonne
     * @return la valeur de la case à la ligne n° rowIndex et à la colonne n° columnIndex
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
            case 4:
                return listPrescription.get(rowIndex).getValider();
            case 5:
                return listPrescription.get(rowIndex).getDateVal();
            case 6:
                return listPrescription.get(rowIndex).getNomInf();
            default:
                return null; //Ne devrait jamais arriver
        }

    }

    /**
     * 
     * @return la liste de prescriptions
     */
    public ArrayList<PrescriptionInf> getListPrescription() {
        return listPrescription;
    }
}
