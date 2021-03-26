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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maelle
 */
// connexion à la base de donnees
public class ExempleJdbc {
 /**
  * Connexion a la base de donnees
  * 
  * @return Statement utilise pour faire
  * les requetes SQL
  * @throws SQLException 
  */
 public static Statement connexion() throws SQLException {
     Statement st=null;
     try{
         // on essaye de se connecter a l'aide d'un identifiant et un mdp
        Class.forName("org.mariadb.jdbc.Driver");
        String urlDB = "jdbc:mysql://mysql-projetsis.alwaysdata.net:3306/projetsis_a"; // ouverture d’une nouvelle connexion à la BD 
        Connection conn= DriverManager.getConnection( urlDB, "projetsis_mams", "Alwaysdatas2020!"); 
        st = conn.createStatement();
        
        
     }catch (SQLException e) {
         System.out.println(e);
     } catch (ClassNotFoundException ex) {
         System.out.println("classe non trouvé");
     }
    

    
    return st;
    }
    
}


