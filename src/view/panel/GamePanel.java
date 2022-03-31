package view.panel;

import constant.GameConstant;
import model.Painting;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static GamePanel instance = null;

    public static GamePanel getInstance(){
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    private int gameWidth;
    private int gameHeight;

    public int getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public GamePanel(){
        gameWidth = 30 + GameConstant.MAX_COL * GameConstant.BLOCKSIZE + 250;
        gameHeight = 60 + GameConstant.MAX_ROW * GameConstant.BLOCKSIZE;
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Painting.getInstance().paint(g);
    }

}
