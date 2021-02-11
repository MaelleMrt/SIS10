/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medecin;

/**
 *
 * @author Maelle
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TableauPatient tp=new TableauPatient();
        new MedecinAcceuil(tp.getListPatient());
    }
    
}
