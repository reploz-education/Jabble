package scrabble.game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Pscrabble extends JPanel {
    private final int carre = 40;
    private final int tcarre = 30;
    private String case1 = "";
    private String case2 = "";
    private String case3 = "";
    private String case4 = "";
    private String case5 = "";
    private String case6 = "";
    private String case7 = "";

    public void setAllCase(String case1, String case2, String case3, String case4, String case5, String case6, String case7) {
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.case5 = case5;
        this.case6 = case6;
        this.case7 = case7;
    }

    public void paintComponent(Graphics g) {
        Font font = new Font("", Font.BOLD, 20);
        g.setFont(font);
        int i = 0, j = 0;
        String tableau = "";
        g.setColor(Color.black);
        g.fillRect(20,20,carre*15+tcarre,carre*15+tcarre);
        for (int y = carre; y <= carre * 15; y += carre) {
            for (int x = carre; x <= carre * 15; x += carre) {
                tableau += Plateau.getvaleurTableau(i,j);
                if (x == carre * 8 && y == carre * 8) {
                    g.setColor(Color.MAGENTA);
                } else if ((x == carre || x == carre * 8 || x == carre * 15)
                        && (y == carre || y == carre * 8 || y == carre * 15)) {
                    g.setColor(Color.RED);
                } else if (((x == carre * 2 || x == carre * 14) && (y == carre * 2 || y == carre * 14))
                        || ((x == carre * 3 || x == carre * 13) && (y == carre * 3 || y == carre * 13))
                        || ((x == carre * 4 || x == carre * 12) && (y == carre * 4 || y == carre * 12))
                        || ((x == carre * 5 || x == carre * 11) && (y == carre * 5 || y == carre * 11))
                        ) {
                    g.setColor(Color.MAGENTA);
                } else if (((x == carre * 6 || x == carre * 10) && (y == carre * 6 || y == carre * 10 || y == carre * 2))
                        || ((x == carre * 2 || x == carre * 14) && (y == carre * 6 || y == carre * 10))) {
                    g.setColor(Color.blue);
                } else if (((x == carre * 7 || x == carre * 9 || x == carre * 3 || x == carre * 13)
                        && (y == carre * 3 || y == carre * 7 || y == carre * 9 || y == carre * 13))
                        || ((x == carre || x == carre * 4 || x == carre * 8 || x == carre * 12 || x == carre * 15)
                        && (y == carre || y == carre * 4 || y == carre * 8 || y == carre * 12 || y == carre * 15))
                ) {
                    g.setColor(Color.cyan);
                } else{
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x, y, tcarre, tcarre);
                g.setColor(Color.black);
                g.drawString(tableau.toUpperCase(),x+5,y+15);
                tableau = "";
                i++;
            }
            j++;
            i=0;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.black);
        Point center = new Point(carre*8+(carre/3),carre*8+(2*carre/9));
        g2d.fill(createStar(8, center, 20 / 9, 10));
        g2d.dispose();
        g.setColor(Color.gray);
        g.fillRect(20,carre * 17,carre*15+tcarre, tcarre*2);
        g.setColor(Color.black);
        g.drawString(case1, carre, carre*18);
        g.drawString(case2, carre*3, carre*18);
        g.drawString(case3, carre*5, carre*18);
        g.drawString(case4, carre*7, carre*18);
        g.drawString(case5, carre*9, carre*18);
        g.drawString(case6, carre*11, carre*18);
        g.drawString(case7, carre*13, carre*18);
    }
    public static Shape createStar(int arms, Point center, double rOuter, double rInner)
    {
        double angle = Math.PI / arms;

        GeneralPath path = new GeneralPath();

        for (int i = 0; i < 2 * arms; i++)
        {
            double r = (i & 1) == 0 ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) path.moveTo(p.getX(), p.getY());
            else path.lineTo(p.getX(), p.getY());
        }
        path.closePath();
        return path;
    }}