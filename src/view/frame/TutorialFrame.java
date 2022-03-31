package view.frame;

import constant.GameConstant;

import javax.swing.*;

public class TutorialFrame extends JFrame {

    public TutorialFrame(){
        this.setTitle("Tutorial");
        this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(650, 100);
        this.setVisible(true);


    }
}
