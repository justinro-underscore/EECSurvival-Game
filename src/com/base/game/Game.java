package com.base.game;

import com.base.engine.*;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.interfaces.MainMenu;
import com.base.game.interfaces.PauseMenu;
import com.base.game.interfaces.UI;
import com.base.game.levels.*;
import jdk.internal.util.xml.impl.Input;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Game {
    public static Game game;

    private MainMenu mainMenu;
    private PauseMenu pauseMenu;

    private LevelManager levelManager;

    public enum State {
        MAIN_MENU, GAME, PAUSE_MENU;
    }

    private static State state = State.MAIN_MENU;

    public Game() {
        mainMenu = new MainMenu();
        mainMenu.init("res/assets/parchment.png");
        pauseMenu = new PauseMenu();
        pauseMenu.init("res/assets/bricks.jpg");

        levelManager = new LevelManager();
    }

    public void update() {
        getInput();

        //TODO: add currEvent to be rendered and updates
        //TODO: make level manager extend interface -> change currInterface to one of these
        switch (state) {
            case MAIN_MENU:
                mainMenu.update();
                mainMenu.render();
                break;
            case GAME:
                levelManager.update();
                levelManager.render();
                break;
            case PAUSE_MENU:
                pauseMenu.update();
                pauseMenu.render();
                break;
        }
    }

    public void render() {}

    public void getInput() {
        if ( InputHandler.isKeyDown(GLFW_KEY_ESCAPE) && state == State.GAME ) {
            state = State.PAUSE_MENU;
            LevelManager.pause();
        }
        //Resume the game after clicking escape to resume the game
        else if ( InputHandler.isKeyDown(GLFW_KEY_ESCAPE) && state == State.PAUSE_MENU) {
            LevelManager.resume();
            start();
        }
    }

    public Level getCurrLevel() {
        return levelManager.getCurrLevel();
    }

    /**
     * start the game
     */
    public static void start() {
        state = State.GAME;
    }
}
