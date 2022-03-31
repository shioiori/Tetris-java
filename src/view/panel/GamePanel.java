package view.panel;

import controller.Painting;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static GamePanel instance = null;

    public static GamePanel getInstance(){
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Painting.getInstance().paint(g);
    }

}
