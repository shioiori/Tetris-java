package view.panel;

import javax.swing.*;

public class GamePanel extends JPanel {
    public static GamePanel instance = null;

    public static GamePanel getInstance(){
        if (instance == null) instance = new GamePanel();
        return instance;
    }
}
