/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

import Médecinanesthésiste.Rdv;
import Connexion.ExempleJdbc;
import Secretaire.RDV;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amira
 */
class TableauRdvMedecin extends AbstractTableModel {
     private ArrayList<RdvMedecin> listRdv= new ArrayList<RdvMedecin>();

     private Medecin medecin;
     
    private final String[] entetes = {"Motif ", "Date", "Heure","Nom","Prenom","Date de Naissance",};
    
    public TableauRdvMedecin(Medecin m) {
 
        medecin=m;
        String motif;
        String date;
        String heure;
        String nomPrenomPat;
        String nomPrenomMed= this.medecin.getPrenom()+" "+this.medecin.getNom();
        int idPat;
        String prenom=null;
        String nom=null;
        String dateN=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rdv= s.executeQuery("SELECT Motif, Date, Heure, Patient, idPatient FROM RendezVous WHERE Médecin ='"+nomPrenomMed+"'" );
                while(rdv.next()){
                    idPat=rdv.getInt("idPatient");
                    date= rdv.getString("Date");
                    motif= rdv.getString("Motif");
                    System.out.println(motif);
                    heure =rdv.getString("Heure");
                    try{
                        ResultSet id= s.executeQuery("SELECT nomusuel, prenom, datedenaissance from Patient WHERE id ='"+idPat+"'");
                        while(id.next()){
                            prenom=id.getString("prenom");
                            nom =id.getString("nomusuel");
                            dateN =id.getString("datedenaissance");
                            RdvMedecin rd = new RdvMedecin(motif,date,heure,nom,prenom,dateN);
                            System.out.println(motif+date+heure+nom+prenom+dateN);
                            listRdv.add(rd);
                            System.out.println("ajout rdv");
                        }
                    } catch(SQLException e){
                    System.out.println(e);
                    }

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
            case 3:
                return listRdv.get(rowIndex).getNom();
            case 4:
                return listRdv.get(rowIndex).getPrenom();
            case 5:
                return listRdv.get(rowIndex).getDataNaissance();
           
            default:
                return null; //Ne devrait jamais arriver
        }
    }
     

    
    
    public ArrayList<RdvMedecin> getListRdv(){
        return listRdv;
    }
    
    
}
