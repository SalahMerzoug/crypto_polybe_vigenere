/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheffreent;

import java.io.*;

public class Vigenere {
    private String Message;
    private String messagecrypt;
    private String clef;

    /* Divise le texte en autant de lignes que longueur
       et sauvegarde le resultat */
    public Vigenere(String message, String cle) {
        int longueur = cle.length();
        clef = cle.toLowerCase();
        //Cette fonction permet d'uniformiser le message a coder afin qu'il soit cryptable par la methode de Vigenere :
      
    }

    public Vigenere(String clef) {
        this.clef = clef;
    }

    public String uniformisation(String message) {
        //Cette fonction permet d'uniformiser le message a coder afin qu'il soit cryptable par la methode de Vigenere :
        message = message.replace('ç', 'c');
        message = message.replace('é', 'e');
        message = message.replace('è', 'e');
        message = message.replace('ê', 'e');
        message = message.replace('à', 'a');
        message = message.replace('â', 'a');
        message = message.replace('ù', 'u');
        return message;
    }



    /* chiffre le texte en utilisant la clef et sauvegarde
       le resultat */
    public void chiffre(String message) {
        Message = uniformisation(message);
        messagecrypt = "";
        for (int i = 0; i < Message.length(); i++) {
            char newligne = ' ';
            char v = Message.charAt(i);
            char c = clef.charAt(i % clef.length());
                int newchar = (get_index(v) + get_index(c)) % 128;
                newligne = get_char(newchar);
            messagecrypt += newligne;

        }
    }

    /* Dechiffre le texte en utilisant la clef et sauvegarde
       le resultat */
    public void dechiffre(String message) {

        messagecrypt = uniformisation(message);
        Message = "";
        for (int i = 0; i < messagecrypt.length(); i++) {
            char newligne = ' ';
            char v = messagecrypt.charAt(i);
            char c = clef.charAt(i% clef.length());
                int newchar = get_index(v) - get_index(c);
                if (newchar < 0) {
                    newchar += 128;
                }
                newligne = get_char(newchar % 128);
                Message += newligne;

        }
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getMessagecrypt() {
        return messagecrypt;
    }

    public void setMessagecrypt(String messagecrypt) {
        this.messagecrypt = messagecrypt;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public int get_index(char a) {
      return (int)a;
    }

    public char get_char(int a) {
     return (char)a;
    }
}
