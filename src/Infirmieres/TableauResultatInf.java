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
 * crée un modèle de tableau contenant la liste des résultats d'un patient
 *
 * @author Maelle
 */
public class TableauResultatInf extends AbstractTableModel {

    /**
     * liste des résultats d'un patient
     */
    private ArrayList<Resultat> listResultat = new ArrayList<Resultat>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Service ", "Medecin", "Date", "Contenu"};
    /**
     * nom du service dans lequel est le patient
     */
    public String service;

    /**
     * Constructeur TableauResultatInf
     * initialise les attributs, remplit la liste des résultats en interrogeant la base de données
     * @param p le patient
     * @param nomS le nom du service
     */
    public TableauResultatInf(PatientHop p, String nomS) {
        service = nomS;
        String date;
        String contenu;
        String login = null;
        String nomM;

        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs1 = s.executeQuery("SELECT date,contenu,login FROM CR WHERE idP ='" + p.getId() + "'");
                while (rs1.next()) {
                    date = rs1.getString("date");
                    contenu = rs1.getString("contenu");
                    System.out.println(contenu);
                    login = rs1.getString("login");
                    ResultSet rs2 = s.executeQuery("SELECT nom FROM Médecin WHERE login ='" + login + "'AND nomS='" + service + "'");
                    while (rs2.next()) {
                        nomM = rs2.getString("nom");

                        Resultat res = new Resultat(service, nomM, date, contenu);
                        listResultat.add(res);
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
     * @return le nombre de lignes du tableau = le nombre de résultats
     */
    public int getRowCount() {
        return listResultat.size();
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
                return listResultat.get(rowIndex).getService();
            case 1:
                return listResultat.get(rowIndex).getMedecin();
            case 2:
                return listResultat.get(rowIndex).getDate();
            case 3:
                return listResultat.get(rowIndex).getContenu();
            default:
                return null; //Ne devrait jamais arriver
        }

    }

    /**
     * 
     * @return la liste des résultats
     */
    public ArrayList<Resultat> getListResultat() {
        return listResultat;
    }
}
