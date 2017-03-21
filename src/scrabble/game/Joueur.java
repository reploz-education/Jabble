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

    //Accesseurs
    public String getNamePlayer(){
        return this.namePlayer;
    }
    public String getMot(){
        return this.mot;
    }
    public int getPositionX(){
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }
    public int getScorePlayer(){ return this.scorePlayer; }
    public boolean getpositionXValide(){
        return this.positionXValide;
    }
    public boolean getpositionYValide(){
        return this.positionYValide;
    }

    //Mutateurs
    public void setPositionXValide(boolean positionXValide) {
        this.positionXValide = positionXValide;
    }
    public void setPositionYValide(boolean positionYValide) {
        this.positionYValide = positionYValide;
    }
    public void setNamePlayer(String pNamePlayer){
        this.namePlayer = pNamePlayer;
    }
    public void setMot(String pMot){
        this.mot = pMot;
    }
    public void setPositionX(int pPositionX){
        this.positionX = pPositionX;
    }
    public void setPositionY(int pPositionY){
        this.positionY = pPositionY;
    }
    public void setScorePlayer(int pScorePlayer){
        this.scorePlayer = pScorePlayer;
    }
    public void setPlacement(String pPlacement){
        String placement = pPlacement;
    }

    //Constructeur Joueur
    public Joueur(String pNamePlayer){this.namePlayer = pNamePlayer;}
    //Méthodes
    public boolean pLacerDesLettres(){
        setPositionXValide(false);
        setPositionYValide(false);
        //Demander le mot, la position X et Y de ce mot au préalable
        System.out.println("Quelle mot voulez vous placer ?");
        Scanner iniParametre = new Scanner(System.in);
        setMot(iniParametre.nextLine());
        do {
            System.out.println("Quelle serait la position X de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionX(iniParametre.nextInt() + 1);
            if (getPositionX()<=15 && getPositionX()>0)
                setPositionXValide(true);
        } while (!getpositionXValide());
        do {
            System.out.println("Quelle serait la positoin Y de ce mot ?" +
                    "Attention : elle ne peut être contenue qu'entre 1 et 15 ");
            setPositionY((iniParametre.nextInt()) + 1);
            if (getPositionY()<=15 && getPositionY()>0)
                setPositionYValide(true);
        } while (!getpositionYValide());

        // Initialise le tableau
        Plateau.setInitialiseTableau();
        //Placer les lettres dans le tableaux factices.
        //Double fonction

        //Verifier si le chevalet et les cases correspondantes possèdent ces lettres
        return true;
    }
    public static boolean touchOther(String pMot, int positionX, int positionY, String pOrientation){
        if (pOrientation == "horizontal"){
            for (int i=positionX; i<pMot.length()+positionX; i++){
                if(!(Plateau.getvaleurTableau(i+1, positionY)=='\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i-1, positionY)=='\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i, positionY-1)=='\0'))
                    return true;
                if (!(Plateau.getvaleurTableau(i, positionY+1)=='\0'))
                    return true;
            }
        } else if ( pOrientation == "vertical" ){
            for (int i=positionY; i<pMot.length()+positionY; i++){
                if(!(Plateau.getvaleurTableau(positionX+1,i)=='\0')) {
                    return true;
                }
                if (!(Plateau.getvaleurTableau(positionX-1,i)=='\0')) {
                    return true;
                }
                if (!(Plateau.getvaleurTableau(positionX, i-1)=='\0')) {
                    return true;
                }
                if (!(Plateau.getvaleurTableau(positionX, i+1)=='\0')) {
                    return true;
                }
            }
        } else {
            System.out.println("Erreur : l'orientation ne peut être que vertical ou horizontal");
            return false;
        }
        return false;
    }
    public static boolean touchMid(String pMot, int positionX, int positionY, String pOrientation){
        if (pOrientation == "horizontal" || pOrientation == "vertical"){
            for (int i=positionX; i<pMot.length()+positionX; i++)
                if (positionX == 7 && positionY == 7)
                    return true;
        }else{
            return false;
        }
        return false;
    }//OK
}
