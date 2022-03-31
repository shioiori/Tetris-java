package view.panel;

import listener.SwitchSceneListener;
import model.GameImage;
import view.roundBtnBorder;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JButton newGameBtn;
    private JButton tutorialBtn;
    private JButton highScoreBtn;
    private JButton exitBtn;
    private SwitchSceneListener switchSceneListener;

    public MainMenuPanel(){
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        this.add(Box.createVerticalStrut(200));

        newGameBtn = new JButton("New Game");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 30));
        newGameBtn.setFocusable(false);
        newGameBtn.setBorder(new roundBtnBorder(15));
        this.add(newGameBtn);
        newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(20));

        tutorialBtn = new JButton("Tutorial");
        tutorialBtn.setFont(new Font("Arial", Font.BOLD, 30));
        tutorialBtn.setFocusable(false);
        tutorialBtn.setBorder(new roundBtnBorder(15));
        this.add(tutorialBtn);
        tutorialBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(20));

        highScoreBtn = new JButton("High Score");
        highScoreBtn.setFont(new Font("Arial", Font.BOLD, 30));
        highScoreBtn.setFocusable(false);
        highScoreBtn.setBorder(new roundBtnBorder(15));
        this.add(highScoreBtn);
        highScoreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(20));

        exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.BOLD, 30));
        exitBtn.setFocusable(false);
        exitBtn.setBorder(new roundBtnBorder(15));
        this.add(exitBtn);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(20));

        switchSceneListener = new SwitchSceneListener();
        newGameBtn.addMouseListener(switchSceneListener);
        tutorialBtn.addMouseListener(switchSceneListener);
        highScoreBtn.addMouseListener(switchSceneListener);
        exitBtn.addMouseListener(switchSceneListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.getInstance().getImage("Mainmenu Background"), 0, 0, null);
    }
}
