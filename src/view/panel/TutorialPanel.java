package view.panel;

import model.GameImage;
import view.button.UndoButton;

import javax.swing.*;
import java.awt.*;

public class TutorialPanel extends JPanel {
    UndoButton undo;

    public TutorialPanel(){
        undo = new UndoButton();
        undo.setPrevious(MainMenuPanel.getInstance());
        this.add(undo);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.getInstance().getImage("Mainmenu Background"), 0, 0, null);
    }

}
