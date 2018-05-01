package com.base.game;

import com.base.engine.*;
import com.base.game.interfaces.MainMenu;
import com.base.game.interfaces.PauseMenu;
import com.base.game.levels.*;
import com.base.game.utilities.Delay;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Game {
    public static Game game;

    private MainMenu mainMenu;
    private PauseMenu pauseMenu;

    private static LevelManager levelManager;

    private Delay buttonDelay;

    public enum State {
        MAIN_MENU, GAME, PAUSE_MENU
    }

    private static State state = State.MAIN_MENU;

    public Game() {
        mainMenu = new MainMenu();
        mainMenu.init("res/assets/parchment.png");
        pauseMenu = new PauseMenu();
        pauseMenu.init("res/assets/bricks.jpg");

        levelManager = new LevelManager();

        buttonDelay = new Delay(100);
        buttonDelay.restart();
    }

    public void run() {
        getInput();

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

    //TODO: remove this and replace with register callback so we don't need delay or getInput
    public void getInput() {
        if ( InputHandler.isKeyDown(GLFW_KEY_ESCAPE) && state == State.GAME && buttonDelay.isOver() ) {
            state = State.PAUSE_MENU;
            LevelManager.pause();
            buttonDelay.start();
        }
        //Resume the game after clicking escape to resume the game
        else if ( InputHandler.isKeyDown(GLFW_KEY_ESCAPE) && state == State.PAUSE_MENU && buttonDelay.isOver()) {
            LevelManager.resume();
            start();
            buttonDelay.start();
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
}
