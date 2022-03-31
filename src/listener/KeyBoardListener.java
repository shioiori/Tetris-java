package listener;

import controller.Painting;
import controller.TetrisController;
import view.frame.GameFrame;
import view.frame.MainMenuFrame;
import view.panel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        TetrisController tetrisController = TetrisController.getInstance();
        Painting painting = Painting.getInstance();
        int key = e.getKeyCode();
        if (!tetrisController.isPause()){
            switch (key) {
                case KeyEvent.VK_ENTER:
                    if (tetrisController.isLose()) {
                        tetrisController.newGameStart();
                        tetrisController.updateHighScore(tetrisController.getScore());
                        tetrisController.setScore(0);
                    }
                    break;
                case KeyEvent.VK_UP:
                    tetrisController.rotateAction();
                    break;
                case KeyEvent.VK_DOWN:
                    tetrisController.softDropAction();
                    break;
                case KeyEvent.VK_LEFT:
                    tetrisController.moveLeftAction();
                    break;
                case KeyEvent.VK_RIGHT:
                    tetrisController.moveRightAction();
                    break;
                case KeyEvent.VK_SPACE:
                    tetrisController.hardDropAction();
                    break;
                case KeyEvent.VK_R:
                    if (tetrisController.isLose()) {
                        tetrisController.updateHighScore(tetrisController.getScore());
                        tetrisController.setScore(0);
                        GameFrame.getInstance().setVisible(false);
                        MainMenuFrame.getInstance().setVisible(true);
                    }
                case KeyEvent.VK_P:
                    if (!tetrisController.isLose()) tetrisController.pauseAction();
            }
        }
        else {
            if (key == KeyEvent.VK_P) tetrisController.continueAction();
        }
        if (!tetrisController.isLose()) GamePanel.getInstance().repaint();
        // rất chắc chắn về tác dụng
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
