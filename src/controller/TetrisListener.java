package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!pause){
            switch (key) {
                case KeyEvent.VK_ENTER:
                    if (lose) {
                        newGameStart();
                        updateHighScore(score);
                        score = 0;
                    }
                    break;
                case KeyEvent.VK_UP:
                    rotateAction();
                    break;
                case KeyEvent.VK_DOWN:
                    softDropAction();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeftAction();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRightAction();
                    break;
                case KeyEvent.VK_SPACE:
                    hardDropAction();
                    break;
                case KeyEvent.VK_R:
                    if (lose) {
                        updateHighScore(score);
                        score = 0;
                        mainmenu.setVisible(true);
                        window.setVisible(false);
                        window.dispose();
                    }
                case KeyEvent.VK_P:
                    if (!lose) pauseAction();
            }
        }
        else {
            if (key == KeyEvent.VK_P) continueAction();
        }
        if (!lose) repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
