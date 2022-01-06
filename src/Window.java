import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.TimerTask;
import java.util.Timer;

public class Window {
    public int width = 550, height = 830;
    public JFrame window;
    private Tetris tetris;
    public Window(){
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        tetris = new Tetris();
        window.add(tetris);
        window.setVisible(true);
        tetris.action();
    }
}