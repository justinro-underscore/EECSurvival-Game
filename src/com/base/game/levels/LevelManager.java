package com.base.game.levels;

import com.base.engine.Audio;
import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.game.gameobject.entity.*;
import com.test.TestLevel;

import java.util.ArrayList;

public class LevelManager {
    private static boolean startedAudio;
    private static int startMusic;
    private Player player;

    private ArrayList<Level> levels;
    private int currLevel;
    private static boolean loadTestLevel = false;
    private static boolean isTestLevelAutomated = false;
    private static boolean hasLoadedLevels;
    private static boolean renderQuit;

    /**
     * Add the levels to the game
     */
    public LevelManager() {
        renderQuit = false;
        startedAudio = false;
        startMusic = Audio.loadSound("res/audio/Fighting_is_not_an_option.ogg");
        levels = new ArrayList();

        hasLoadedLevels = false;
    }

    public static void startAudio() {
        Audio.playBuffer(startMusic);
        Audio.loopBuffer(startMusic);

        startedAudio = true;
    }

    /**
     * Pause the level
     */
    public static void pause() {
        Audio.pauseBuffer(startMusic);
    }

    /**
     * Resume the level
     */
    public static void resume() {
        Audio.resumeBuffer(startMusic);
    }

    /**
     * Get the current level
     * @return the current level
     */
    public Level getCurrLevel() {
        return levels.get(currLevel);
    }

    /**
     * Update the game
     */
    public void update() {
        if (!startedAudio) {
            startAudio();
        }

        levels.get(currLevel).update();
    }

    /**
     * Render the game
     */
    public void render() {
        levels.get(currLevel).render();
    }

    /**
     * Add an object to the game
     * @param obj the object to be added
     */
    public void addObj(GameObject obj) {
        getCurrLevel().addObj(obj);
    }

    /**
     * End the level
     */
    public void endLevel() {
        getCurrLevel().endLevel();
    }

    /**
     * Get objects close to the object passed in
     * @param object the object to search around
     * @param range the range around the current object to search for close objects
     * @return return all of the objects close to the parameter object
     */
    public ArrayList<GameObject> getCloseObjects(GameObject object, float range) {
        return getCurrLevel().getCloseObjects(object, range);
    }

    /**
     * End the level
     * @param lose true if the player lose
     */
    public void levelOver(boolean lose) {
        getCurrLevel().levelOver(lose);
    }

    /**
     * Get the player's x-value
     * @return the player's x-value
     */
    public float getPlayerX() {
        return getCurrLevel().getPlayerX();
    }

    /**
     * Get the player's y-value
     * @return the player's y-value
     */
    public float getPlayerY() {
        return getCurrLevel().getPlayerY();
    }

    /**
     * Get the health of the specified player
     * @param isPlayer the specific player
     * @return the player's health
     */
    public int getHealth(boolean isPlayer)
    {
        return getCurrLevel().getHealth(isPlayer);
    }

    /**
     * Executes the cheat code
     * Can be changed to create different cheats
     */
    public void executeCheat()
    {
        if(getCurrLevel() instanceof BossLevel)
            ((BossLevel)getCurrLevel()).killBoss();
    }

    /**
     * Increment the level
     */
    public void nextLevel() {
        if (currLevel < levels.size() - 1) {
            currLevel++;
        }

        if (currLevel == levels.size() - 1) {
            renderQuit = true;
        }

        resume();
    }

    /**
     * Tells levelManager to load test level
     * @param isAutomated sets test level to either automated or manual
     */
    public static void loadTestLevel(boolean isAutomated) {
        loadTestLevel = true;
        isTestLevelAutomated = isAutomated;
    }

    /**
     * Tells levelManager to load the game
     */
    public static void loadGameLevel() {
        loadTestLevel = false;
    }

    /**
     * Loads levels into levelManager
     */
    public void loadLevels() {
        currLevel = 0;
        renderQuit = false;
        levels.clear();

        if (!Audio.isMuted())
            startAudio();

        if (!loadTestLevel) {
            player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 42, 88,  4f, 100, 5,"res/SpriteSheets/walkcyclevarious.png");

            EmptyLevel level1 = new EmptyLevel("res/assets/levelBack.png", 500, 500, player, true);

            Boss168 boss168 = new Boss168(Display.getWidth() / 2 - 35, Display.getHeight() - 290, 1, 170, 222, "res/assets/gibboWhite.png", 3f,60, 8);
            BossLevel level2 = new BossLevel("res/assets/classroom.png", 1280, 960, boss168, player, "res/scripts/eecs168Script.bsh");

            Boss268 boss268 = new Boss268(Display.getWidth() / 2 - 35, Display.getHeight() - 290, 1, 170, 222, "res/assets/gibboWhite.png", 3f,100, 8);
            BossLevel level3 = new BossLevel("res/assets/classroom.png", 1280, 960, boss268, player, "res/scripts/eecs268Script.bsh");

            Boss388 boss388 = new Boss388(Display.getWidth() / 2 - 35, Display.getHeight() - 275, 2, 159, 197, "res/assets/minden.png", 3f,20, 8);
            BossLevel level4 = new BossLevel("res/assets/classroom.png", 1280, 960, boss388, player, "res/scripts/eecs388Script.bsh");

            Boss368 boss368 = new Boss368(Display.getWidth() / 2 - 35, Display.getHeight() - 275, 1, 190, 200, "res/assets/klineWhite.png", 3f,100, 8);
            BossLevel level5 = new BossLevel("res/assets/classroom.png", 1280, 960, boss368, player, "res/scripts/eecs368Script.bsh");

            Boss448 boss448 = new Boss448(Display.getWidth() / 2 - 35, Display.getHeight() - 280, 1, 170, 215, "res/assets/bardasWhite.png", 3f,100, 8);
            BossLevel level6 = new BossLevel("res/assets/classroom.png", 1280, 960, boss448, player, "res/scripts/eecs448Script.bsh");

            EmptyLevel endGame = new EmptyLevel("res/assets/diploma.png",1056, 816, player, false);

            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
            levels.add(level5);
            levels.add(level6);
            levels.add(endGame);
        } else {
            TestLevel testLevel = new TestLevel(isTestLevelAutomated);
            levels.add(testLevel);
        }

        hasLoadedLevels = true;
    }

    /**
     * Returns true if final level
     * @return returns true if time to render quit button
     */
    public static boolean isRenderQuit() {
        return renderQuit;
    }

    /**
     * Reloads game levels (restart)
     */
    public static void reLoadLevels() {
        hasLoadedLevels = false;
    }

    /**
     * Checks if levels have been reloaded
     * @return if the levels have loaded
     */
    public static boolean hasLoadedLevels() {
        return hasLoadedLevels;
    }
}
