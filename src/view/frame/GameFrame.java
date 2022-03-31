package view.frame;

import model.Painting;
import controller.TetrisController;
import listener.KeyBoardListener;
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
        //this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        //this.setLocation(650, 100);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        tetrisController = TetrisController.getInstance();
        gamePanel = GamePanel.getInstance();
        this.add(gamePanel);
        KeyBoardListener keyBoardListener = new KeyBoardListener();
        this.addKeyListener(keyBoardListener);
        tetrisController.newGameStart();
        Painting painting = Painting.getInstance();
        painting.setTetrisController(tetrisController);
        this.pack();
    }
}