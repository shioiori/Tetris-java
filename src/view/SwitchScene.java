package view;

import controller.Tetris;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwitchScene implements MouseListener {

    JFrame myFrame;

    SwitchScene(JFrame jFrame){
        myFrame = jFrame;
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
            Window window = new Window(myFrame);
            myFrame.setVisible(false);
        }
        if ("Tutorial".equals(cmd)){
            Tutorial tutorial = new Tutorial(myFrame);
            myFrame.setVisible(false);
        }
        if ("High Score".equals(cmd)){
            HighScoreBoard highScoreBoard = new HighScoreBoard(myFrame);
            myFrame.setVisible(false);
        }
        if ("Exit".equals(cmd)){
            //Tutorial tutorial = new Tutorial();
            System.exit(1);
            myFrame.setVisible(false);
        }
    }
}
