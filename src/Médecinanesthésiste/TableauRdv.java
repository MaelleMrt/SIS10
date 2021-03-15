/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Médecinanesthésiste;

import Connexion.ExempleJdbc;
import Medecin.Medecin;
import Patient.PatientHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amira
 */
class TableauRdv extends AbstractTableModel {
     private ArrayList<Rdv> listRdv= new ArrayList<Rdv>();
     private PatientHop patient;
     private Medecin medecin;
     
    private final String[] entetes = {"Motif ", "Date", "Heure"};
    
    public TableauRdv(PatientHop p, Medecin m) {
        patient=p;
        medecin=m;
        String motif;
        String date;
        String heure;
       
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rdv= s.executeQuery("SELECT Motif, Date, Heure  FROM RendezVous WHERE idPatient ='"+ patient.getId()+"'" );
                while(rdv.next()){
                    date= rdv.getString("Date");
                    motif= rdv.getString("Motif");
                    System.out.println(motif);
                    heure =rdv.getString("Heure");
                    Rdv rd = new Rdv(date,motif,heure);
                    listRdv.add(rd);
                   
                
                }
                
            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);
        }
     }

    @Override
    public int getRowCount() {
        return listRdv.size();
    }

    @Override
    public int getColumnCount() {
         return entetes.length;
    }
     public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
            case 0:
                return listRdv.get(rowIndex).getMotif();
            case 1:
                return listRdv.get(rowIndex).getDate();
            case 2: 
                return listRdv.get(rowIndex).getHeure();
           
            default:
                return null; //Ne devrait jamais arriver
        }
    }
     

    
    
    public ArrayList<Rdv> getListRdv(){
        return listRdv;
    }
    
    
}
