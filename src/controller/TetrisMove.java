package controller;

import model.Cell;
import model.Tetromino;

public class TetrisMove {
    public void softDropAction() {
        if (canDrop())
            tetromino.softDrop();
        else {
            landToWall();
            destroyLine();
            gameOver();
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
        gameOver();
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
            if (col >= max_col || col - 1 < 0 || wall[row][col - 1] != null) return;
        }
        tetromino.moveLeft();
    }

    public void moveRightAction() {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (col + 1 >= max_col || col < 0 || wall[row][col + 1] != null) return;
        }
        tetromino.moveRight();
    }
}
