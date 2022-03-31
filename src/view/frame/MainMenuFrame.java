package view.frame;

import constant.GameConstant;
import model.GameImage;
import model.LoadData;
import view.panel.MainMenuPanel;

import javax.swing.*;

public class MainMenuFrame extends JFrame {
    public static MainMenuFrame instance = null;

    public static MainMenuFrame getInstance(){
        if (instance == null) instance = new MainMenuFrame();
        return instance;
    }

    MainMenuPanel mainMenuPanel;

    public MainMenuFrame(){
        GameImage.getInstance().setImageMap(LoadData.getInstance().loadData());
        mainMenuPanel = new MainMenuPanel();
        this.add(mainMenuPanel);
        this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void changePanel(JPanel jPanel){
        this.getContentPane().removeAll();
        this.getContentPane().add(jPanel);
        this.revalidate();
        repaint();
    }
}
