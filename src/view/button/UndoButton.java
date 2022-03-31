package view.button;

import view.frame.GameFrame;
import view.frame.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UndoButton extends JButton implements MouseListener {
    private Object previous;
    private Object current;

    public UndoButton(){
        this.setText("Return");
        this.setPreferredSize(new Dimension(200, 50));
        this.setFont(new Font("Noto Sans", Font.BOLD, 32));
        this.setBorder(new roundBtnBorder(25));
        this.setFocusable(false);
        this.addMouseListener(this);
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Object getCurrent() {
        return current;
    }

    public void setCurrent(Object current) {
        this.current = current;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (previous instanceof JFrame){
            MainMenuFrame mainMenuFrame = (MainMenuFrame) previous;
            GameFrame gameFrame = (GameFrame) current;
            gameFrame.setVisible(false);
            mainMenuFrame.setVisible(true);
        }
        if (previous instanceof JPanel){
            MainMenuFrame mainMenuFrame = MainMenuFrame.getInstance();
            mainMenuFrame.changePanel((JPanel) previous);
        }
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
}
