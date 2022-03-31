package listener;

import view.frame.GameFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwitchSceneListener implements MouseListener {

    public SwitchSceneListener(){
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton obj = (JButton) e.getSource();
        String cmd = obj.getText();
        if ("New Game".equals(cmd)){
            GameFrame gameFrame = new GameFrame();
        }
        if ("Tutorial".equals(cmd)){
            //Tutorial tutorial = new Tutorial();
        }
        if ("High Score".equals(cmd)){
           // HighScoreBoard highScoreBoard = new HighScoreBoard();
        }
        if ("Exit".equals(cmd)){
            System.exit(1);
        }
    }
}
