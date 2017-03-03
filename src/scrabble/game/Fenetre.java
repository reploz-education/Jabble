package scrabble.game;
import javax.swing.*;

public class Fenetre extends JFrame{
    public Fenetre(){
        this.setTitle("Scrabble v.Java");
        this.setSize(1500,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
