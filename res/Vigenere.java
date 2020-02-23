/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheffreent;

import java.io.*;

public class Vigenere {

    private String[] lignes;
    private String Message;
    private String messagecrypt;
    private String clef;
    char vigenereUn[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* Divise le texte en autant de lignes que longueur
       et sauvegarde le resultat */
    public Vigenere(String message, String cle) {
        int longueur = cle.length();
        clef = cle.toLowerCase();
        //Cette fonction permet d'uniformiser le message a coder afin qu'il soit cryptable par la methode de Vigenere :
        message = message.toLowerCase();
        message = message.replace('ç', 'c');
        message = message.replace('é', 'e');
        message = message.replace('è', 'e');
        message = message.replace('ê', 'e');
        message = message.replace('à', 'a');
        message = message.replace('â', 'a');
        message = message.replace('ù', 'u');
        message = message.replaceAll("£", "livre sterling");
        message = message.replaceAll("0", "zeros");
        message = message.replaceAll("1", "un");
        message = message.replaceAll("2", "deux");
        message = message.replaceAll("3", "trois");
        message = message.replaceAll("4", "quatre");
        message = message.replaceAll("5", "cinq");
        message = message.replaceAll("6", "six");
        message = message.replaceAll("7", "sept");
        message = message.replaceAll("8", "huit");
        message = message.replaceAll("9", "neuf");
        message = message.replaceAll("@", "at");
        message = message.replaceAll("&", "et");
        /*for(int i = 33; i<97;i++){message=message.replace(((char)i), ' ');}
        for(int i = 123;i<127;i++){message=message.replace(((char)i), ' ');}
        message=message.replaceAll(" ","");*/
        Message = message;
        lignes = new String[longueur];
        for (int i = 0; i < longueur; i++) {
            lignes[i] = "";
        }

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            // Pour eviter de prendre en compte les espaces
            if ('a' <= c && c <= 'z') {
                lignes[i % longueur] += c;
            }
        }
    }

    public Vigenere(String clef) {
        this.clef = clef;
    }
    
      public  String uniformisation(String message)
    {  
        //Cette fonction permet d'uniformiser le message a coder afin qu'il soit cryptable par la methode de Vigenere :
        message=message.toLowerCase();
        message=message.replace('ç','c');
        message=message.replace('é','e');
        message=message.replace('è','e');
        message=message.replace('ê','e');
        message=message.replace('à','a');
        message=message.replace('â','a');
        message=message.replace('ù','u');
        message=message.replaceAll("£", "livre sterling");
        message=message.replaceAll("0", "zeros");
        message=message.replaceAll("1", "un");
        message=message.replaceAll("2", "deux");
        message=message.replaceAll("3", "trois");
        message=message.replaceAll("4", "quatre");
        message=message.replaceAll("5", "cinq");
        message=message.replaceAll("6", "six");
        message=message.replaceAll("7", "sept");
        message=message.replaceAll("8", "huit");
        message=message.replaceAll("9", "neuf");
        message=message.replaceAll("@", "at");
        message=message.replaceAll("&", "et");
      /*  for(int i = 33; i<97;i++){message=message.replace(((char)i), ' ');}
        for(int i = 123;i<127;i++){message=message.replace(((char)i), ' ');}
        message=message.replaceAll(" ","");*/
         
         
        return message;
    }
    /* Donne un tableau comptant le nombre d'occurrences de
       chaque lettre dans ligne */
    private static int[] compte(String ligne) {
        int[] occ = new int[26];

        for (int i = 0; i < ligne.length(); i++) {
            occ[ligne.charAt(i) - (int) 'a']++;
        }

        return occ;
    }

    /* Donne les indices de coincidence reels du texte */
    public float[] coincidence() {
        float[] ic = new float[lignes.length];

        for (int i = 0; i < ic.length; i++) {
            int[] occ = compte(lignes[i]);
            float sum = 0;
            for (int j = 0; j < 26; j++) {
                sum += occ[j] * (occ[j] - 1);
            }
            ic[i] = sum / (lignes[i].length() * (lignes[i].length() - 1));
        }
        return ic;
    }

    /* chiffre le texte en utilisant la clef et sauvegarde
       le resultat */
  
    public void chiffre(String message) {
        Message=uniformisation(message);
        int j=0;
        messagecrypt = "";
        for (int i = 0; i < Message.length(); i++) {
            char newligne = ' ';
            char v=Message.charAt(i);
            if(v!=' ')
            {
                char c=clef.charAt(j% clef.length());
                int newchar =(get_index(v)+get_index(c))% 26;
                newligne =  get_char(newchar) ;
                j++;
            }
           messagecrypt += newligne ;
          

        }
    }
      /* Dechiffre le texte en utilisant la clef et sauvegarde
       le resultat */
    
     public void dechiffre(String message) {
         
        messagecrypt = uniformisation(message);
        Message = "";
        int j=0;
        for (int i = 0; i < messagecrypt.length(); i++) {
            char newligne = ' ';
            char v=messagecrypt.charAt(i);
            if(v!=' ')
            {
                char c=clef.charAt(j % clef.length());
                int newchar=get_index(v)-get_index(c) ;
                if(newchar<0)
                    newchar+=26;
                newligne =  get_char(newchar % 26) ;
                j++;
            }
           Message += newligne ;
           
        }
    }


  
    /* Prend une chaine de caracteres arbitraire et donne une chaine
    contenant chaque caractere une seule fois, dans l'ordre de
    frequence decroissant */
    public static String ordonne(String ligne) {
        int[] occ = compte(ligne);
        String ord = "";

        /* Un algorithme de tri TRES naif */
        for (int i = 0; i < 26; i++) {
            /* Recherche du maximum */
            int max = 0, pos = -1;
            for (int j = 0; j < 26; j++) {
                if (occ[j] > max) {
                    max = occ[j];
                    pos = j;
                }
            }
            /* Si le maximum est superieur a 0,
	       on le met dans la sortie et on
	       l'efface du tableau */
            if (max > 0) {
                occ[pos] = 0;
                ord += (char) ('a' + pos);
            } /* Sinon on a termine */ else {
                break;
            }
        }
        return ord;
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
    public  int get_index(char a)
    {
      for( int i=0; i<vigenereUn.length;i++)
      {
          if(vigenereUn[i]==a)
              return  i;
      }
        return -1;  
    }
    public  char get_char(int a)
    {
      for( int i=0; i<vigenereUn.length;i++)
      {
          if(i==a)
              return  vigenereUn[i];
      }
        System.out.println(a);
        return '-';  
    }
}
