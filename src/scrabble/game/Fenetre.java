package scrabble.game;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    Pscrabble plateau = new Pscrabble();
    CScrabble chevalet = new CScrabble();

    public Fenetre() {
        this.setTitle("Scrable");
        this.setSize(700, 800);
        this.setLocationRelativeTo(null);
        this.setContentPane(plateau);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miseajour();
    }

    public void miseajour() {
        plateau.repaint();
    }

    public static void infoNouveauJoueur(String pName){
        JOptionPane nouveauJoueur;

        nouveauJoueur = new JOptionPane();
        nouveauJoueur.showMessageDialog(null,
                "C'est au tour de "+pName, "Changement de joueur", JOptionPane.INFORMATION_MESSAGE);
    }

    public void changerchevalet(String case1, String case2, String case3, String case4, String case5, String case6, String case7) {
        plateau.setAllCase(case1, case2, case3, case4, case5, case6, case7);
        plateau.repaint();
    }

}