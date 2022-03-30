package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    Clip clip;
    String CLEAR_VOICE = "F:\\Java Basic\\tetris-clone\\src\\resources\\Audio\\clear.wav";
    String DROP_VOICE = "F:\\Java Basic\\tetris-clone\\src\\resources\\Audio\\drop.wav";
    String GAMEOVER_VOICE = "F:\\Java Basic\\tetris-clone\\src\\resources\\Audio\\gameover.wav";
    AudioInputStream audioInputStream;
    public void setClipOpen(String PATH_NAME) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        audioInputStream = AudioSystem.getAudioInputStream(new File(PATH_NAME).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void clearVoice(){
        try {
            setClipOpen(CLEAR_VOICE);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dropVoice(){
        try {
            setClipOpen(DROP_VOICE);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void gameOverVoice(){
        try {
            setClipOpen(GAMEOVER_VOICE);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
