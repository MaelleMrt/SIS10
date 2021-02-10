/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maelle
 */
public class ExempleJdbc {
    
 public void connexion() throws SQLException{
     try{
        Class.forName("com.mysql.jdbc.Driver");
        String urlDB = "jdbc:mysql://mysql-projetsis.alwaysdata.net:3306/projetsis_a"; // ouverture d’une nouvelle connexion à la BD 
        Connection conn= DriverManager.getConnection( urlDB, "projetsis", "Alwaysdatas2020!"); 
        Statement st = conn.createStatement();
        st.executeUpdate("CREATE TABLE personne( nom VARCHAR(30),prenom VARCHAR(30), age INTEGER)");  st.close(); 
        int nb = st.executeUpdate("INSERT INTO personne(nom,prenom) VALUES (‘Pierre’, ‘Martin’)");  
        System.out.println("Nombre de lignes insérées = " + nb); 
        st.close(); 
        ResultSet r = st.executeQuery("Select * from personne");
        System.out.println(r.getInt(1));
        System.out.println(r.getInt(2));
        
     }catch(Exception e) {
         
     }
    

    
    
    }
}


