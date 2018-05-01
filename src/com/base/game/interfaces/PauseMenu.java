package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.gameobject.button.GameButton;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class PauseMenu extends Interface {
    private GameButton startButton;
    private GameButton quitButton;
    // TODO: implement button delay of 10ms


    /**
     * Initialize the pause menu screen
     */
    @Override
    public void init(String fileName,int widthOfImage,int heightOfImage) {
        super.init(fileName,widthOfImage,heightOfImage);

        //Start Button
        startButton = new GameButton((float)(Display.getWidth()/2 - 450), (float)(Display.getHeight()/2 - 40), 299, 80,
                "res/assets/quit_combine.png", "res/assets/quit_combine.png", Display::start);
        //Quit Button
//        quitButton = new GameButton((float)(Display.getWidth()/2 + 50), (float)(Display.getHeight()/2 - 40), 400, 80,
//                "res/assets/quit_combine.png", "res/assets/quit_combine.png", Display::quit);
   }

    /**
     * Perform actions based off of the user clicking the buttons
     */
    public void update() {
        startButton.update();
        quitButton.update();
    }

    /**
     * Render the pause menu
     */
    @Override
    public void render() {
        super.render();

        startButton.render();
        quitButton.render();
    }
}

