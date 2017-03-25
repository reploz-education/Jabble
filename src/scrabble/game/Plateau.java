package scrabble.game;

public class Plateau {
    private static char valeurTableau[][] = new char[15][15];
    private static char valeurFalseTableau[][] = new char[15][15];

    public static void setValeurFalseTableau(int pPositionX, int pPositionY, char pLettres){
        valeurFalseTableau[pPositionX][pPositionY] = pLettres;
    }
    public static void setValeurTableau(int pPositionX, int pPositionY, char pLettres){
        valeurTableau[pPositionX][pPositionY] = pLettres;
    }
    public static char getvaleurTableau(int pPositionX, int pPositionY){
        return valeurTableau[pPositionX][pPositionY];
    }
    public static char getValeurFalseTableau(int pPositionX, int pPositionY) {
        return valeurFalseTableau[pPositionX][pPositionY];
    }

    public static void setInitialiseTableau(){
        for (int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                setValeurFalseTableau(i,j,getvaleurTableau(i,j));
            }
        }
    }
    public static void setConfirmTableau(){
        for (int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                setValeurTableau(i,j,getValeurFalseTableau(i,j));
            }
        }
    }
    public static void placerlesLettres(){

    }
}
