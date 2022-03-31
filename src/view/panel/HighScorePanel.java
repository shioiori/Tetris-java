package view.panel;

import model.GameImage;
import model.LoadData;
import view.button.UndoButton;

import javax.swing.*;
import java.awt.*;

public class HighScorePanel extends JPanel {
    UndoButton undo;
    static public int scr[] = {0, 0, 0, 0, 0};

    public HighScorePanel(){
        undo = new UndoButton();
        undo.setPrevious(MainMenuPanel.getInstance());
        this.add(undo);
        // repaint();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintHighScore(g);
    }
}
