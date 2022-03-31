package view.frame;
import constant.GameConstant;
import controller.Painting;
import controller.TetrisController;
import view.panel.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame{
    public static GameFrame instance = null;

    public static GameFrame getInstance(){
        if (instance == null) instance = new GameFrame();
        return instance;
    }

    TetrisController tetrisController;
    GamePanel gamePanel;

    public GameFrame(){
        this.setTitle("Tetris");
        this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(650, 100);
        this.setVisible(true);

        tetrisController = TetrisController.getInstance();
        gamePanel = new GamePanel();
        this.add(gamePanel);
        tetrisController.newGameStart();
        Painting painting = new Painting();
        painting.setGamePanel(gamePanel);
        painting.paint();
    }
}