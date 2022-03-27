package model;
import java.util.Random;

import controller.Tetris;

public class Tetromino {

    protected Cell[] cell = new Cell[4];
    protected State[] state;

    public Cell[] getCell() {
        return cell;
    }

    public State[] getState() {
        return state;
    }

    public static Tetromino randomTetromino(){
        Random r = new Random();
        int type = r.nextInt(7);
        switch (type){
            case 0: return new I();
            case 1: return new J();
            case 2: return new L();
            case 3: return new O();
            case 4: return new S();
            case 5: return new T();
            case 6: return new Z();
        }
        return null;
    }

    public void softDrop(){
        for (int i = 0; i < cell.length; ++i)
            cell[i].moveDown();
    }

    private int index = 0;

    public void rotateRight(){
        index++;
        State s = state[index % state.length];
        Cell o = cell[0];
        cell[1].setRow(o.getRow() + s.row1);
        cell[1].setCol(o.getCol() + s.col1);
        cell[2].setRow(o.getRow() + s.row2);
        cell[2].setCol(o.getCol() + s.col2);
        cell[3].setRow(o.getRow() + s.row3);
        cell[3].setCol(o.getCol() + s.col3);
    }

    public void rotateLeft(){
        index += state.length;
        index--;
        State s = state[index % state.length];
        Cell o = cell[0];
        cell[1].setRow(o.getRow() + s.row1);
        cell[1].setCol(o.getCol() + s.col1);
        cell[2].setRow(o.getRow() + s.row2);
        cell[2].setCol(o.getCol() + s.col2);
        cell[3].setRow(o.getRow() + s.row3);
        cell[3].setCol(o.getCol() + s.col3);
    }

    public void moveLeft(){
        for (int i = 0; i < cell.length; ++i)
            cell[i].moveLeft();
    }

    public void moveRight(){
        for (int i = 0; i < cell.length; ++i)
            cell[i].moveRight();
    }

}

class I extends Tetromino{
    public I(){
        cell[0] = new Cell(0,4, Tetris.I);
        cell[1] = new Cell(0,5, Tetris.I);
        cell[2] = new Cell(0,6, Tetris.I);
        cell[3] = new Cell(0,7, Tetris.I);
        state = new State[]{
                new State(0,0,0,1,0,2,0,3),
                new State(0,0,1,0,2,0,3,0)
        };
    }
}

class J extends Tetromino{
    public J(){
        cell[0] = new Cell(1,4, Tetris.J);
        cell[1] = new Cell(1,5, Tetris.J);
        cell[2] = new Cell(1,6, Tetris.J);
        cell[3] = new Cell(0,4, Tetris.J);
        state = new State[]{
                new State(0,0,0,1,0,2,-1,0),
                new State(0,0,0,1,1,0,2,0),
                new State(0,0,0,1,0,2,1,2),
                new State(0,0,0,1,-1,1,-2,1)
        };
    }
}

class L extends Tetromino{
    public L(){
        cell[0] = new Cell(1,4, Tetris.L);
        cell[1] = new Cell(1,5, Tetris.L);
        cell[2] = new Cell(1,6, Tetris.L);
        cell[3] = new Cell(0,6, Tetris.L);
        state = new State[]{
                new State(0,0,0,1,0,2,-1,2),
                new State(0,0,0,1,-1,0,-2,0),
                new State(0,0,0,1,0,2,1,0),
                new State(0,0,0,1,1,1,2,1)
        };
    }
}

class O extends Tetromino{
    public O(){
        cell[0] = new Cell(0,5, Tetris.O);
        cell[1] = new Cell(0,6, Tetris.O);
        cell[2] = new Cell(1,5, Tetris.O);
        cell[3] = new Cell(1,6, Tetris.O);
        state = new State[]{
                new State(0,0,0,1,1,0,1,1)
        };
    }
}

class S extends Tetromino{
    public S(){
        cell[0] = new Cell(0,5, Tetris.S);
        cell[1] = new Cell(1,5, Tetris.S);
        cell[2] = new Cell(0,6, Tetris.S);
        cell[3] = new Cell(1,4, Tetris.S);
        state = new State[]{
                new State(0,0,1,0,0,1,1,-1),
                new State(0,0,1,0,1,1,2,1)
        };
    }
}

class T extends Tetromino{
    public T(){
        cell[0] = new Cell(1,4, Tetris.T);
        cell[1] = new Cell(1,5, Tetris.T);
        cell[2] = new Cell(1,6, Tetris.T);
        cell[3] = new Cell(0,5, Tetris.T);
        state = new State[]{
                new State(0,0,0,1,0,2,-1,1),
                new State(0,0,1,0,2,0,1,1),
                new State(0,0,0,1,0,2,1,1),
                new State(0,0,0,1,-1,1,1,1)
        };
    }
}

class Z extends Tetromino{
    public Z(){
        cell[0] = new Cell(0,4, Tetris.Z);
        cell[1] = new Cell(0,5, Tetris.Z);
        cell[2] = new Cell(1,5, Tetris.Z);
        cell[3] = new Cell(1,6, Tetris.Z);
        state = new State[]{
                new State(0,0,0,1,1,1,1,2),
                new State(0,0,-1,1,0,1,1,0)
        };
    }
}
