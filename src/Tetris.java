import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends JPanel {
    // 21 35
   public final static int blockSize = 28, max_col = 11, max_row = 26;
   public static Image I, J, L, O, S, T, Z, gameover, bgfull, bgmini, flowerframe;
   private Tetromino tetromino;
   private Cell[][] wall = new Cell[50][50];
   public int score = 0, level = 1;
   static public boolean lose = false;
   static {
       try {
           I = ImageIO.read(Tetris.class.getResource("/resources/I.png"));
           J = ImageIO.read(Tetris.class.getResource("/resources/J.png"));
           L = ImageIO.read(Tetris.class.getResource("/resources/L.png"));
           O = ImageIO.read(Tetris.class.getResource("/resources/O.png"));
           S = ImageIO.read(Tetris.class.getResource("/resources/S.png"));
           T = ImageIO.read(Tetris.class.getResource("/resources/T.png"));
           Z = ImageIO.read(Tetris.class.getResource("/resources/Z.png"));
           gameover = ImageIO.read(Tetris.class.getResource("/resources/gameoverlight.png"));
           bgfull = ImageIO.read(Tetris.class.getResource("/resources/bg_flower.jpg"));
           bgmini = ImageIO.read(Tetris.class.getResource("/resources/bg_mini.jpg"));
           flowerframe = ImageIO.read(Tetris.class.getResource("/resources/wooden_bar.png"));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void action(){
        newGameStart();
        KeyAdapter l = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_F:
                        if (lose) newGameStart();
                    case KeyEvent.VK_UP:
                        rotateAction();
                        break;
                    case KeyEvent.VK_DOWN:
                        softDropAction();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeftAction();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightAction();
                        break;
                    case KeyEvent.VK_SPACE:
                        hardDropAction();
                        break;
                }
                if (!lose) repaint();
            }
        };
        this.requestFocus();
        this.addKeyListener(l);
    }


    public void paint(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        if (!lose) {
            paintTetromino(g);
            paintWall(g);
        }
        paintScore(g);
    }

    public void paintBackground(Graphics g){
        g.drawImage(bgfull, 0,0, 550,830, null);
        g.translate(30,30);
        Color line = new Color(20,52,64);
        g.setColor(line);
//        for (int i = 0; i <= max_row; ++i)
//            g.drawLine(0, i * blockSize, blockSize * max_col, i * blockSize);
//        for (int i = 0; i <= max_col; ++i)
//            g.drawLine(i * blockSize, 0, i * blockSize, blockSize * max_row);
        g.drawImage(bgmini, 0,0, max_col * blockSize,max_row * blockSize, null);
        g.drawLine(0, 0, blockSize * max_col, 0);
        g.drawLine(0, max_row * blockSize, blockSize * max_col, max_row * blockSize);
        g.drawLine(max_col * blockSize, 0, max_col * blockSize, max_row * blockSize);
        g.drawLine(0, 0, 0, max_row * blockSize);
        if (lose) {
            g.drawImage(gameover,0,0,max_col * blockSize,max_row * blockSize,null);
            return;
        }
    }

    public void paintTetromino(Graphics g){
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i){
            Cell c = cells[i];
            int x = c.getCol() * blockSize;
            int y = c.getRow() * blockSize;
            g.drawImage(c.getImage(), x, y, null);
        }
   }

   private void paintWall(Graphics g){
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

   public void paintScore(Graphics g){
       g.drawImage(flowerframe, 320,0, null);
       g.setFont(new Font("#9Slide05 Phobia", Font.BOLD, 40));
       String str = "Score: " + Integer.toString(score);
       g.setColor(new Color(20,52,64));
       g.drawString(str, 416 - (str.length() * 8), 122);

       g.drawImage(flowerframe, 320,150, null);
       String strlv = "Level: " + Integer.toString(level);
       g.drawString(strlv, 416 - (strlv.length() * 8), 276);
   }

   public void softDropAction(){
        if (canDrop())
            tetromino.softDrop();
        else {
            landToWall();
            destroyLine();
            gameOver();
            tetromino = Tetromino.randomTetromino();
        }
   }

   public void hardDropAction(){
        while (canDrop()) tetromino.softDrop();
        landToWall();
        destroyLine();
        gameOver();
        tetromino = Tetromino.randomTetromino();
   }

    public void rotateAction(){
        tetromino.rotateRight();
        if (outOfBound() || coincide()) tetromino.rotateLeft();
    }

   public void moveLeftAction(){
       if (coincide()) return;
       Cell[] cells = tetromino.getCell();
       for (int i = 0; i < cells.length; ++i){
           int row = cells[i].getRow();
           int col = cells[i].getCol();
           if (col >= max_col || col - 1 < 0 || (col - 1 >= 0 && wall[row][col-1] != null)) return;
       }
       tetromino.moveLeft();
   }

   public void moveRightAction(){
       Cell[] cells = tetromino.getCell();
       for (int i = 0; i < cells.length; ++i){
           int row = cells[i].getRow();
           int col = cells[i].getCol();
           if (col+1 >= max_col || col < 0 || (col + 1 < max_col && wall[row][col+1] != null)) return;
       }
       tetromino.moveRight();
   }

   public boolean fullCell(int row){
       for (int i = 0; i < max_col; ++i)
           if (wall[row][i] == null) return false;
       return true;
   }

   private final static int[] lineScore = {0, 10, 25, 50, 100};

   public void destroyLine(){
        Cell[][] w = new Cell[50][50];
        int line = 0;
        int k = max_row - 1;
        for (int i = max_row - 1; i >= 0; --i) {
            while (i >= 0 && fullCell(i)) {
                i--;
                line++;
            }
            w[k] = wall[i];
            k--;
        }
        wall = w;
        score += lineScore[line];
        level = score / 100 + 1;
    }

   public boolean canDrop(){
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i)
            if (cells[i].getRow() == max_row - 1) return false;
        for (int i = 0; i < cells.length; ++i){
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (wall[row+1][col] != null) return false;
        }
        return true;
   }

   public boolean outOfBound(){
       Cell[] cells = tetromino.getCell();
       for (int i = 0; i < cells.length; ++i){
           int col = cells[i].getCol();
           if (col >= max_col || col < 0) return true;
       }
       return false;
   }

   public boolean coincide(){
        Cell[] cells = tetromino.getCell();
        for (int i = 0; i < cells.length; ++i){
            int row = cells[i].getRow();
            int col = cells[i].getCol();
            if (row >= max_row || col >= max_col || row < 0 || col < 0 || wall[row][col] != null) return true;
        }
        return false;
   }

   public void landToWall(){
       Cell[] cells = tetromino.getCell();
       for (int i = 0; i < cells.length; ++i){
           int row = cells[i].getRow();
           int col = cells[i].getCol();
           wall[row][col] = cells[i];
       }
   }

   public void gameOver(){
        for (int i = 0; i < wall[0].length; ++i)
            if (wall[0][i] != null)
                if (wall[0][i].getRow() <= 0) lose = true;
   }

   public void clearWall(){
       for (int i = 0; i < wall.length; ++i)
           Arrays.fill(wall[i], null);
   }

   public void newGameStart(){
        clearWall();
        lose = false;
        score = 0;
        tetromino = Tetromino.randomTetromino();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
           @Override
           public void run() {
               int numDrop = level;
               while (numDrop > 0){
                   softDropAction();
                   numDrop--;
               }
               repaint();
           }
       }, 600, 600);
   }
}
