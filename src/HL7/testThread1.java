/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

/**
 *
 * @author Maelle
 */
public class testThread1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestThread t = new TestThread("A");
        TestThread t2 = new TestThread("  B");
        t.start();
        t2.start();
    }
    
}
