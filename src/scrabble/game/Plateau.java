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

    public static String formationMotSecondaireH(int pPositionX, int pPositionY, String pMot){
        String mot = "";
        int j = 0;
        for (int i = pPositionX ; i < pPositionX + pMot.length(); i++){
            valeurFalseTableau[i][pPositionY] = pMot.charAt(j);
            j++;
        }
        System.out.println(!(valeurFalseTableau[pPositionX][pPositionY]=='\0'));
        while (!(valeurFalseTableau[pPositionX][pPositionY]=='\0')) {
            System.out.println(pPositionX);
            pPositionX--;
        }
        while (!(valeurFalseTableau[pPositionX][pPositionY]=='\0')){
            mot += valeurFalseTableau[pPositionX][pPositionY];
            pPositionX++;
        }
        return mot;
    }
    public static String formationMotSecondaireV(int pPositionX, int pPositionY){
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

    public static boolean formationMotPrincipalH(String pMot, int pPositionX, int pPositionY){
        if(pPositionX + pMot.length()>15){
            System.out.println("Votre mot depasse les cases du tableau");
            return false;
        }
        for (int i = 0; i <=pMot.length(); i++){
            if (!(getvaleurTableau(i + pPositionX , pPositionY) == pMot.charAt(i))){
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
