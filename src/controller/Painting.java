package controller;

import constant.GameConstant;
import model.Cell;
import model.GameImage;
import view.panel.GamePanel;

import java.awt.*;

public class Painting{
    public static Painting instance = null;

    public static Painting getInstance(){
        if (instance == null) instance = new Painting();
        return instance;
    }

    TetrisController tetrisController;
    GameImage gameImage;

    public Painting(){
        gameImage = GameImage.getInstance();
    }

    public Painting(GamePanel gamePanel){
        tetrisController = TetrisController.getInstance();
        gameImage = GameImage.getInstance();
    }

    public void setTetrisController(TetrisController tetrisController) {
        this.tetrisController = tetrisController;
    }

    public void paint(Graphics g) {
        paintBackground(g);
        paintBoard(g);
        if (!tetrisController.isLose()) {
            paintTetromino(g);
            paintWall(g);
        }
        paintScore(g);
    }

    public void paintBackground(Graphics g) {
        g.drawImage(gameImage.getImage("Tetris Background"), 0, 0, 550, 830, null);
        g.translate(30, 30);
        Color line = new Color(20, 52, 64);
        g.setColor(line);
    }

    public void paintBoard(Graphics g) {
        g.drawImage(gameImage.getImage("Transparent Board"), 0, 0, GameConstant.MAX_COL * GameConstant.BLOCKSIZE, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE, null);
        g.drawLine(0, 0, GameConstant.BLOCKSIZE * GameConstant.MAX_COL, 0);
        g.drawLine(0, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE, GameConstant.BLOCKSIZE * GameConstant.MAX_COL, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE);
        g.drawLine(GameConstant.MAX_COL * GameConstant.BLOCKSIZE, 0, GameConstant.MAX_COL * GameConstant.BLOCKSIZE, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE);
        g.drawLine(0, 0, 0, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE);
        g.setColor(new Color(102,102,102));
        for (int i = 0; i <= GameConstant.MAX_ROW; ++i)
            g.drawLine(0, i * GameConstant.BLOCKSIZE, GameConstant.BLOCKSIZE * GameConstant.MAX_COL, i * GameConstant.BLOCKSIZE);
        for (int i = 0; i <= GameConstant.MAX_COL; ++i)
            g.drawLine(i * GameConstant.BLOCKSIZE, 0, i * GameConstant.BLOCKSIZE, GameConstant.BLOCKSIZE * GameConstant.MAX_ROW);
        if (tetrisController.isLose()) {
            g.drawImage(gameImage.getImage("Game Over"), 0, 0, GameConstant.MAX_COL * GameConstant.BLOCKSIZE, GameConstant.MAX_ROW * GameConstant.BLOCKSIZE, null);
        }
    }

    public void paintTetromino(Graphics g) {
        Cell[] cells = tetrisController.getTetromino().getCell();
        for (int i = 0; i < cells.length; ++i) {
            Cell c = cells[i];
            int x = c.getCol() * GameConstant.BLOCKSIZE;
            int y = c.getRow() * GameConstant.BLOCKSIZE;
            g.drawImage(c.getImage(), x, y, null);
        }
        Cell[] nextcells = tetrisController.getNextTetromino().getCell();
        for (int i = 0; i < nextcells.length; ++i) {
            Cell c = nextcells[i];
            int x = c.getCol() * GameConstant.BLOCKSIZE;
            int y = c.getRow() * GameConstant.BLOCKSIZE;
            g.drawImage(c.getImage(), 255+x, 340+y, null);
        }
    }

    private void paintWall(Graphics g) {
        Cell[][] wall = tetrisController.getWall();
        for (int i = 0; i < wall.length; ++i) {
            Cell[] line = wall[i];
            for (int j = 0; j < line.length; ++j) {
                Cell cell = line[j];
                int x = j * GameConstant.BLOCKSIZE;
                int y = i * GameConstant.BLOCKSIZE;
                if (cell != null)
                    g.drawImage(cell.getImage(), x - 1, y - 1, null);
            }
        }
    }

    public void paintScore(Graphics g) {
        g.drawImage(gameImage.getImage("Wooden Bar"), 320, 0, null);
        g.setFont(new Font("#9Slide05 Phobia", Font.BOLD, 40));
        String str = "Score: " + tetrisController.getScore();
        g.setColor(new Color(20, 52, 64));
        g.drawString(str, 416 - (str.length() * 8), 122);

        g.drawImage(gameImage.getImage("Wooden Bar"), 320, 150, null);
        String strlv = "Level: " + tetrisController.getLevel();
        g.drawString(strlv, 416 - (strlv.length() * 8), 276);
    }

//    public void repaint(){
//        repaint();
//    }

}
