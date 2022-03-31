package view;

import model.GameImage;
import model.LoadData;

import javax.swing.*;
import java.awt.*;

public class HighScore extends JPanel {
    static public int scr[] = {0, 0, 0, 0, 0};

    public void paint(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintHighScore(g);
    }

    public void paintBackground(Graphics g){
        g.drawImage(GameImage.getInstance().getImage("Mainmenu Background"), 0,0, 550,830, null);
    }

    public void paintHighScore(Graphics g){
        g.setFont(new Font("Noto Sans", Font.BOLD, 40));
        int [] scr = LoadData.getInstance().loadHighScore();
        g.drawString("Top High Score", 100, 90);
        for (int i = 1; i <= 5; ++i) {
            g.drawString("No." + i + ": " + scr[i-1], 100, 100 + i * 80);
        }
    }

    public HighScore(){
        repaint();
    }
}
