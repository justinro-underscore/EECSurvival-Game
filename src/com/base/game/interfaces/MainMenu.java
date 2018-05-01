package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.gameobject.button.GameButton;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu extends Interface {
    private GameButton startButton;
    private GameButton quitButton;
    private int music;
    private boolean startedAudio;
    private Animation testAnimation;
    // TODO: implement button delay of 10ms

    @Override
    /**
     * Initialize the main menu screen
     */
    public void init(String fileName,int widthOfImage, int heightOfImage) {
        super.init(fileName,widthOfImage,heightOfImage);

        startedAudio = false;
        music = Audio.loadSound("res/audio/Ove_Melaa_Times.ogg");
        testAnimation = new Animation(1,0,0,fileName,60,60,Display.getWidth(),Display.getHeight());

        startButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 + 100), 299, 81, "res/assets/quit_combine.png", "res/assets/quit_combine.png", () -> {Display.start(); reset();});
       // quitButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 299, 81, "res/assets/quit_combine.png", "res/assets/quit_combine.png", Display::quit);
    }

    public void startAudio() {
        Audio.playBuffer(music);
        Audio.loopBuffer(music);

        startedAudio = true;
    }

    /**
     * Perform actions based off of the user clicking the buttons
     */
    public void update() {
        if (!startedAudio) {
            startAudio();
        }

        startButton.update();
        //quitButton.update();
    }

    @Override
    /**
     * Render the main menu
     */
    public void render() {

        super.render();
        startButton.render();
       // quitButton.render();
        //testAnimation.render(Display.getWidth()/2,Display.getHeight()/2);
    }

    public void reset() {
        startedAudio = false;
        Audio.stopBuffer(music);
    }
}
