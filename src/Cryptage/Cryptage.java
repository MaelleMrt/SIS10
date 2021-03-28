/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cryptage;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


/**
 *
 * /**
 *
 * @author Maelle
 */
public class Cryptage {
 
     
    private static final int TAILLE_ALPHABET = 26;
    private String texte;
 
    public Cryptage(String texte) {
        this.texte = texte;
    }
 
    public static String chiffre(int decalage,String texte) {
        StringBuilder sb = new StringBuilder(texte.length());
        for (char c : texte.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(decaleVar(c, decalage, 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append(decaleVar(c, decalage, 'A'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
 
    public static String dechiffre(int decalage,String texte) {
        return chiffre(-decalage,texte);
    }
 
    private static char decaleVar(char caractere, int decalage, char caractereBase) {
        int base = caractereBase;
        if (decalage < 0) {
            base += TAILLE_ALPHABET - 1;
        }
        return (char) (((caractere) - base + decalage) % TAILLE_ALPHABET + base);
    }
// 
//    public static void main(String[] args) {
//        System.out.println(Cryptage.chiffre(6,"hello world"));
//        System.out.println(Cryptage.dechiffre(6,Cryptage.chiffre(6,"hello world")));
//       
//    }
}

