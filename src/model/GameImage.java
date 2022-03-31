package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameImage {
    public static GameImage instance = null;

    public static GameImage getInstance(){
        if (instance == null) instance = new GameImage();
        return instance;
    }

    Map<String, Image> imageMap;

    public GameImage(){
        imageMap = new HashMap<String, Image>();
    }

    public void setImageMap(Map<String, Image> imageMap) {
        this.imageMap = imageMap;
    }

    public void add(String key, Image img){
        imageMap.put(key, img);
    }

    public Image getImage(String key){
        return imageMap.get(key);
    }

}
