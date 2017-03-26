package scrabble.game;

import java.util.Random;
import java.util.Scanner;

class Chevalet {
    private char lettres1;
    private String case1;
    private String case2;
    private String case3;
    private String case4;
    private String case5;
    private String case6;
    private String case7;
    private String caseFact1;
    private String caseFact2;
    private String caseFact3;
    private String caseFact4;
    private String caseFact5;
    private String caseFact6;
    private String caseFact7;
    private static String lettresAleatoires = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String lettreJoker = "Joker";
    private static String vide = "Vide";

    public char getLettres() {
        return this.lettres1;
    }

    public void setLettres(char pLettres) {
        this.lettres1 = pLettres;
    }

    //Get case
    public String getCase1() {
        return this.case1;
    }

    public String getCase2() {
        return this.case2;
    }

    public String getCase3() {
        return this.case3;
    }

    public String getCase4() {
        return this.case4;
    }

    public String getCase6() {
        return this.case6;
    }

    public String getCase7() {
        return this.case7;
    }

    public String getCase5() {
        return this.case5;
    }

    public String getCaseFact1() {
        return this.caseFact1;
    }

    public String getCaseFact2() {
        return this.caseFact2;
    }

    public String getCaseFact3() {
        return this.caseFact3;
    }

    public String getCaseFact4() {
        return this.caseFact4;
    }

    public String getCaseFact5() {
        return this.caseFact5;
    }

    public String getCaseFact6() {
        return this.caseFact6;
    }

    public String getCaseFact7() {
        return this.caseFact7;
    }

    //Voir toutes les lettres que l'on possède en début de partie.
    public void seeAllCase() {
        System.out.println("Vous posséder les lettres suivantes :" + getCase1()
                + getCase2()
                + getCase3()
                + getCase4()
                + getCase5()
                + getCase6()
                + getCase7());
    }

    //Relation case Factuelle et Case réelle (les cases factuelles sont utilisé pour les tests et deviennent des csaes réelles si tous marche correctement
    public void setAllCaseFact() {
        this.caseFact1 = this.case1;
        this.caseFact2 = this.case2;
        this.caseFact3 = this.case3;
        this.caseFact4 = this.case4;
        this.caseFact5 = this.case5;
        this.caseFact6 = this.case6;
        this.caseFact7 = this.case7;
    }

    public void confirmAllsetchange() {
        this.case1 = this.caseFact1;
        this.case2 = this.caseFact2;
        this.case3 = this.caseFact3;
        this.case4 = this.caseFact4;
        this.case5 = this.caseFact5;
        this.case6 = this.caseFact6;
        this.case7 = this.caseFact7;
    }

    //Set caseFact
    public void setCaseFact1(String caseFact1) {
        this.caseFact1 = caseFact1;
    }

    public void setCaseFact2(String caseFact2) {
        this.caseFact2 = caseFact2;
    }

    public void setCaseFact3(String caseFact3) {
        this.caseFact3 = caseFact3;
    }

    public void setCaseFact4(String caseFact4) {
        this.caseFact4 = caseFact4;
    }

    public void setCaseFact5(String caseFact5) {
        this.caseFact5 = caseFact5;
    }

    public void setCaseFact6(String caseFact6) {
        this.caseFact6 = caseFact6;
    }

    public void setCaseFact7(String caseFact7) {
        this.caseFact7 = caseFact7;
    }

    //Set case
    public void setCase1(String case1) {
        this.case1 = case1;
    }

    public void setCase2(String case2) {
        this.case2 = case2;
    }

    public void setCase3(String case3) {
        this.case3 = case3;
    }

    public void setCase4(String case4) {
        this.case4 = case4;
    }

    public void setCase5(String case5) {
        this.case5 = case5;
    }

    public void setCase6(String case6) {
        this.case6 = case6;
    }

    public void setCase7(String case7) {
        this.case7 = case7;
    }

    //Initialiser toutes les lettres du chevalet
    public void initaliseChevalet() {
        setCase1(generateLettre());
        setCase2(generateLettre());
        setCase3(generateLettre());
        setCase4(generateLettre());
        setCase4(generateLettre());
        setCase5(generateLettre());
        setCase6(generateLettre());
        setCase7(generateLettre());
    }

    //Changer les lettres du chevalet
    public void changeLettres(int pReplay) {
        int a = 0;
        while (a < pReplay) {
            a++;
            System.out.println("Choisissez la case à remplacer");
            Scanner choix = new Scanner(System.in);
            int choixCase = choix.nextInt();
            switch (choixCase) {
                case 1:
                    this.setCase1(generateLettre());
                    System.out.println(this.getCase1());
                    break;
                case 2:
                    this.setCase2(generateLettre());
                    System.out.println(this.getCase2());
                    break;
                case 3:
                    this.setCase3(generateLettre());
                    System.out.println(this.getCase3());
                    break;
                case 4:
                    this.setCase4(generateLettre());
                    System.out.println(this.getCase4());
                    break;
                case 5:
                    this.setCase5(generateLettre());
                    System.out.println(this.getCase5());
                    break;
                case 6:
                    this.setCase6(generateLettre());
                    System.out.println(this.getCase6());
                    break;
                case 7:
                    this.setCase7(generateLettre());
                    System.out.println(this.getCase7());
                    break;
                default:
                    System.out.println("Vous vous êtes trompés de case");
                    a--;
                    break;
            }
        }
    }//Everything is all Right !!!
    //Vérifier s'il y a les lettres demandés sont disponible dans le chevalet
    //Remplacer toutes les lettres du chevalet
    //Générer une lettre
    public String generateLettre() {
        //Erreur ! j'ai reçu une erreur avec Math.random.
        if (Lettre.nouvellesLettresDisponible()) {
            String lettreChoisi = "";
            int i;
            Random rand = new Random();
            do {
                i = rand.nextInt(27);
                if (i == 26) {
                    lettreChoisi = lettreJoker;
                } else {
                    lettreChoisi += lettresAleatoires.charAt(i);
                }
            } while (i <= 0 && i >= 26);
            return lettreChoisi;
        } else {
            System.out.println("Vous ne pouvez plus récuperer de nouvelle lettre");
            return vide;
        }
    }
}
