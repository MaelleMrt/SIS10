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
 * crée un modèle de tableau contenant la liste des patients d'un service donné
 * @author Maelle
 */
public class TableauPatientInf  extends AbstractTableModel{
    /**
     * liste de patients à mettre dans le tableau
     * @see PatientHop
     */
     private ArrayList<PatientHop> listPatient= new ArrayList<PatientHop>();
     /**
      * l'entête du tableau
      */
    private final String[] entetes = {"Nom ", "Prenom", "Date de Naissance"};
    
    /**
     * Constructeur TableauPatientInf
     *
     * @param nomS le nom du service
     * 
     */
    public TableauPatientInf(String nomS) {
        ArrayList<String> listLoginMedecin= new ArrayList<String>();
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet r1= s.executeQuery("SELECT login FROM Médecin where nomS='"+nomS+"'" );
                 while(r1.next()){
                    String login=r1.getString("login");
                    listLoginMedecin.add(login);
                }   
                for(String log:listLoginMedecin){
                    ResultSet r2= s.executeQuery("SELECT nomusuel, prenom, datedenaissance,id FROM Patient INNER JOIN Acte WHERE Acte.login ='"+log+"'AND Patient.id = Acte.idP" );
                    while(r2.next()){
                    PatientHop patient =new PatientHop(r2.getString("nomusuel"), r2.getString("prenom"), r2.getString("datedenaissance"));
                    listPatient.add(patient);
                    } 
                }
              
                 

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
     }
 
    
   
    /**
     * 
     * @return le nombre de lignes dans le tableau = le nombre de patients
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
     * @return le nom de la colonne n° columnIndex
     */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    /**
     * 
     * @param rowIndex l'indice de la ligne
     * @param columnIndex l'indice de la colonne
     * @return la valeur dans la case de la ligne n° rowIndex et de la colonne n° columnIndex
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
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
    public ArrayList<PatientHop> getListPatient(){
        return listPatient;
    }
    
    
   
}
