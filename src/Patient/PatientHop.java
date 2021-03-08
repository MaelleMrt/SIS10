/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import Connexion.ExempleJdbc;
import Medecin.Prescription;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kalma
 */
public class PatientHop {

    private String nom;
    private String prenom;
    private String naissance;
    private int id;



    //, Date naissance, Integer numSecu
    public PatientHop(String nom, String prenom, String dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateDeNaissance;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT id FROM Patient WHERE nomusuel='"+this.nom+"' AND prenom='"+this.prenom+"'AND datedenaissance='"+this.naissance+"'" );
                while(rs.next()){
                    id=rs.getInt("id");
                    System.out.println("id    "+id);
                }    

            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);

        }
        
    }

    public int getId() {
        return id;
    }

   

    public String getNomUsuel() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNaissance() {
        return naissance;
    }
   
    public String getNomNaissance() {
        String nomNaissance=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nomdenaissance FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   nomNaissance=rs.getString("nomdenaissance");
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return nomNaissance;
    }
    
    public String getSexe() {
        String sexe=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT sexe FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   sexe=rs.getString("sexe");
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return sexe;
    }
   
    public String getAdresse() {
        String adresse="";
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT rue,ville,codepostale FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   adresse+="rue "+rs.getString("rue")+"  ";
                   adresse+=rs.getString("codepostale")+"   ";
                   adresse+="\n"+rs.getString("ville")+"   ";
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return adresse;
    }
    
    public String getSecu() {
        String secu=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT secu FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   secu=rs.getString("secu");
                 
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return secu;
    }
    
    public String getMedecinTraitant() {
        String medT=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT médecintraitant FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   medT=rs.getString("médecintraitant");
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return medT;
    }
   
    public String getNationalite() {
        String nat=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nationalité FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   nat=rs.getString("nationalité");
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return nat;
    }
    
    public String getLieuNaissance() {
        String lieuN=null;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT lieudenaissance FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   lieuN=rs.getString("lieudenaissance");
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return lieuN;
    }
   
   
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    
 

    public boolean equals(Object o) {
        if (o instanceof PatientHop) {
            PatientHop p = (PatientHop) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

}