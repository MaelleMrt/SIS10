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
 *
 * @author Maelle
 */
public class TableauxSoins  extends AbstractTableModel{
     private ArrayList<Soins> listSoins= new ArrayList<Soins>();
     
    private final String[] entetes = {"Poids ", "Température", "FC","PA","Saturation","Glycemie","Observation","date"};
    
    public TableauxSoins(int idp) {
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet r1= s.executeQuery("SELECT poids, temp, fc, pa, saturation, glycemie, observation,date FROM Soin where idP='"+idp+"'" );
                while(r1.next()){
                    int poids= r1.getInt("poids");
                    int temp= r1.getInt("temp");
                    int fc =r1.getInt("fc");
                    int pa =r1.getInt("pa");
                    int sat =r1.getInt("saturation");
                    int gly =r1.getInt("glycemie");
                    String observation=r1.getString("observation");
                    String date =r1.getString("date");
                    listSoins.add(new Soins(poids,temp,pa,fc,sat,gly,observation,date));
                }
            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
     }
 
    
   
    
      public int getRowCount() {
        return listSoins.size();
        
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
            default:
                return null; //Ne devrait jamais arriver
        }

    }
    
    public ArrayList<Soins> getListSoins(){
        return listSoins;
    }
    
    
   
}
