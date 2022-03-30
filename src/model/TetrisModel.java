package model;

import controller.Tetris;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TetrisModel {
    public final static int BLOCK_SIZE = 28, MAX_COL = 11, MAX_ROW = 26;
    public static Image I, J, L, O, S, T, Z, gameover, bgfull, bgmini, flowerframe, bgpanel;
    public static int score = 0, level = 1;
    public static boolean lose = false;
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
            bgpanel = ImageIO.read(Tetris.class.getResource("/resources/bg_full.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
