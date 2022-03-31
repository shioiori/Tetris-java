package listener;

import view.frame.GameFrame;
import view.frame.MainMenuFrame;
import view.panel.HighScorePanel;
import view.panel.TutorialPanel;

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
            GameFrame gameFrame = GameFrame.getInstance();
            MainMenuFrame.getInstance().setVisible(false);
        }
        if ("Tutorial".equals(cmd)){
            //TutorialFrame tutorial = TutorialFrame.getInstance();
            MainMenuFrame.getInstance().changePanel(new TutorialPanel());
        }
        if ("High Score".equals(cmd)){
           // HighScoreBoard highScoreBoard = new HighScoreBoard();
            MainMenuFrame.getInstance().changePanel(new HighScorePanel());
        }
        if ("Exit".equals(cmd)){
            System.exit(1);
        }
    }
}
