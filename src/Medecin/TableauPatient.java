/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import Connexion.ExempleJdbc;
import Patient.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Maelle
 */
public class TableauPatient  extends AbstractTableModel{
     private ArrayList<Patient> listPatient= new ArrayList<Patient>();
     
    private final String[] entetes = {"Nom ", "Prenom", "Date de Naissance"};
    
    public TableauPatient(String login) {
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nomusuel, prenom, datedenaissance,id FROM Patient INNER JOIN Acte WHERE Acte.login ='"+ login+"'AND Patient.id = Acte.idP" );
                while(rs.next()){
                    Patient patient =new Patient(rs.getString("nomusuel"), rs.getString("prenom"), rs.getString("datedenaissance"));
                    listPatient.add(patient);
                }   

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
     }
 
    
   
    
      public int getRowCount() {
        return listPatient.size();
        
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
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
    
    public ArrayList<Patient> getListPatient(){
        return listPatient;
    }
    
    
   
}
