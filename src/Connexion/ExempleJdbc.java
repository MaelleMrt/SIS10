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
public class ExempleJdbc {
    
 public static Statement connexion() throws SQLException {
     Statement st=null;
     try{
        Class.forName("org.mariadb.jdbc.Driver");
        String urlDB = "jdbc:mysql://mysql-projetsis.alwaysdata.net:3306/projetsis_a"; // ouverture d’une nouvelle connexion à la BD 
        Connection conn= DriverManager.getConnection( urlDB, "projetsis_elo", "Caramel95$"); 
        st = conn.createStatement();
        
        
     }catch (SQLException e) {
         System.out.println(e);
     } catch (ClassNotFoundException ex) {
         System.out.println("classe non trouvé");
     }
    

    
    return st;
    }
    
}


