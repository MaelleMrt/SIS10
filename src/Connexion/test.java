/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;


import com.sun.org.apache.bcel.internal.generic.Select;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maelle
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Statement s= ExempleJdbc.connexion();
        ResultSet r= s.executeQuery("SELECT * FROM Patient");
          if (r.next()) {
           String  userName = r.getString(1) ;
           System.out.println(userName);
         }
        
    }
}
