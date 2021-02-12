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
public class TableauConsultations extends AbstractTableModel {
    private ArrayList<Consultations> listCons= new ArrayList<Consultations>();
     
    private final String[] entetes = {"Service ", "Medecin", "Date ","motif"};
    
    public TableauConsultations(Patient p) {
        String date;
        String contenu;
        String login=null;
        String nomM;
        String nomS;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs1= s.executeQuery("SELECT date,motif,login FROM Acte WHERE idP ='"+ p.getId()+"'AND type='Consultation'" );
                while(rs1.next()){
                    date= rs1.getString("date");
                    contenu= rs1.getString("motif");
                    login =rs1.getString("login");
                    ResultSet rs2= s.executeQuery("SELECT nom,nomS FROM Médecin WHERE login ='"+login+"'" );
                        while(rs2.next()){
                            nomM= rs2.getString("nom");
                            nomS= rs2.getString("nomS");
                            Consultations pres=new Consultations(nomS,nomM,date,contenu);
                            System.out.println("ajout"+ contenu);
                            listCons.add(pres);
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
        return listCons.size();
        
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
                System.out.println("cas 0");
                return listCons.get(rowIndex).getService();
            case 1:
                System.out.println("cas 1");
                return listCons.get(rowIndex).getNomMedecin();
            case 2: 
                System.out.println("cas 2");
                return listCons.get(rowIndex).getDate();
            case 3: 
                System.out.println("cas 3");
                return listCons.get(rowIndex).getMotif();
            default:
                return null; //Ne devrait jamais arriver
        }

    }
    
    public ArrayList<Consultations> getListConsultations(){
        return listCons;
    }
}
