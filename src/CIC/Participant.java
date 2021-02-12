/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

/**
 *
 * @author clara
 */
public class Participant {
    private String nomU;
//    private String nomN;
    private String prenom;
    private String dateN;
    private String type;
    
    public Participant(String nomU,String prenom,String dateN, String type){
        this.nomU = nomU;
//        this.nomN = nomN;
        this.prenom = prenom;
        this.dateN = dateN;
        this.type = type;
    }
    
    public String getNomU() {
        return nomU;
    }

//    public String getNomN() {
//        return nomN;
//    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateN() {
        return dateN;
    }

    public String getType() {
        return type;
    }
    
    public boolean egal(Participant p){
        return (nomU.equals(p.getNomU()) && prenom.equals(p.getPrenom()) && dateN.equals(p.getDateN()) && type.equals(p.getType()));
    }
}
