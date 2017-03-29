package scrabble.game;

import javafx.print.PageLayout;

import java.util.*;
import java.util.function.Consumer;

public class Joueur extends Chevalet {

    //Variables
    private String namePlayer;
    private String mot;
    private int scorePlayer;
    private int PointaAjouter;
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

    //Constructeur Joueur
    public Joueur() {
    }

    //Méthodes
    public boolean pLacerDesLettres() {
        //Etape placement
        setPositionXValide(false);
        setPositionYValide(false);
        //Demander le mot, la position positionX et positionY de ce mot au préalable
        System.out.println("Quelle mot voulez vous placer ?");
        Scanner iniParametre = new Scanner(System.in);
        setMot(iniParametre.nextLine().toUpperCase());
        do {
            System.out.println("Quelle serait la position positionX de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionX(iniParametre.nextInt() - 1);
            if (getPositionX() <= 14 && getPositionX() > 0)
                setPositionXValide(true);
        } while (!getpositionXValide());

        do {
            System.out.println("Quelle serait la positoin positionY de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionY(iniParametre.nextInt() - 1);
            if (getPositionY() <= 14 && getPositionY() > 0)
                setPositionYValide(true);
        } while (!getpositionYValide());
        // Initialise le tableau
        Plateau.setInitialiseTableau();
        //Placer les lettres dans le tableaux factices.

        boolean verifOrientation = false;
        String orientation;
        while (!verifOrientation) {
            System.out.println("Quelle est l'orientation du mot ?" +
                    "Attention, l'horientation ne peut être qu'horizontal ou vertical");
            orientation = iniParametre.nextLine();
            System.out.println(orientation);
            if (orientation.equals("horizontal") || orientation.equals("HORIZONTAL")) {
                setOrientationMot('h');
                verifOrientation = true;
            } else if (orientation.equals("vertical") || orientation.equals("VERTICAL")) {
                setOrientationMot('v');
                verifOrientation = true;
            }
        }
        System.out.println(motVeritalbe());
        formationMotPrincipal();
        //Etape verification
        //Verifier si le mot indiqué touche bien une case contenant une lettre ou le milieu du tableau
        if (!touch()) { //Il marche
            System.out.println("Vos lettres ne touchent ni le centre du plateau ni au autre mot");
            return false;
        } else {
            //Verifier si le chevalet et les cases correspondantes possèdent ces lettres (inclure les lettre déjà présente dans le plateau)
            if (!presenceLettres()) { //il ne marche pas
                System.out.println("Vous ne possédez pas les lettres suffisantes pour afficher ce mot");
                return false;
            } else {
                // Récuperer les mots formés, puis verifier qu'ils font partis du dico
                if (!motSecondaire()) {
                    System.out.println("Les mots formés avec les lettres que vous avez posé ne sont pas valide");
                    return false;
                } else {
                    this.confirmAllsetchange();
                    Plateau.setConfirmTableau();
                    System.out.println("Everything is all Right !!!");
                }
            }
        }
        return true;
    }

    public boolean motSecondaire() {
        String MotFactice = "";
        boolean alawaysAword = true;
        ArrayList<String> tableaudeMot = new ArrayList<>();
        if (getOrientationMot() == 'h') {
            for (int i = getPositionX(); i <= getPositionX() + getMot().length(); i++) {
                if (!(Plateau.getValeurFalseTableau(i, getPositionY() - 1) == '\0')
                        && (Plateau.getValeurFalseTableau(i, getPositionY() + 1) == '\0')
                        || !(Plateau.getValeurFalseTableau(i, getPositionY() + 1) == '\0')
                        && (Plateau.getValeurFalseTableau(i, getPositionY() - 1) == '\0')) {
                    int posMotsec = getPositionY(); // Position du mot secondaire
                    while (alawaysAword) {
                        if (!(posMotsec == '\0')) {
                            posMotsec--;
                        } else {
                            alawaysAword = false;
                        }
                    }
                    while (!alawaysAword) {
                        if (!(posMotsec == '\0')) {
                            MotFactice += Plateau.getValeurFalseTableau(i, posMotsec);
                            posMotsec++;
                        }
                    }
                    tableaudeMot.add(MotFactice);
                }
            }
        } else if (getOrientationMot() == 'v') {
            for (int i = getPositionY(); i < getPositionY() + getMot().length(); i++) {
                if (!(Plateau.getValeurFalseTableau(getPositionX(), i - 1) == '\0')
                        && !(Plateau.getValeurFalseTableau(getPositionX(), i + 1) == '\0')
                        || !(Plateau.getValeurFalseTableau(getPositionX(), i + 1) == '\0')
                        && (Plateau.getValeurFalseTableau(getPositionX(), i - 1) == '\0')
                        ) {
                    int posMotsec = getPositionY(); // Position du mot secondaire
                    while (alawaysAword) {
                        if (!(posMotsec == '\0')) {
                            posMotsec--;
                        } else {
                            alawaysAword = false;
                        }
                    }
                    while (!alawaysAword) {
                        if (!(posMotsec == '\0')) {
                            MotFactice += Plateau.getValeurFalseTableau(i, posMotsec);
                            posMotsec++;
                        }
                    }
                    tableaudeMot.add(MotFactice);
                }
            }
        }
        for (String listeMot : tableaudeMot) {
            //PointaAjouter += ajoutScore();
            if (!Lettre.motValide(listeMot)) {
                return false;
            }
        }
        return true;
    }

    public boolean presenceLettres() {
        this.setAllCaseFact();
        String primaire;
        String lettre;
        for (int i = 0; i < getMot().length(); i++) {
            primaire = "";
            lettre = "";
            if (getOrientationMot() == 'h') {
                primaire += Plateau.getvaleurTableau(getPositionX() + i, getPositionY());
            } else if (getOrientationMot() == 'v') {
                primaire += Plateau.getvaleurTableau(getPositionX(), getPositionY() + i);
            }
            lettre += getMot().charAt(i);
            if (this.getCase1().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase1() + "de la case 1");
                this.setCaseFact1(generateLettre());
            } else if (this.getCase2().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase2() + "de la case 2");
                this.setCaseFact2(generateLettre());
            } else if (this.getCase3().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase3() + "de la case 3");
                this.setCaseFact3(generateLettre());
            } else if (this.getCase4().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase4() + "de la case 4");
                this.setCaseFact4(generateLettre());
            } else if (this.getCase5().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase5() + "de la case 5");
                this.setCaseFact5(generateLettre());
            } else if (this.getCase6().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase6() + "de la case 6");
                this.setCaseFact6(generateLettre());
            } else if (this.getCase7().equals(lettre)) {
                System.out.println("Vous avez utiliser la lettre : " + getCase7() + "de la case 7");
                this.setCaseFact7(generateLettre());
            } else if (lettre == primaire) {
                System.out.println("La lettre " + Plateau.getValeurFalseTableau(getPositionX(), getPositionY()) + "se trouvait dans le tableau");
            } else {
                if (this.getCase1().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact1(generateLettre());
                } else if (this.getCase2().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact2(generateLettre());
                } else if (this.getCase3().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact3(generateLettre());
                } else if (this.getCase4().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact4(generateLettre());
                } else if (this.getCase5().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact5(generateLettre());
                } else if (this.getCase6().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact6(generateLettre());
                } else if (this.getCase7().equals("Joker")) {
                    System.out.println("Vous avez utilisé votre Joker");
                    this.setCaseFact7(generateLettre());
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean touch() {
        if (getOrientationMot() == 'h') {
            for (int i = getPositionX(); i < getMot().length() + getPositionX(); i++) {
                System.out.println("position positionX : " + i);
                System.out.println("position positionY : " + positionY);
                if (!(Plateau.getvaleurTableau(i + 1, positionY) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i - 1, positionY) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i, positionY - 1) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i, positionY + 1) == '\0')) {
                    return true;
                } else if (i == 7 && positionY == 7) {
                    return true;
                }
            }
        } else if (getOrientationMot() == 'v') {
            for (int i = getPositionY(); i < getMot().length() + getPositionY(); i++) {
                System.out.println("position positionX : " + positionX);
                System.out.println("position positionY : " + i);
                if (!(Plateau.getvaleurTableau(positionX + 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX - 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX, i - 1) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(positionX, i + 1) == '\0')) {
                    return true;
                } else if (i == 7 && positionX == 7) {
                    return true;
                }
            }
        } else {
            System.out.println("Erreur : l'orientation ne peut être que vertical ou horizontal");
            return false;
        }
        return false;
    }

    public boolean formationMotPrincipal() {
        int j = 0;
        if (this.getOrientationMot() == 'h') {
            if (getPositionX() + getMot().length() > 15) {
                System.out.println("Votre mot depasse les cases du tableau");
                return false;
            }
            for (int i = 0; i < getMot().length(); i++) {
                if (j + getPositionY() > 15) {
                    System.out.println("Erreur programme !");
                    return false;
                }
                while (!(Plateau.getvaleurTableau(positionX + j, positionY) == '\0')
                        || (Plateau.getvaleurTableau(positionX + j, positionY) == getMot().charAt(i))) {
                    j++;
                }
                Plateau.setValeurFalseTableau(getPositionX() + j, getPositionY(), getMot().charAt(i));
                j++;
            }
            return true;
        } else if (this.getOrientationMot() == 'v') {
            if (positionY + mot.length() > 15) {
                System.out.println("Votre mot depasse les cases du tableau");
                return false;
            }
            for (int i = 0; i < mot.length(); i++) {
                if (j + positionY > 15) {
                    System.out.println("Erreur programme !");
                    return false;
                }
                while (!(Plateau.getvaleurTableau(positionX, positionY + j) == '\0')
                        || (Plateau.getvaleurTableau(positionX, positionY + j) == mot.charAt(i))) {
                    j++;
                }
                Plateau.setValeurFalseTableau(positionX, j + positionY, mot.charAt(i));
                j++;
            }
            System.out.println("Le mot a été formé");
            return true;
        }
        System.out.println("Erreur sur l'orientation du mot");
        return false;
    }

    public String motVeritalbe() {
        String motComplet = "";
        int j = 0;
        if (orientationMot == 'h') {

            System.out.println((!(Plateau.getvaleurTableau(positionX + j, positionY) == '\0')));
            while ((!(Plateau.getvaleurTableau(positionX + j, positionY) == '\0'))) {
                j--;
            }
            while ((!(Plateau.getvaleurTableau(positionX + j, positionY) == '\0'))) {
                motComplet += Plateau.getvaleurTableau(positionX + j, positionY);
                j++;
            }
        } else if (orientationMot == 'v') {

            System.out.println((!(Plateau.getvaleurTableau(positionX, positionY + j) == '\0')));
            while ((!(Plateau.getvaleurTableau(positionX, positionY + j) == '\0'))) {
                j--;
            }
            while ((!(Plateau.getvaleurTableau(positionX, positionY + j) == '\0'))) {
                motComplet += Plateau.getvaleurTableau(positionX, positionY + j);
                j++;
            }
        }
        System.out.println("Le mot a été formé");
        System.out.println("Mot Complet : " + motComplet);
        return motComplet;
    }

    public void ajoutScore(String pMot, int pPositionX, int pPositionY) {
        int doublemot = 0;
        int triplemot = 0;
        int valeurLettre = 0;
        if (orientationMot == 'h') {
            for (int i = positionX; i < positionX + mot.length(); i++) {
                valeurLettre = position(pPositionX, pPositionY);
                if ((positionX == 1 || positionX == 8 || positionX == 15)
                        && (positionY == 0 || positionY == 0 || positionY == 14)) {
                    //Mot triple
                    triplemot++;
                } else if (((positionX == 1 || positionX == 13) && (positionY == 1 || positionY == 13))
                        || ((positionX == 2 || positionX == 12) && (positionY == 2 || positionY == 12))
                        || ((positionX == 3 || positionX == 11) && (positionY == 3 || positionY == 11))
                        || ((positionX == 4 || positionX == 10) && (positionY == 4 || positionY == 10))
                        ) {
                    //Mot Double
                    doublemot++;
                } else if (((positionX == 5 || positionX == 9) && (positionY == 5 || positionY == 9 || positionY == 1))
                        || ((positionX == 1 || positionX == 13) && (positionY == 5 || positionY == 9))) {
                    //Lettre triple
                    valeurLettre = valeurLettre * 3;
                } else if (((positionX == 6 || positionX == 8 || positionX == 2 || positionX == 12)
                        && (positionY == 2 || positionY == 6 || positionY == 8 || positionY == 12))
                        || ((positionX == 0 || positionX == 3 || positionX == 7 || positionX == 11 || positionX == 14)
                        && (positionY == 0 || positionY == 3 || positionY == 7 || positionY == 11 || positionY == 14))
                        ) {
                    //Double lettre
                    valeurLettre = valeurLettre * 2;
                }
            }
        } else if (orientationMot == 'v') {

            if ((positionX == 1 || positionX == 8 || positionX == 15)
                    && (pPositionY == 0 || pPositionY == 0 || pPositionY == 14)) {
                //Mot triple
                triplemot++;
            } else if (((positionX == 1 || positionX == 13) && (pPositionY == 1 || pPositionY == 13))
                    || ((positionX == 2 || positionX == 12) && (pPositionY == 2 || pPositionY == 12))
                    || ((positionX == 3 || positionX == 11) && (pPositionY == 3 || pPositionY == 11))
                    || ((positionX == 4 || positionX == 10) && (pPositionY == 4 || pPositionY == 10))
                    ) {
                //Mot Double
                doublemot++;
            } else if (((positionX == 5 || positionX == 9) && (pPositionY == 5 || pPositionY == 9 || pPositionY == 1))
                    || ((positionX == 1 || positionX == 13) && (pPositionY == 5 || pPositionY == 9))) {
                //Lettre triple
                valeurLettre = valeurLettre * 3;
            } else if (((positionX == 6 || positionX == 8 || positionX == 2 || positionX == 12)
                    && (pPositionY == 2 || pPositionY == 6 || pPositionY == 8 || pPositionY == 12))
                    || ((positionX == 0 || positionX == 3 || positionX == 7 || positionX == 11 || positionX == 14)
                    && (pPositionY == 0 || pPositionY == 3 || pPositionY == 7 || pPositionY == 11 || pPositionY == 14))
                    ) {
                //Double lettre
                valeurLettre = valeurLettre * 2;
            }
        } else {
            System.out.println("erreur orientation");
        }
    }

    public static int position(int pPositionX, int pPositionY) {
        int valeurLettre = 0;
        char valeurTableau = Plateau.getValeurFalseTableau(pPositionX, pPositionY);
        if (valeurTableau == 'A' || valeurTableau == 'E' || valeurTableau == 'I'
                || valeurTableau == 'O' || valeurTableau == 'U' || valeurTableau == 'L'
                || valeurTableau == 'N' || valeurTableau == 'R' || valeurTableau == 'S'
                || valeurTableau == 'T') {
            valeurLettre = 1;
        } else if (valeurTableau == 'D' || valeurTableau == 'G' || valeurTableau == 'M') {
            valeurLettre = 2;
        } else if (valeurTableau == 'B' || valeurTableau == 'C' || valeurTableau == 'P') {
            valeurLettre = 3;
        } else if (valeurTableau == 'J' || valeurTableau == 'Q') {
            valeurLettre = 8;
        } else if (valeurTableau == 'K' || valeurTableau == 'W' || valeurTableau == 'X' ||
                valeurTableau == 'Y' || valeurTableau == 'Z') {
            valeurLettre = 10;
        } else if (valeurTableau == 'F' || valeurTableau == 'H' || valeurTableau == 'V') {
            valeurLettre = 4;
        }
        return valeurLettre;
    }
}

