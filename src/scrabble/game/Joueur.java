package scrabble.game;

import java.util.*;

public class Joueur extends Chevalet {

    //Variables
    private String namePlayer;
    private String mot;
    private int scorePlayer;
    private int positionX;
    private int positionY;

    //Accesseurs
    public String getNamePlayer(){
        return namePlayer;
    }
    public String getMot(){
        return mot;
    }
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public int getScorePlayer(){ return scorePlayer; }
    //Mutateurs
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
    public void placerdesLettres(String pMot, int positionX, int positionY, String pOrientation, boolean First){
        //Initialise un nouveau tableau
        Plateau.setInitialiseTableau();
        //Vérifier si le chevalet possède les lettres
        //presenceLettres(pMot);
        //Vérifier si le mot touche d'autres cases
        boolean p = false;
        if(!First){
            p = touchother(pMot, positionX, positionY, pOrientation);
            System.out.println(p);
        } else {
            p = touchmid(pMot, positionX, positionY, pOrientation);
            System.out.println(p);
        }
        if(p){
            //Récuperer les mots formés avec le placement des lettres
            String MotFactice, MotFactice2;
            ArrayList<String> motHorizontal = new ArrayList<String>();
            ArrayList<String> motVertical = new ArrayList<String>();

            if (pOrientation == "horizontal") {
                for (int i = positionX ; i < positionX + pMot.length(); i++) {
                    MotFactice = Plateau.formationMotH(i, positionY, pMot);
                    MotFactice2 = Plateau.formationMotV(i, positionY);
                    if (MotFactice.length()>=2){
                        //Récuperer les mots
                        System.out.println(MotFactice);
                        motHorizontal.add(MotFactice);
                    }
                    if (MotFactice2.length()>=2){
                        //Récupérer les mots
                        System.out.println(MotFactice2);
                        motVertical.add(MotFactice2);
                    }
                }
                //Ici v'est la liste motHorizontal qui aura des doublons, ne le récuperer qu'une seule fois !
            }
            if (pOrientation == "vertical") {
                for (int i = positionY; positionY < pMot.length(); i++) {
                    MotFactice = Plateau.formationMotH(positionX, i, pMot);
                    MotFactice2 = Plateau.formationMotV(positionX, i);
                    if (MotFactice.length()>=2){
                        //Récuperer les mots
                        motHorizontal.add(MotFactice);
                    }
                    if (MotFactice2.length()>=2){
                        //Récupérer les mots
                        motVertical.add(MotFactice2);
                    }
                    Lettre.motValide(motVertical.get(i));
                }
                //Ici c'est la liste motVertical qui aura des doublons, ne le récuperer qu'une seule fois !
            }
            for (int i=0; i<motHorizontal.size();i++){
                Lettre.motValide(motVertical.get(i));
            }
            for (int i=0; i<motVertical.size();i++){
                Lettre.motValide(motHorizontal.get(i));
            }
        }
        //Vérifier si les mots Factices récupérer est valide (je ne sais pas du tout comment je vais faire)
        //Placer les lettres
    }
    //Définir la vrai taille de la colonne/ligne qui doit être utilisé

    public static boolean touchother(String pMot, int positionX, int positionY, String pOrientation){
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
    public static boolean touchmid(String pMot, int positionX, int positionY, String pOrientation){
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
