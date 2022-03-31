package controller;

import constant.GameConstant;
import model.*;
import view.panel.GamePanel;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisController {
    public static TetrisController instance = null;

    public static TetrisController getInstance(){
        if (instance == null) instance = new TetrisController();
        return instance;
    }
    GamePanel gamePanel;
    private Cell[][] wall = new Cell[50][50];
    private Tetromino tetromino;
    private Tetromino nextTetromino;
//    private Painting painting;
    private AudioPlayer audioPlayer;
    private Timer timer;
    private Tetris tetris;
    
    public TetrisController(){
        audioPlayer = new AudioPlayer();
        timer = new Timer();
        tetris = Tetris.getInstance();
        tetris.setTetrisController(this);
        gamePanel = GamePanel.getInstance();
        //painting = Painting.getInstance();
        //painting.setTetrisController(this);
    }


    public int getScore() {
        return tetris.getScore();
    }

    public void setScore(int score) {
        tetris.setScore(score);
    }

    public int getLevel() {
        return tetris.getLevel();
    }

    public void setLevel(int level) {
        tetris.setLevel(level);
    }

    public boolean isPause() {
        return tetris.isPause();
    }

    public void setPause(boolean pause) {
        tetris.setPause(pause);
    }

    public boolean isLose() {
        return tetris.isLose();
    }

    public void setLose(boolean lose) {
        tetris.setLose(lose);
    }

    public Cell[][] getWall() {
        return wall;
    }

    public void setWall(Cell[][] wall) {
        this.wall = wall;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public void softDropAction() {
        if (canDrop())
            tetromino.softDrop();
        else {
            landToWall();
            destroyLine();
            tetris.gameOver();
            tetromino = nextTetromino;
            nextTetromino = Tetromino.randomTetromino();
        }
    }

    public void hardDropAction() {
        try {
            audioPlayer.dropVoice();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        while (canDrop()) tetromino.softDrop();
        landToWall();
        destroyLine();
        tetris.gameOver();
        tetromino = nextTetromino;
        nextTetromino = Tetromino.randomTetromino();
    }

    public void rotateAction() {
        tetromino.rotateRight();
        if (outOfBound() || coincide()) tetromino.rotateLeft();
    }

    public void moveLeftAction() {
        if (coincide()) return;
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (col >= GameConstant.MAX_COL || col - 1 < 0 || wall[row][col - 1] != null) return;
        }
        tetromino.moveLeft();
    }

    public void moveRightAction() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (col + 1 >= GameConstant.MAX_COL || col < 0 || wall[row][col + 1] != null) return;
        }
        tetromino.moveRight();
    }

    public boolean fullCell(int row) {
        for (int i = 0; i < GameConstant.MAX_COL; ++i)
            if (wall[row][i] == null) return false;
        try {
            audioPlayer.clearVoice();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void destroyLine() {
        Cell[][] w = new Cell[50][50];
        int line = 0;
        int k = GameConstant.MAX_ROW - 1;
        for (int i = GameConstant.MAX_ROW - 1; i >= 0; --i) {
            while (i >= 0 && fullCell(i)) {
                i--;
                line++;
            }
            w[k] = wall[i];
            k--;
        }
        wall = w;
        tetris.setScore(tetris.getScore() + tetris.lineScore[line]);
        tetris.setLevel(tetris.getScore() / 100 + 1);
    }

    public boolean canDrop() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i)
            if (cells[i].getRow() == GameConstant.MAX_ROW - 1) return false;
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (wall[row + 1][col] != null) return false;
        }
        return true;
    }

    public boolean outOfBound() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int col = cells[i].getCol();
            if (col >= GameConstant.MAX_COL || col < 0) return true;
        }
        return false;
    }

    public boolean coincide() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (row >= GameConstant.MAX_ROW || col >= GameConstant.MAX_COL || row < 0 || col < 0 || wall[row][col] != null) return true;
        }
        return false;
    }

    public void landToWall() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            wall[row][col] = cells[i];
        }
    }

    public void clearWall() {
        for (int i = 0; i < wall.length; ++i)
            Arrays.fill(wall[i], null);
    }

    public void newGameStart() {
        clearWall();
        tetris.setLose(false);
        tetris.setScore(0);
        tetris.setPause(false);
        tetromino = Tetromino.randomTetromino();
        nextTetromino = Tetromino.randomTetromino();
        continueAction();
    }

    public void updateHighScore(int value){
        LoadData.getInstance().updateHighScore(value);
    }

    public void pauseAction(){
        timer.cancel();
        tetris.setPause(true);
        //painting.epaint();
    }

    public void continueAction(){
        timer = new Timer();
        tetris.setPause(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int numDrop = tetris.getLevel();
                while (numDrop > 0){
                    softDropAction();
                    numDrop--;
                }
                //painting.repaint();
                gamePanel.repaint();
                if (isLose()) {
                    try {
                        audioPlayer.gameOverVoice();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    timer.cancel();
                }
            }
        }, 600, 600);
    }

}
