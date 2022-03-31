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
        this.setVisible(true);
        this.setSize(GameConstant.WIDTH, GameConstant.HEIGHT);
    }
}
