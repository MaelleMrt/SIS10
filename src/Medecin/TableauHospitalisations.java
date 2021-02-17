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
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maelle
 */
public class TableauHospitalisations extends AbstractTableModel{
    private ArrayList<Hospitalisations> listHospi= new ArrayList<Hospitalisations>();
     
    private final String[] entetes = {"Service ", "Medecin", "Date ","motif"};
    
    public TableauHospitalisations(Patient p) {
        String date;
        String contenu;
        String login=null;
        String nomM;
        String nomS;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs1= s.executeQuery("SELECT date,motif,login FROM Acte WHERE idP ='"+ p.getId()+"'AND type='Hospitalisation'" );
                while(rs1.next()){
                    date= rs1.getString("date");
                    contenu= rs1.getString("motif");
                    login =rs1.getString("login");
                    ResultSet rs2= s.executeQuery("SELECT nom,nomS FROM Médecin WHERE login ='"+login+"'" );
                        while(rs2.next()){
                            nomM= rs2.getString("nom");
                            nomS= rs2.getString("nomS");
                            Hospitalisations pres=new Hospitalisations(nomS,nomM,date,contenu);
                            System.out.println("ajout"+ contenu);
                            listHospi.add(pres);
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
        return listHospi.size();
        
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

                return listHospi.get(rowIndex).getService();
            case 1:

                return listHospi.get(rowIndex).getNomMedecin();
            case 2: 

                return listHospi.get(rowIndex).getDate();
            case 3: 

                return listHospi.get(rowIndex).getMotif();
            default:
                return null; //Ne devrait jamais arriver
        }

    }
    
    public ArrayList<Hospitalisations> getListHospitalisations(){
        return listHospi;
    }
}
