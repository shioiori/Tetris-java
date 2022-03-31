package view.panel;

import model.GameImage;
import view.roundBtnBorder;

import javax.swing.*;
import java.awt.*;

public class TutorialPanel extends JPanel {
    JButton undo;

    public TutorialPanel(){
        undo = new JButton("Undo");
        undo.setPreferredSize(new Dimension(200, 50));
        undo.setFont(new Font("Noto Sans", Font.BOLD, 32));
        undo.setBorder(new roundBtnBorder(25));
        undo.setFocusable(false);
        this.add(undo);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.getInstance().getImage("Mainmenu Background"), 0, 0, null);
    }

}
