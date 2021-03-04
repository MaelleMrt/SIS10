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
 *
 * @author Maelle
 */
public class TableauResultatInf extends AbstractTableModel{
     private ArrayList<Prescription> listPrescription= new ArrayList<Prescription>();
     
    private final String[] entetes = {"Service ", "Medecin", "Date","Contenu"};
    public String service;
    
    public TableauResultatInf(PatientHop p,String nomS) {
        service=nomS;
        String date;
        String contenu;
        String login=null;
        String nomM;

        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs1= s.executeQuery("SELECT date,contenu,login FROM CR WHERE idP ='"+ p.getId()+"'" );
                while(rs1.next()){
                    date= rs1.getString("date");
                    contenu= rs1.getString("contenu");
                    System.out.println(contenu);
                    login =rs1.getString("login");
                    ResultSet rs2= s.executeQuery("SELECT nom FROM Médecin WHERE login ='"+login+"'AND nomS='"+service+"'"  );
                        while(rs2.next()){
                            nomM= rs2.getString("nom");

                            Prescription pres=new Prescription(service,nomM,date,contenu);
                            listPrescription.add(pres);
                        }   
                
                }
                
            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);
        }
     }
 
    
   
    
      public int getRowCount() {
        return listPrescription.size();
        
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
    
    public ArrayList<Prescription> getListPrescription(){
        return listPrescription;
    }
}
