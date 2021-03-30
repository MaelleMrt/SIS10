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
import javax.swing.table.AbstractTableModel;

/**
 * crée un modèle de tableau avec la liste des patients d'un médecin
 *
 * @author Maelle
 */
public class TableauPatient extends AbstractTableModel {

    /**
     * liste des patients du médecin
     *
     * @see PatientHop
     */
    private ArrayList<PatientHop> listPatient = new ArrayList<PatientHop>();

    /**
     * entête du tableau
     */
    private final String[] entetes = {"Nom ", "Prenom", "Date de Naissance"};

    /**
     * Constructeur TableauPatient
     * remplit la liste en interrogeant la base de données
     * @param login le login du médecin
     */
    public TableauPatient(String login) {
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT nomusuel, prenom, datedenaissance,id FROM Patient INNER JOIN Acte WHERE Acte.login ='" + login + "'AND Patient.id = Acte.idP");
                while (rs.next()) {
                    PatientHop patient = new PatientHop(rs.getString("nomusuel"), rs.getString("prenom"), rs.getString("datedenaissance"));
                    listPatient.add(patient);
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
     * @return le nombre de lignes du tableau, c'est-à-dire le nombre de patients
     */
    public int getRowCount() {
        return listPatient.size();

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
                return listPatient.get(rowIndex).getNomUsuel();
            case 1:
                return listPatient.get(rowIndex).getPrenom();
            case 2:
                return listPatient.get(rowIndex).getNaissance();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    /**
     * 
     * @return la liste des patients
     */
    public ArrayList<PatientHop> getListPatient() {
        return listPatient;
    }

}
