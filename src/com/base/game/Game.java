package com.base.game;

import com.base.engine.*;
import com.base.game.interfaces.MainMenu;
import com.base.game.interfaces.OptionMenu;
import com.base.game.levels.*;
import com.base.game.utilities.Delay;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Game {
    public static Game game;

    private MainMenu mainMenu;
    private OptionMenu pauseMenu;

    private static LevelManager levelManager;

    public enum State {
        MAIN_MENU, GAME, PAUSE_MENU
    }

    private static State state = State.MAIN_MENU;

    /**
     * The overarching game
     */
    public Game() {
        mainMenu = new MainMenu();
        mainMenu.init("res/assets/parchment.png",640,480);
        pauseMenu = new OptionMenu();
        pauseMenu.init("res/assets/optionsScreen.png", 640,480);
        levelManager = new LevelManager();

        EventQueue.registerCallback(new Event("Keyboard", Integer.toString(GLFW_KEY_ESCAPE), false),
                () -> {
                    if (InputHandler.isKeyDown(GLFW_KEY_ESCAPE))
                        return;

                    if (state == State.GAME) {
                        state = State.PAUSE_MENU;
                        LevelManager.pause();
                    } else if (state == State.PAUSE_MENU) {
                        LevelManager.resume();
                        start();
                    }
                });
    }

    /**
     * Runs and manages game states
     */
    public void run() {
        switch (state) {
            case MAIN_MENU:
                mainMenu.update();
                mainMenu.render();
                break;
            case GAME:
                if (!LevelManager.hasLoadedLevels()) {
                    levelManager.loadLevels();
                }

                levelManager.update();
                levelManager.render();
                break;
            case PAUSE_MENU:
                pauseMenu.update();
                pauseMenu.render();
                break;
        }
    }

    /**
     * Returns current level of game
     * @return current level
     */
    public Level getCurrLevel() {
        return levelManager.getCurrLevel();
    }

    /**
     * Returns the levelManager for manipulation
     * @return levelManager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * start the game
     */
    public static void start() {
        state = State.GAME;
    }

    /**
     * Changes game state to main menu
     */
    public static void backToMenu() {
        LevelManager.reLoadLevels();
        state = State.MAIN_MENU;
    }
}
