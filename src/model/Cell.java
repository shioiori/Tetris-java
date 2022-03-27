package model;
import java.awt.*;

public class Cell {
    private int row, col;
    private Image image;
    public Cell(){}

    public Cell(int row, int col, Image image){
        this.row = row;
        this.col = col;
        this.image = image;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Image getImage() {
        return image;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void moveRight(){
        col++;
    }

    public void moveLeft(){
        col--;
    }

    public void moveDown(){
        row++;
    }
}
