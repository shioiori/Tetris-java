package view;


import model.GameImage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HighScoreBoard {
    public int width = 550, height = 830;
    JFrame highScoreBoard;
    public HighScoreBoard(JFrame mainmenu) {
        highScoreBoard = new JFrame("High Score Board");
        highScoreBoard.setSize(width, height);
        highScoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        highScoreBoard.setResizable(false);
        highScoreBoard.setLocation(650, 100);
        HighScore highScore = new HighScore();
        highScoreBoard.setLayout(new BorderLayout());
        highScoreBoard.add(highScore, BorderLayout.CENTER);
        highScoreBoard.setVisible(true);
        
        JButton undo = new JButton("RETURN");
        undo.setPreferredSize(new Dimension(200, 50));
        undo.setFont(new Font("Noto Sans", Font.BOLD, 32));
        undo.setBorder(new roundBtnBorder(25));
        undo.setFocusable(false);
        JPanel flowPanel = new JPanel(){
        	 @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(GameImage.getInstance().getImage("Mainmenu Background"), 0, 0, null);
             }
        };        
        flowPanel.add(undo);
        
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainmenu.setVisible(true);
                highScoreBoard.setVisible(false);
                highScoreBoard.dispose();
            }
        });
        highScoreBoard.add(flowPanel, BorderLayout.NORTH);
        
    }
}
