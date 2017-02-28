package scrabble.game;

public class Main {
    public static void main(String[] args){
        int x = 1;
        int y = 7;
        String Mot = "CEDEZ";
        boolean ok =Joueur.touchmid(Mot, x, y, "horizontal");
        System.out.println(ok);
        Chevalet Monchevalet = new Chevalet();
        Monchevalet.setCase1(Monchevalet.generateLettre());
        System.out.println(Monchevalet.getCase1());
        Monchevalet.changeLettres(2);
        /*
        for (int i = 0; i < 104; i++){
            Lettre.addLettresUtilise();
        }
        System.out.print(Lettre.getLettresUtilise());
        Monchevalet.setCase2(Monchevalet.generateLettre());
        System.out.println("\n" + Monchevalet.getCase2());
        boolean mot = Lettre.motValide(Mot);
        System.out.println(mot);
        */
    }
}
