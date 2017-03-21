package scrabble.game;
import javax.swing.*;

public class Fenetre extends JFrame{
    private Panneau pan = new Panneau();

    public Fenetre(){
        this.setTitle("animation");
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        /* Créer un pannel
        Via ligne de commande de la classe JPanel
        JPanel pan = new JPanel();
        pan.setBackground(Color.green);
        this.setContentPane(pan);

        Via une classe hérité de JPanel
        this.setContentPane(new Panneau());
         */
        this.setContentPane(pan);
        this.setVisible(true);
        go();
    }
    private void go(){
        for(int i=0;i<5;i++){
            int x = pan.getPosX(), y = pan.getPosY();
            x++;
            y++;
            pan.setPosX(x);
            pan.setPosY(y);
            pan.repaint();
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(x == pan.getWidth() || y == pan.getHeight()){
                pan.setPosX(-50);
                pan.setPosY(-50);
            }
        }
    }
}
