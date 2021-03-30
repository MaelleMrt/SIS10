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
 * crée un modèle de tableau avec la liste des résultats d'un patient
 *
 * @author Maelle
 */
public class TableauResultat extends AbstractTableModel {

    /**
     * liste des résultats
     *
     * @see Resultat
     */
    private ArrayList<Resultat> listResultats = new ArrayList<Resultat>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Service ", "Medecin", "Date", "Contenu"};

    /**
     * Constructeur TableauResultat
     * remplit la liste en interrogeant la base de données 
     * @param p le patient
     */
    public TableauResultat(PatientHop p) {
        String date;
        String contenu;
        String login = null;
        String nomM;
        String nomS;
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs1 = s.executeQuery("SELECT date,contenu,login FROM CR WHERE idP ='" + p.getId() + "'");
                while (rs1.next()) {
                    date = rs1.getString("date");
                    contenu = rs1.getString("contenu");
                    System.out.println(contenu);
                    login = rs1.getString("login");
                    ResultSet rs2 = s.executeQuery("SELECT nom,nomS FROM Médecin WHERE login ='" + login + "'");
                    while (rs2.next()) {
                        nomM = rs2.getString("nom");
                        nomS = rs2.getString("nomS");
                        Resultat res = new Resultat(nomS, nomM, date, contenu);
                        listResultats.add(res);
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
     * @return le nombre de lignes du tableau, c'est-à-dire le nombre de résultats
     */
    public int getRowCount() {
        return listResultats.size();
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
     * @return la valeur de la case à la ligne n°roxIndex et à la colonne n°columnIndex
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listResultats.get(rowIndex).getService();
            case 1:
                return listResultats.get(rowIndex).getMedecin();
            case 2:
                return listResultats.get(rowIndex).getDate();
            case 3:
                return listResultats.get(rowIndex).getContenu();
            default:
                return null; //Ne devrait jamais arriver
        }

    }

    /**
     * 
     * @return la liste de résultats
     */
    public ArrayList<Resultat> getListResultats() {
        return listResultats;
    }
}
