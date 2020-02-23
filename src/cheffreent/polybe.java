/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheffreent;

import java.awt.Event;

/**
 *
 * @author 5470
 */
public class polybe {

    private char[][] carre;
    private String clef;
    private String messageCrypte;
    private String Message;

    public polybe(String clef) {
        this.clef = clef;
        carre = init_c();
    }

    public polybe() {
        carre = init();
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

    public void chiffre(String message) {
        //init des variables :
        Message = uniformisation(message);
        messageCrypte = "";
        //cryptage du message :
        for (int i = 0; i < Message.length(); i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 12; k++) {
                    if ((Message.charAt(i)) == (carre[j][k])) {
                        messageCrypte += (j);
                        messageCrypte += " " + k;
                        messageCrypte += "  ";
                    }
                }
            }
        }
    }

    public void dechiffre(String message) {
        //init des variables :
        messageCrypte = message;
        Message = "";
        //cryptage du message :
        String[] sp = messageCrypte.split("  ");
        if (sp.length > 0) {
            for (int i = 0; i < sp.length; i++) {
                String[] cheffer = sp[i].split(" ");
                if (cheffer.length >= 2) {
                    int k = Integer.parseInt(cheffer[0]);
                    int j = Integer.parseInt(cheffer[1]);
                    if (k < 12 && j < 12) {
                        Message += carre[k][j];
                    }
                }
            }
        }
    }

    public char[][] init() {
        char[][] tab = new char[12][12];
        int k = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                tab[i][j] = (char) k;
                System.out.print(tab[i][j] + "\t");
                k++;
                if (k == 127) {
                    break;
                }
            }
            System.out.println();
            if (k == 127) {
                break;
            }
        }
        return tab;
    }

    public char[][] init_c() {
        char[][] tab = new char[12][12];
        int k = 0;
        int i = 0;
        int j = 0;
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 12; j++) {
                if (k >= clef.length()) {
                    break;
                }
                tab[i][j] = clef.charAt(k);
                System.out.println(tab[i][j]);
                k++;
            }
            if (k >= clef.length()) {
                break;
            }
        }
        k = 0;
        j++;
        while (i < 12) {
            while (j < 12) {
                while (test((char) k, tab, i)) {
                    k++;
                }
                tab[i][j] = (char) k;
                if (k == 127) {
                    break;
                }
                k++;
                j++;
            }
            i++;
            if (k == 127) {
                break;
            }
            j = 0;
        }
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 12; j++) {
                System.out.print(tab[i][j] + "\t");
            }
            System.out.println();
        }
        return tab;
    }

    public boolean test(char v, char[][] tab, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 12; j++) {
                if (tab[i][j] == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public String getMessageCrypte() {
        return messageCrypte;
    }

    public void setMessageCrypte(String messageCrypte) {
        this.messageCrypte = messageCrypte;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
