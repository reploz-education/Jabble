package scrabble.game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue \n Vous jouez au Scrabble \n Combien de joueurs êtes vous ? \n");
        Scanner parametre = new Scanner(System.in);
        int nombreJoueurs;
        do {
            nombreJoueurs = parametre.nextInt();
        } while (!(nombreJoueurs <= 4 && nombreJoueurs > 0));
        deroulementdelapartie(nombreJoueurs);
        System.out.println("Fin du jeu.");
    }

    public static void deroulementdelapartie(int pNombreJoeurs) {
        System.out.println("Il y aura " + pNombreJoeurs + " joueur(s)");
        Fenetre fenetre = new Fenetre();
        Scanner choixjoueur = new Scanner(System.in);
        String name;
        Joueur Player1 = new Joueur();
        Joueur Player2 = new Joueur();
        Joueur Player3 = new Joueur();
        Joueur Player4 = new Joueur();
        if (pNombreJoeurs >= 1) {
            System.out.println("Choisissez un nom pour le joueur 1 :");
            name = choixjoueur.nextLine();
            Player1.initaliseChevalet();
            Player1.setNamePlayer(name);
        }
        if (pNombreJoeurs >= 2) {
            System.out.println("Choisissez un nom pour le joueur 2 :");
            name = choixjoueur.nextLine();
            Player2.initaliseChevalet();
            Player2.setNamePlayer(name);
        }
        if (pNombreJoeurs >= 3) {
            System.out.println("Choisissez un nom pour le joueur 3 :");
            name = choixjoueur.nextLine();
            Player3.initaliseChevalet();
            Player3.setNamePlayer(name);
        }
        if (pNombreJoeurs >= 4) {
            System.out.println("Choisissez un nom pour le joueur 4 :");
            name = choixjoueur.nextLine();
            Player4.initaliseChevalet();
            Player4.setNamePlayer(name);
        }

        //Initialisation de la partie : Mise en place des variables
        boolean passertour1 = false;
        boolean passertour2 = false;
        boolean passertour3 = false;
        boolean passertour4 = false;
        boolean gameOver = false;
        boolean retry1;
        boolean retry2;
        boolean retry3;
        boolean retry4;
        int challenge = 0;
        //Deroulement de la partie
        while (!gameOver) {
            retry1 = true;
            retry2 = true;
            retry3 = true;
            retry4 = true;
            while (retry1 && pNombreJoeurs >= 1) {
                System.out.println("Joueur 1 : "+Player1.getNamePlayer());
                fenetre.changerchevalet(
                        Player1.getCase1(),
                        Player1.getCase2(),
                        Player1.getCase3(),
                        Player1.getCase4(),
                        Player1.getCase4(),
                        Player1.getCase6(),
                        Player1.getCase7());
                Fenetre.infoNouveauJoueur(Player1.getNamePlayer());
                System.out.println("Menu des actions :" +
                        "\n 1 - Placer un mot" +
                        "\n 2 - Changer de lettre" +
                        "\n 3 - Passer votre tour");
                switch (choixjoueur.nextInt()) {
                    case 1:
                        if (Player1.pLacerDesLettres()) {
                            fenetre.miseajour();
                            retry1 = false;
                        }
                        break;
                    case 2:
                        if (Lettre.nouvellesLettresDisponible()) {
                            System.out.println("Combien de lettre voulez vous changer ?");
                            Player1.changeLettres(choixjoueur.nextInt());
                            retry1 = false;
                            challenge ++;
                        } else {
                            System.out.println("Il n'y a plus de lettre disponible");
                        }
                        break;
                    case 3:
                        System.out.println("Vous passez votre tour");
                        passertour1 = true;
                        retry1 = false;
                        challenge ++;
                        break;
                    default:
                        System.out.println("Vous vous êtes trompé, veuillez recommencer votre choix");
                        break;
                }
            }
            while (retry2 && pNombreJoeurs >= 2) {
                System.out.println("Joueur 2 : "+Player2.getNamePlayer());
                fenetre.changerchevalet(
                        Player2.getCase1(),
                        Player2.getCase2(),
                        Player2.getCase3(),
                        Player2.getCase4(),
                        Player2.getCase4(),
                        Player2.getCase6(),
                        Player2.getCase7());
                Fenetre.infoNouveauJoueur(Player2.getNamePlayer());
                System.out.println("Menu des actions :" +
                        "\n 1 - Placer un mot" +
                        "\n 2 - Changer de lettre" +
                        "\n 3 - Passer votre tour");
                switch (choixjoueur.nextInt()) {
                    case 1:
                        if (Player2.pLacerDesLettres()) {
                            fenetre.miseajour();
                            retry2 = false;
                        }
                        break;
                    case 2:
                        if (Lettre.nouvellesLettresDisponible()) {
                            System.out.println("Combien de lettre voulez vous changer ?");
                            Player2.changeLettres(choixjoueur.nextInt());
                            retry2 = false;
                        } else {
                            System.out.println("Il n'y a plus de lettre disponible");
                        }
                        break;
                    case 3:
                        System.out.println("Vous passez votre tour");
                        passertour2 = true;
                        retry2 = false;
                        break;
                    default:
                        System.out.println("Vous vous êtes trompé, veuillez recommencer votre choix");
                        break;
                }
            }
            while (retry3 && pNombreJoeurs >= 3) {
                System.out.println("Joueur 3 : " +Player3.getNamePlayer());
                fenetre.changerchevalet(
                        Player3.getCase1(),
                        Player3.getCase2(),
                        Player3.getCase3(),
                        Player3.getCase4(),
                        Player3.getCase4(),
                        Player3.getCase6(),
                        Player3.getCase7());
                Fenetre.infoNouveauJoueur(Player3.getNamePlayer());
                System.out.println("Menu des actions :" +
                        "\n 1 - Placer un mot" +
                        "\n 2 - Changer de lettre" +
                        "\n 3 - Passer votre tour");
                switch (choixjoueur.nextInt()) {
                    case 1:
                        if (Player3.pLacerDesLettres()) {
                            retry3 = false;
                            fenetre.miseajour();
                        }
                        break;
                    case 2:
                        if (Lettre.nouvellesLettresDisponible()) {
                            System.out.println("Combien de lettre voulez vous changer ?");
                            Player3.changeLettres(choixjoueur.nextInt());
                            retry3 = false;
                        } else {
                            System.out.println("Il n'y a plus de lettre disponible");
                        }
                        break;
                    case 3:
                        System.out.println("Vous passez votre tour");
                        passertour3 = true;
                        retry3 = false;
                        break;
                    default:
                        System.out.println("Vous vous êtes trompé, veuillez recommencer votre choix");
                        break;
                }
            }
            while (retry4 && pNombreJoeurs == 4) {
                System.out.println("Joueur 4 : " +Player4.getNamePlayer());
                fenetre.changerchevalet(
                        Player4.getCase1(),
                        Player4.getCase2(),
                        Player4.getCase3(),
                        Player4.getCase4(),
                        Player4.getCase4(),
                        Player4.getCase6(),
                        Player4.getCase7());
                Fenetre.infoNouveauJoueur(Player4.getNamePlayer());
                System.out.println("Menu des actions :" +
                        "\n 1 - Placer un mot" +
                        "\n 2 - Changer de lettre" +
                        "\n 3 - Passer votre tour");
                switch (choixjoueur.nextInt()) {
                    case 1:
                        if (Player4.pLacerDesLettres()) {
                            fenetre.miseajour();
                            retry4 = false;
                        }
                        break;
                    case 2:
                        if (Lettre.nouvellesLettresDisponible()) {
                            System.out.println("Combien de lettre voulez vous changer ?");
                            retry4 = false;
                            Player4.changeLettres(choixjoueur.nextInt());
                        } else {
                            System.out.println("Il n'y a plus de lettre disponible");
                        }
                        break;
                    case 3:
                        System.out.println("Vous passez votre tour");
                        passertour4 = true;
                        retry4 = false;
                        break;
                    default:
                        System.out.println("Vous vous êtes trompé, veuillez recommencer votre choix");
                        break;
                }
            }

            if ((pNombreJoeurs == 1 && (challenge==3))
                    || ((pNombreJoeurs == 2) && (passertour1 && passertour2))
                    || (pNombreJoeurs == 3 && (passertour1 && passertour2 && passertour3))
                    || (pNombreJoeurs > 4 && (passertour1 && passertour2 && passertour3 && passertour4))
                    ) {
                gameOver = true;
            }
        }
    }

}

