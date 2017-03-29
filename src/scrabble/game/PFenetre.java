package scrabble.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PFenetre extends JFrame {
    private Pscrabble plateau = new Pscrabble();
    public PFenetre() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Scrable");
        this.setSize(700, 800);
        this.setLocationRelativeTo(null);
        this.setContentPane(plateau);
        this.setVisible(true);
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