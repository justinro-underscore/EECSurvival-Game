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

    public Game() {
        mainMenu = new MainMenu();
        mainMenu.init("res/assets/parchment.png");
        pauseMenu = new OptionMenu();
        pauseMenu.init("res/assets/parchment.png");

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

    public void run() {
        switch (state) {
            case MAIN_MENU:
                mainMenu.update();
                mainMenu.render();
                break;
            case GAME:
                if (!levelManager.hasLoadedLevels()) {
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

    public Level getCurrLevel() {
        return levelManager.getCurrLevel();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * start the game
     */
    public static void start() {
        state = State.GAME;
    }
    public static void backToMenu() {
        state = State.MAIN_MENU;
    }
}
