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
import javax.swing.table.AbstractTableModel;

/**
 * crée un modèle de tableau contenant la liste des soins d'un patient
 *
 * @author Maelle
 */
public class TableauxSoins extends AbstractTableModel {
    /**
     * liste des soins d'un patient
     */
    private ArrayList<Soins> listSoins = new ArrayList<Soins>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Poids ", "Température", "FC", "PA", "Saturation", "Glycemie", "Observation", "date", "infirmier"};

    /**
     * Constructeur TableauxSoins
     * initialise les attributs, remplit la liste des soins en interrogeant la base de données
     * @param idp l'identifiant du patient
     */
    public TableauxSoins(int idp) {
        try {
            Statement s = ExempleJdbc.connexion();

            try {

                ResultSet r1 = s.executeQuery("SELECT poids, temp, fc, pa, saturation, glycemie, observation,date,login FROM Soin where idP='" + idp + "'");
                while (r1.next()) {
                    int poids = r1.getInt("poids");
                    int temp = r1.getInt("temp");
                    int fc = r1.getInt("fc");
                    int pa = r1.getInt("pa");
                    int sat = r1.getInt("saturation");
                    int gly = r1.getInt("glycemie");
                    String observation = r1.getString("observation");
                    String date = r1.getString("date");
                    String login = r1.getString("login");
                    try {

                        ResultSet r2 = s.executeQuery("SELECT nom FROM Infirmier where login='" + login + "'");
                        while (r2.next()) {
                            String nomInf = r2.getString("nom");
                            listSoins.add(new Soins(poids, temp, pa, fc, sat, gly, observation, date, nomInf));
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
     * @return le nombre de lignes du tableau = le nombre de soins
     */
    public int getRowCount() {
        return listSoins.size();

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
                return listSoins.get(rowIndex).getPoids();
            case 1:
                return listSoins.get(rowIndex).getTemp();
            case 2:
                return listSoins.get(rowIndex).getPa();
            case 3:
                return listSoins.get(rowIndex).getFc();
            case 4:
                return listSoins.get(rowIndex).getSaturation();
            case 5:
                return listSoins.get(rowIndex).getGly();
            case 6:
                return listSoins.get(rowIndex).getObservation();
            case 7:
                return listSoins.get(rowIndex).getDate();
            case 8:
                return listSoins.get(rowIndex).getNomInf();
            default:
                return null; //Ne devrait jamais arriver
        }

    }

    /**
     * 
     * @return la liste des soins
     */
    public ArrayList<Soins> getListSoins() {
        return listSoins;
    }

}
