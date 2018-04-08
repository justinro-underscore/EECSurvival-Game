package com.base.game;

import com.base.engine.*;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.interfaces.UI;
import com.base.game.levels.BossLevel;
import com.base.game.levels.EmptyLevel;
import com.base.game.levels.Level;
import com.base.game.levels.LevelTransition;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static Game game;

    private ArrayList<Level> levels;
    private int currLevel;

    /**
     * Add the levels to the game
     */
    public Game() {
        currLevel = 0;
        levels = new ArrayList();

        EmptyLevel level1 = new EmptyLevel("./res/bricks.jpg");
      
        Boss boss = new Boss(Display.getWidth() / 2 - 35, Display.getHeight() - 150, 70, 70, "", 2f,67, 5);
        BossLevel level2 = new BossLevel("./res/bricks.jpg", boss);

        levels.add(level1);
        levels.add(level2);
    }

    /**
     * Get the current level
     * @return the current level
     */
    private Level getCurrLevel() {
        return levels.get(currLevel);
    }

    /**
     * Update the game
     */
    public void update() {
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
     * Increment the level
     */
    public void nextLevel() {
        if (currLevel < levels.size() - 1)
            currLevel++;
    }
}
