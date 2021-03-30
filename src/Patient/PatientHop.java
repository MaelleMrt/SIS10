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
 * Définit un patient
 * @author Maelle
 */
public class PatientHop {

    /**
     * nom usuel du patient
     */
    private String nom;
    /**
     * prénom du patient
     */
    private String prenom;
    /**
     * date de naissance du patient
     */
    private String naissance;
    /**
     * identifiant du patient
     */
    private int id;
    /**
     * nom de naissance du patient
     */
    private String nomN;



    /**
     * Constructeur PatientHop 
     * initialise les attributs, trouve l'identifiant du patient en interrogeant la base de données
     * @param nom nom usuel du patient
     * @param prenom prénom du patient
     * @param dateDeNaissance date de naissance du patient
     */
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
                }    

            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);
        }      
    }
   
    /**
     * 2e constructeur PatientHop
     * initialise les attributs
     * @param nom nom usuel du patient
     * @param nomN nom de naissance du patient
     * @param prenom prénom du patient
     * @param naissance date de naissance du patient
     * @param id identifiant du patient
     */
    public PatientHop(String nom, String nomN, String prenom, String naissance, int id){
        this.nom = nom;
        this.nomN = nomN;
        this.prenom = prenom;
        this.naissance = naissance;
        this.id = id;
    }
    
    /**
     * 3e constructeur PatientHop
     * initialise les attributs en interrogeant la base de données
     * @param id l"identifiant du patient
     */
    public PatientHop(int id){
        this.id = id;
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nomusuel, prenom,datedenaissance FROM Patient WHERE id='"+this.id+"'" );
                while(rs.next()){
                    this.nom=rs.getString("nomusuel");
                    this.prenom=rs.getString("prenom");
                    this.naissance=rs.getString("datedenaissance");
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
     * @return l'identifiant du patient
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return le nom usuel du patient
     */
    public String getNomUsuel() {
        return nom;
    }

    /**
     * 
     * @return le prénom du patient
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * 
     * @return la date de naissance du patient
     */
    public String getNaissance() {
        return naissance;
    }
   
    /**
     * Interroge la base de données pour trouver le nom de naissance du patient
     * @return le nom de naissance du patient
     */
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
    
    /**
     * interroge la base de données pour trouver le sexe du patient
     * @return le sexe du patient
     */
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
   
    /**
     * interroge la base de données pour trouver l'adresse du patient
     * @return l'adresse du patient
     */
    public String getAdresse() {
        String adresse="";
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT rue,ville,codepostale FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   adresse+="rue "+rs.getString("rue")+"  ";
                   adresse+=rs.getString("codepostale")+"  ";
                   adresse+="\n"+rs.getString("ville")+"  ";
                }    

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
        return adresse;
    }
    
    /**
     * interroge la base de données pour trouver la ville du patient
     * @return la ville du patient
     */
    public String getVille(){
        String ville = "";
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT ville FROM Patient WHERE id='"+id+"'" );
                while(rs.next()){
                   ville=rs.getString("ville");
                }    
            } catch(SQLException e){
                    System.out.println(e);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return ville;
    }
    
    /**
     * interroge la base de données pour trouver le n° de sécurité sociale du patient
     * @return le n° de sécurité sociale du patient
     */
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
    
    /**
     * interroge la base de données pour trouver le nom du médecin traitant du patient
     * @return le médecin traitant du patient
     */
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
   
    /**
     * interroge la base de données pour trouver la nationalité du patient
     * @return la nationalité du patient
     */
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
    
    /**
     * interroge la base de données pour trouver le lieu de naissance du patient
     * @return le lieu de naissance du patient
     */
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
   
    /**
     * 
     * @return le prénom suivi du nom du patient
     */
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    
    /**
     * Fixe la valeur de l'identifiant à id
     * @param id identifiant
     */
    public void setID(int id){
     this.id=id;
    }
 
    /**
     * vérifie si 2 patients sont les mêmes
     * @param o un Object correspondant à un patient 
     * @return true si le nom et le prénom des 2 patients sont les mêmes, false sinon
     */
    public boolean equals(Object o) {
        if (o instanceof PatientHop) {
            PatientHop p = (PatientHop) o;
            return nom.equals(p.getNomUsuel()) && prenom.equals(p.getPrenom());
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @return le prénom du patient suivi de son nom usuel et de son nom de naissance entre parenthèses
     */
    public String toString2(){
        return prenom + " " + nom + " (" + nomN + ")";
    }

}