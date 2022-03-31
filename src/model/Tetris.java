package model;

import controller.TetrisController;

import java.util.Timer;

public class Tetris {
    public static Tetris instance = null;

    public static Tetris getInstance(){
        if (instance == null) instance = new Tetris();
        return instance;
    }

    public final static int[] lineScore = {0, 10, 25, 50, 100};

    private int score = 0;
    private int level = 1;
    private boolean pause = false;
    private boolean lose = false;
    private Timer timer;
    private TetrisController tetrisController;

    public void setTetrisController(TetrisController tetrisController) {
        this.tetrisController = tetrisController;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public void gameOver() {
        Cell [][] wall = tetrisController.getWall();
        for (int i = 0; i < wall[0].length; ++i)
            if (wall[0][i] != null) lose = true;
//                if (wall[0][i].getRow() <= 0) {
//                    lose = true;
//                }
    }
}
