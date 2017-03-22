package scrabble.game;

public class Plateau {
    private static char valeurTableau[][] = new char[15][15];
    private static char valeurFalseTableau[][] = new char[15][15];

    public static void setValeurFalseTableau(int pPositionX, int pPositionY, char pLettres){
        valeurFalseTableau[pPositionX][pPositionY] = pLettres;
    }
    public static char getvaleurTableau(int pPositionX, int pPositionY){
        return valeurTableau[pPositionX][pPositionY];
    }
    public static char getValeurFalseTableau(int pPositionX, int pPositionY) {
        return valeurFalseTableau[pPositionX][pPositionY];
    }

    public static void setInitialiseTableau(){
        valeurFalseTableau = valeurTableau;
    }
    public static void setConfirmTableau(){
        valeurTableau = valeurFalseTableau;
    }

    public static boolean formationMotPrincipalH(String pMot, int pPositionX, int pPositionY){
        if(pPositionX + pMot.length()>15){
            System.out.println("Votre mot depasse les cases du tableau");
            return false;
        }
        for (int i = 1; i <pMot.length(); i++){
            char c = pMot.charAt(i);
            System.out.println(c);
            if (!(getvaleurTableau(i + pPositionX , pPositionY) == c)){
                setValeurFalseTableau(i + pPositionX , pPositionY, pMot.charAt(i));
            }
        }
        return true;
    }
    public static boolean formationMotPrincialV(String pMot, int pPositionX, int pPositionY){
        if(pPositionY + pMot.length()>15){
            System.out.println("Votre mot depasse les cases du tableau");
            return false;
        }
        for (int i = 0 ; i <= pMot.length();i++){
            if (!(getvaleurTableau(pPositionX ,i + pPositionY) == pMot.charAt(i))){
                setValeurFalseTableau(pPositionX ,i + pPositionY, pMot.charAt(i));
            }
        }
        return true;
    }

    public static void placerlesLettres(){

    }



}
