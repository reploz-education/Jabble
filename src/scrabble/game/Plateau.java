package scrabble.game;

public class Plateau {
    private static char valeurTableau[][] = new char[15][15];
    private static char valeurFalseTableau[][] = new char[15][15];

    public static void setValeurTableau(int pPositionX, int pPositionY, char pLettres){
        valeurTableau[pPositionX][pPositionY] = pLettres;
    }
    public static char getvaleurTableau(int pPositionX, int pPositionY){
        return valeurTableau[pPositionX][pPositionY];
    }

    public static void setInitialiseTableau(){
        valeurFalseTableau = valeurTableau;
    }
    public static String formationMotH(int pPositionX, int pPositionY){
        String mot = "";
        while (valeurFalseTableau[pPositionX][pPositionY]=='\0') {
            pPositionX--;
        }
        while (!(valeurFalseTableau[pPositionX][pPositionY]=='\0')){
            mot += valeurFalseTableau[pPositionX][pPositionY];
            pPositionX++;
        }
        return mot;
    }
    public static String formationMotV(int pPositionX, int pPositionY){
            String mot = "";
        while (valeurFalseTableau[pPositionX][pPositionY]=='\0') {
        pPositionX--;
        }
        while (!(valeurFalseTableau[pPositionX][pPositionY]=='\0')){
        mot += valeurFalseTableau[pPositionX][pPositionY];
        pPositionX++;
        }
        return mot;
    }
}
