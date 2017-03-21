package scrabble.game;

import java.util.*;

public class Joueur extends Chevalet {

    //Variables
    private String namePlayer;
    private String mot;
    private int scorePlayer;
    private int positionX;
    private int positionY;
    private boolean positionXValide;
    private boolean positionYValide;
    private char orientationMot;

    //Accesseurs
    public char getOrientationMot() {
        return this.orientationMot;
    }
    public String getNamePlayer() {
        return this.namePlayer;
    }
    public String getMot() {
        return this.mot;
    }
    public int getPositionX() {
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }
    public int getScorePlayer() {
        return this.scorePlayer;
    }
    public boolean getpositionXValide() {
        return this.positionXValide;
    }
    public boolean getpositionYValide() {
        return this.positionYValide;
    }

    //Mutateurs
    public void setOrientationMot(char orientationMot) {
        this.orientationMot = orientationMot;
    }
    public void setPositionXValide(boolean positionXValide) {
        this.positionXValide = positionXValide;
    }
    public void setPositionYValide(boolean positionYValide) {
        this.positionYValide = positionYValide;
    }
    public void setNamePlayer(String pNamePlayer) {
        this.namePlayer = pNamePlayer;
    }
    public void setMot(String pMot) {
        this.mot = pMot;
    }
    public void setPositionX(int pPositionX) {
        this.positionX = pPositionX;
    }
    public void setPositionY(int pPositionY) {
        this.positionY = pPositionY;
    }
    public void setScorePlayer(int pScorePlayer) {
        this.scorePlayer = pScorePlayer;
    }
    public void setPlacement(String pPlacement) {
        String placement = pPlacement;
    }

    //Constructeur Joueur
    public Joueur(String pNamePlayer) {
        this.namePlayer = pNamePlayer;
    }

    //Méthodes
    public boolean pLacerDesLettres() {
        //Etape placement
        setPositionXValide(false);
        setPositionYValide(false);
        //Demander le mot, la position X et Y de ce mot au préalable
        System.out.println("Quelle mot voulez vous placer ?");
        Scanner iniParametre = new Scanner(System.in);
        setMot(iniParametre.nextLine());
        do {
            System.out.println("Quelle serait la position X de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionX(iniParametre.nextInt() - 1);
            if (getPositionX() <= 14 && getPositionX() > 0)
                setPositionXValide(true);
        } while (!getpositionXValide());

        do {
            System.out.println("Quelle serait la positoin Y de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionY(iniParametre.nextInt() - 1);
            if (getPositionY() <= 14 && getPositionY() > 0)
                setPositionYValide(true);
        } while (!getpositionYValide());
        // Initialise le tableau
        Plateau.setInitialiseTableau();
        //Placer les lettres dans le tableaux factices.
        //Double fonction (en fonction de l'horientation du mot
        boolean verifOrientation = false;
        String orientation;
        do {
            System.out.println("Quelle est l'orientation du mot ?" +
                    "Attention, l'horientation ne peut être qu'horizontal ou vertical");
            orientation = iniParametre.nextLine();
            if (orientation == "horizontal" || orientation == "HORIZONTAL") {
                Plateau.formationMotPrincipalH(getMot(), getPositionX(), getPositionY());
                setOrientationMot('h');
            } else if (orientation == "vertical" || orientation == "VERTICAL") {
                Plateau.formationMotPrincialV(getMot(), getPositionX(), getPositionY());
                setOrientationMot('v');
            }
        } while (!verifOrientation);
        //Etape verification
        //Verifier si le mot indiqué touche bien une case contenant une lettre ou le milieu du tableau
        if (!touch(getMot(), getPositionX(), getPositionY())) {
            return false;
        } else {
            //Verifier si le chevalet et les cases correspondantes possèdent ces lettres (inclure les lettre déjà présente dans le plateau)
            if (!presenceLettres(getMot(), getPositionX(), getPositionY())) {
                return false;
            } else {
                // Récuperer les mots formés, puis valider tous les mots formés

            }
        }
        return true;
    }
    public Map ListeMot(){
        String MotFactice = "a";
        Map tableaudeMot = new Hashtable();

        return tableaudeMot;
    }

    public boolean presenceLettres(String pMot, int pPositionX, int pPositionY) {
        this.setAllCaseFact();
        char find = '\0';
        for (int i = 0; i < pMot.length(); i++) {
            if (getOrientationMot() == 'h') {
                find = Plateau.getValeurFalseTableau(pPositionX + i, pPositionY);
            } else if (getOrientationMot() == 'v') {
                find = Plateau.getValeurFalseTableau(pPositionX, pPositionY + i);
            }
            if (getCaseFact1().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase1());
                this.setCaseFact1(null);
            } else if (getCaseFact2().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase2());
                this.setCaseFact2(null);
            } else if (getCaseFact3().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase3());
                this.setCaseFact3(null);
            } else if (getCaseFact4().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase4());
                this.setCaseFact4(null);
            } else if (getCaseFact5().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase5());
                this.setCaseFact5(null);
            } else if (getCaseFact6().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase6());
                this.setCaseFact6(null);
            } else if (getCaseFact7().equals(find)) {
                System.out.println("Vous avez utiliser la lettre :" + getCase7());
                this.setCaseFact7(null);
            } else if (Plateau.getValeurFalseTableau(pPositionX, pPositionY) == Plateau.getValeurFalseTableau(pPositionX, pPositionY)) {
                System.out.println("La lettre " + Plateau.getValeurFalseTableau(pPositionX, pPositionY) + "se trouvait dans le tableau");
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean touch(String pMot, int positionX, int positionY) {
        if (getOrientationMot() == 'h') {
            for (int i = positionX; i <= pMot.length() + positionX; i++) {
                if (!(Plateau.getvaleurTableau(i + 1, positionY) == '\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i - 1, positionY) == '\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i, positionY - 1) == '\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i, positionY + 1) == '\0'))
                    return true;
                if (i == 8)
                    return true;
            }
        } else if (getOrientationMot() == 'v') {
            for (int i = positionY; i <= pMot.length() + positionY; i++) {
                if (!(Plateau.getvaleurTableau(positionX + 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX - 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX, i - 1) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX, i + 1) == '\0')) {
                    return true;
                } else if (i == 8) {
                    return true;
                }
            }
        } else {
            System.out.println("Erreur : l'orientation ne peut être que vertical ou horizontal");
            return false;
        }
        System.out.println("");
        return false;
    }

}
