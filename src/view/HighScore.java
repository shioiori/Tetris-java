package view;

import controller.Tetris;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class HighScore extends JPanel {
    static public File fileScore = new File("HighScore.txt");
    static public int scr[] = {0, 0, 0, 0, 0};

    public void paint(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintHighScore(g);
    }

    public void paintBackground(Graphics g){
        g.drawImage(Tetris.bgpanel, 0,0, 550,830, null);
    }

    public void paintHighScore(Graphics g){
        g.setFont(new Font("Noto Sans", Font.BOLD, 40));
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        try {
            fileInputStream = new FileInputStream(fileScore);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            g.drawString("Top High Score", 100, 90);
            for (int i = 1; i <= 5; ++i) {
                g.drawString("No." + i + ": " + bufferedReader.readLine(), 100, 100 + i * 80);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public HighScore(){
        repaint();
    }
}
