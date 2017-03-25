package scrabble.game;

import javafx.print.PageLayout;

import java.util.*;
import java.util.function.Consumer;

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

    //Constructeur Joueur
    public Joueur() {}

    //Méthodes
    public boolean pLacerDesLettres() {
        //Etape placement
        setPositionXValide(false);
        setPositionYValide(false);
        //Demander le mot, la position X et Y de ce mot au préalable
        System.out.println("Quelle mot voulez vous placer ?");
        Scanner iniParametre = new Scanner(System.in);
        setMot(iniParametre.nextLine().toUpperCase());
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
                System.out.println("position X : " + i);
                System.out.println("position Y : " + getPositionY());
                if (!(Plateau.getvaleurTableau(i + 1, getPositionY()) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i - 1, getPositionY()) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i, getPositionY() - 1) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(i, getPositionY() + 1) == '\0')) {
                    return true;
                } else if (i == 7 && getPositionY() == 7) {
                    return true;
                }
            }
        } else if (getOrientationMot() == 'v') {
            for (int i = getPositionY(); i < getMot().length() + getPositionY(); i++) {
                System.out.println("position X : " + getPositionX());
                System.out.println("position Y : " + i);
                if (!(Plateau.getvaleurTableau(getPositionX() + 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(getPositionX() - 1, i) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(getPositionX(), i - 1) == '\0')) {
                    return true;
                } else if (!(Plateau.getvaleurTableau(getPositionX(), i + 1) == '\0')) {
                    return true;
                } else if (i == 7 && getPositionX() == 7) {
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
                    if (j+getPositionY()>15){
                        System.out.println("Erreur programme !");
                        return false;
                    }
                    while (!(Plateau.getvaleurTableau(getPositionX()+j,getPositionY())=='\0')
                            ||(Plateau.getvaleurTableau(getPositionX()+j,getPositionY())==getMot().charAt(i))) {
                        j++;
                    }
                    Plateau.setValeurFalseTableau(getPositionX()+j, getPositionY(), getMot().charAt(i));
                    j++;
            }
            do {
                j--;
            } while((!(Plateau.getvaleurTableau(getPositionX()+j,getPositionY())=='\0')));
            String motComplet = "";
            while((!(Plateau.getvaleurTableau(getPositionX()+j,getPositionY())=='\0'))){
                motComplet +=Plateau.getvaleurTableau(getPositionX()+j,getPositionY());
                j++;
            }
            System.out.println(motComplet);
            System.out.println("Le mot a été formé");
            return true;
        } else if (this.getOrientationMot() == 'v') {
            if (getPositionY() + getMot().length() > 15) {
                System.out.println("Votre mot depasse les cases du tableau");
                return false;
            }
            for (int i = 0; i < getMot().length(); i++) {
                if (j+getPositionY()>15){
                    System.out.println("Erreur programme !");
                    return false;
                }
                while (!(Plateau.getvaleurTableau(getPositionX(),getPositionY()+j)=='\0')
                        ||(Plateau.getvaleurTableau(getPositionX(),getPositionY()+j)==getMot().charAt(i))) {
                        j++;
                }
                Plateau.setValeurFalseTableau(getPositionX(), j + getPositionY(), getMot().charAt(i));
                j++;
            }
            System.out.println("Le mot a été formé");
            return true;
        }
        System.out.println("Erreur sur l'orientation du mot");
        return false;
    }
}