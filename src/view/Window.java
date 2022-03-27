package view;
import javax.swing.*;

import controller.Tetris;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.Timer;

public class Window {
    public int width = 550, height = 830;
    public JFrame window;
    private Tetris tetris;
    public Window(JFrame mainmenu){
        window = new JFrame("Tetris");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
//        window.setLocationRelativeTo(null);
        window.setLocation(650, 100);
        tetris = new Tetris(mainmenu,window);
        window.add(tetris);
        window.setVisible(true);
        tetris.action();
    }
}