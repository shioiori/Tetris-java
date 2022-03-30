package controller;

import model.Cell;

import java.awt.*;

public class Painting{
    public void paint(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintBoard(g);
        if (!lose) {
            paintTetromino(g);
            paintWall(g);
        }
        paintScore(g);
    }

    public void paintBackground(Graphics g) {
        g.drawImage(bgfull, 0, 0, 550, 830, null);
        g.translate(30, 30);
        Color line = new Color(20, 52, 64);
        g.setColor(line);
    }

    public void paintBoard(Graphics g) {
        g.drawImage(bgmini, 0, 0, max_col * blockSize, max_row * blockSize, null);
        g.drawLine(0, 0, blockSize * max_col, 0);
        g.drawLine(0, max_row * blockSize, blockSize * max_col, max_row * blockSize);
        g.drawLine(max_col * blockSize, 0, max_col * blockSize, max_row * blockSize);
        g.drawLine(0, 0, 0, max_row * blockSize);
        g.setColor(new Color(102,102,102));
        for (int i = 0; i <= max_row; ++i)
            g.drawLine(0, i * blockSize, blockSize * max_col, i * blockSize);
        for (int i = 0; i <= max_col; ++i)
            g.drawLine(i * blockSize, 0, i * blockSize, blockSize * max_row);
        if (lose) {
            g.drawImage(gameover, 0, 0, max_col * blockSize, max_row * blockSize, null);
        }
    }

    public void paintTetromino(Graphics g) {
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i) {
            Cell c = cells[i];
            int x = c.getCol() * blockSize;
            int y = c.getRow() * blockSize;
            g.drawImage(c.getImage(), x, y, null);
        }
        Cell[] nextcells = nextTetromino.getCell();
        for (int i = 0; i < nextcells.length; ++i) {
            Cell c = nextcells[i];
            int x = c.getCol() * blockSize;
            int y = c.getRow() * blockSize;
            g.drawImage(c.getImage(), 255+x, 340+y, null);
        }
    }

    private void paintWall(Graphics g) {
        for (int i = 0; i < wall.length; ++i) {
            Cell[] line = wall[i];
            for (int j = 0; j < line.length; ++j) {
                Cell cell = line[j];
                int x = j * blockSize;
                int y = i * blockSize;
                if (cell != null)
                    g.drawImage(cell.getImage(), x - 1, y - 1, null);
            }
        }
    }

    public void paintScore(Graphics g) {
        g.drawImage(flowerframe, 320, 0, null);
        g.setFont(new Font("#9Slide05 Phobia", Font.BOLD, 40));
        String str = "Score: " + score;
        g.setColor(new Color(20, 52, 64));
        g.drawString(str, 416 - (str.length() * 8), 122);

        g.drawImage(flowerframe, 320, 150, null);
        String strlv = "Level: " + level;
        g.drawString(strlv, 416 - (strlv.length() * 8), 276);
    }
}
