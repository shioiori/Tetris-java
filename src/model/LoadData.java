package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static view.panel.HighScorePanel.scr;


public class LoadData {
    public static LoadData instance = null;

    public static LoadData getInstance(){
        if (instance == null) instance = new LoadData();
        return instance;
    }
    public static File fileScore = new File("HighScore.txt");

    public Map<String,Image> loadData(){
        Map<String, Image> imageMap = new HashMap<>();
        try {
            imageMap.put("I", ImageIO.read(getClass().getResource("../resources/I.png")));
            imageMap.put("J", ImageIO.read(getClass().getResource("../resources/J.png")));
            imageMap.put("L", ImageIO.read(getClass().getResource("../resources/L.png")));
            imageMap.put("O", ImageIO.read(getClass().getResource("../resources/O.png")));
            imageMap.put("S", ImageIO.read(getClass().getResource("../resources/S.png")));
            imageMap.put("T", ImageIO.read(getClass().getResource("../resources/T.png")));
            imageMap.put("Z", ImageIO.read(getClass().getResource("../resources/Z.png")));
            imageMap.put("I", ImageIO.read(getClass().getResource("../resources/I.png")));
            imageMap.put("Game Over", ImageIO.read(getClass().getResource("../resources/gameoverlight.png")));
            imageMap.put("Tetris Background", ImageIO.read(getClass().getResource("../resources/bg_flower.jpg")));
            imageMap.put("Transparent Board", ImageIO.read(getClass().getResource("../resources/bg_mini.jpg")));
            imageMap.put("Wooden Bar", ImageIO.read(getClass().getResource("../resources/wooden_bar.png")));
            imageMap.put("Mainmenu Background", ImageIO.read(getClass().getResource("../resources/bg_full.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageMap;
    }

    public int [] loadHighScore(){
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        int [] scr = new int[5];
        try {
            fileInputStream = new FileInputStream(fileScore);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            for (int i = 0; i < 5; ++i) {
                String line = bufferedReader.readLine();
                if (line != null) scr[i] = Integer.parseInt(line);
                else scr[i] = 0;
            }
            Arrays.sort(scr);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return scr;
    }

    public void updateHighScore(int value) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        try {
            fileInputStream = new FileInputStream(fileScore);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            for (int i = 0; i < 5; ++i) {
                String line = bufferedReader.readLine();
                if (line != null) scr[i] = Integer.parseInt(line);
                else scr[i] = 0;
            }
            Arrays.sort(scr);
            for (int i = 0; i < 5; ++i) {
                if (value > scr[i]) {
                    scr[i] = value;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter myWriter = null;
        try {
            Arrays.sort(scr);
            myWriter = new FileWriter(fileScore);
            for (int i = 4; i >= 0; --i) {
                myWriter.write(scr[i] + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                if (myWriter != null) myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
