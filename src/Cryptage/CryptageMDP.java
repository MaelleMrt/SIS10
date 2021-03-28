/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cryptage;

import Connexion.ExempleJdbc;
import Patient.PatientHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author Maelle
 */
public class CryptageMDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> listMDP=new ArrayList<String>();
        ArrayList<String> listLogin=new ArrayList<String>();
         try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet r1= s.executeQuery("SELECT mdp,login FROM Utilisateur" );
                 while(r1.next()){
                    String mdp=r1.getString("mdp");
                    String login=r1.getString("login");
                    listMDP.add(mdp);
                    listLogin.add(login);
                }   
                int i=0;
                for(String mdp:listMDP){
                    String newMdp=Cryptage.dechiffre(5, mdp);
                    System.out.println("login = "+listLogin.get(i));
                    i++;
                    System.out.println("dechiffre= "+newMdp);
                }
              
                 

            } catch(SQLException e){
                    System.out.println(e);

            }

        } catch (SQLException e){
            System.out.println(e);

        }
         System.out.println("---------------------");
         System.out.println("dechiffrage "+Cryptage.dechiffre(5,"lnqgjwy_ojfs"));
    }
    
    
 
    
}

