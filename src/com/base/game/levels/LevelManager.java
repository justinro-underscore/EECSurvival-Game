package com.base.game.levels;

import com.base.engine.Audio;
import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.test.TestLevel;

import java.util.ArrayList;

public class LevelManager {
    private boolean startedAudio;
    private static int startMusic;
    private Player player;

    private ArrayList<Level> levels;
    private int currLevel;
    private static boolean loadTestLevel = false;
    private static boolean isTestLevelAutomated = false;
    private static boolean hasLoadedLevels;

    /**
     * Add the levels to the game
     */
    public LevelManager() {
        startedAudio = false;
        startMusic = Audio.loadSound("res/audio/Fighting_is_not_an_option.ogg");
        levels = new ArrayList();

        hasLoadedLevels = false;
    }

    public void startAudio() {
        Audio.playBuffer(startMusic);
        Audio.loopBuffer(startMusic);

        startedAudio = true;
    }

    public static void pause() {
        Audio.pauseBuffer(startMusic);
    }

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
        if(currLevel == 1) // Only works if you are on the second level
            ((BossLevel)levels.get(1)).killBoss();
    }

    /**
     * Increment the level
     */
    public void nextLevel() {
        if (currLevel < levels.size() - 1)
            currLevel++;
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
        levels.clear();

        if (!loadTestLevel) {
            player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "res/assets/player.png", 4f, 20, 5);

            EmptyLevel level1 = new EmptyLevel("res/assets/levelBack.png", player, true);

            Boss boss = new Boss(Display.getWidth() / 2 - 35, Display.getHeight() - 150, 70, 70, "", 3f, 60, 8);
            BossLevel level2 = new BossLevel("res/assets/bossBack.png", boss, player, "res/scripts/cutsceneTest1.bsh");

            EmptyLevel endGame = new EmptyLevel("res/assets/thankYouForWatching.png", player, false);

            levels.add(level1);
            levels.add(level2);
            levels.add(endGame);
        } else {
            TestLevel testLevel = new TestLevel(isTestLevelAutomated);
            levels.add(testLevel);
        }

        hasLoadedLevels = true;
    }

    /**
     * Reloads game levels (restart)
     */
    public static void reLoadLevels() {
        hasLoadedLevels = false;
    }

    /**
     * Checks if levels have been reloaded
     * @return
     */
    public static boolean hasLoadedLevels() {
        return hasLoadedLevels;
    }
}
