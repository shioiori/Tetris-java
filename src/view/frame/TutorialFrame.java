package view.frame;

import constant.GameConstant;
import view.panel.TutorialPanel;

import javax.swing.*;

public class TutorialFrame extends JFrame {
    public static TutorialFrame instance = null;

    public static TutorialFrame getInstance(){
        if (instance == null) instance = new TutorialFrame();
        return instance;
    }

    private TutorialPanel tutorialPanel;

    public TutorialFrame(){
        this.setTitle("Tutorial");
        this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(650, 100);
        this.setVisible(true);

        tutorialPanel = new TutorialPanel();
        this.add(tutorialPanel);

    }
}
