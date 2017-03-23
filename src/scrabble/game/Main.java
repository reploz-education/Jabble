package scrabble.game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        String Motest = "VALEUR";
        int positionX = 7;
        int positionY = 7;
        String orientation = "horizontal";

        Joueur Player1 = new Joueur("Mickael");

        Player1.placerdesLettres(Motest, positionX, positionY, orientation, true);
        */
        Joueur Player1 = new Joueur("Dimitri");
        boolean retry = true;
        while (retry){
            Scanner choixjoueur = new Scanner(System.in);
            System.out.println("Menu des actions :" +
                    "\n 1 - Placer un mot" +
                    "\n 2 - Changer de lettre" +
                    "\n 3 - Passer votre tour");
            switch (choixjoueur.nextInt()) {
                case 1:
                    Player1.pLacerDesLettres();
                    retry = false;
                    break;
                case 2:
                    System.out.println("Combien de lettre voulez vous changer ?");
                    Player1.changeLettres(choixjoueur.nextInt());
                    retry = false;
                    break;
                case 3:
                    System.out.println("Vous passez votre tour");
                    retry = false;
                    break;
                default:
                    System.out.println("Vous vous êtes trompé, veuillez recommencer votre choix");
                    break;
            }
        }
        System.out.println("Fin du jeu.");
    }
}
