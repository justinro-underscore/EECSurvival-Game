package com.base.game.levels;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.gameobject.object.Door;
import com.base.game.interfaces.UI;

import java.awt.*;
import java.util.ArrayList;

public abstract class LevelTemplate {
    private Sprite background;

    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> toAdd;
    private ArrayList<GameObject> toRemove;

    private ConsumableItem consumableItem;

    protected Player player;
    protected UI ui;

    private boolean levelOver;
    private boolean gameOver;
    private LevelTransition lvlTransition;

    /**
     * Template for each level. Defaults to createing a player and a consumable
     * @param backgroundPath the file path for the background image
     */
    public void init(String backgroundPath) {
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

        background = new Sprite(Display.getWidth(), Display.getHeight(), backgroundPath);

        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "./res/player.png", 4f, 20, 5);
        consumableItem = new ConsumableItem(Display.getWidth() - 50,0, 50, 50, "", 5000, 5);

        addObj(player);
        addObj(consumableItem);

        levelOver = false;
        gameOver = false;
        lvlTransition = new LevelTransition();
    }

    /**
     * The big kahuna update method, where all of the other updates are called
     */
    public void update() {
        if (levelOver) {
            return;
        }
        for (GameObject object : gameObjects) {
            if (!object.isRemoved())
                object.update();
            else
                toRemove.add(object);
        }
        if (ui != null) {
            ui.update();
        }

        if (!toAdd.isEmpty()) {
            while (!toAdd.isEmpty())
                gameObjects.add(toAdd.remove(0));
        }

        if (!toRemove.isEmpty()) {
            gameObjects.removeAll(toRemove);

            toRemove.clear();
        }
    }

    /**
     * Render the level and everything else on the screen
     */
    public void render() {
        background.render(0 ,0);

        gameObjects.forEach(GameObject::render);

        if (ui != null) { ui.render(); }

        if(levelOver)
        {
            if(lvlTransition != null && lvlTransition.render(gameOver))
            {
                lvlTransition = null;
                levelOver = false;
            }
        }
    }

    /**
     * Add objects to the game
     * @param obj the object to be added
     */
    public void addObj(GameObject obj) {
        toAdd.add(obj);
    }

    /**
     * Get the main player's x-position
     * @return the player's x-position
     */
    public float getPlayerX() {
        return player.getX();
    }

    /**
     * Get the main player's y-position
     * @return the player's y-position
     */
    public float getPlayerY() {
        return player.getY();
    }

    /**
     * Get the objects around another object
     * @param object the object we are looking around
     * @param range the range around the current object we look for for other objects
     * @return return the objects that are close to the parameter object
     */
    public ArrayList<GameObject> getCloseObjects(GameObject object, float range)
    {
        ArrayList<GameObject> closeObj = new ArrayList<>();
        float p1 = object.getX() - (object.getWidth()/2f) - range;
        float p2 = object.getY() - (object.getHeight()/2f) - range;
        Rectangle field = new Rectangle((int)p1, (int)p2, (int)(object.getWidth() + 2 * range), (int)(object.getHeight() + 2 * range));

        for(GameObject o : gameObjects)
        {
            if(!o.equals(object) && Physics.checkCollision(field, o))
                closeObj.add(o);
        }
        return closeObj;
    }

    /**
     * End the level
     * @param lose true if the player lost the level
     */
    public void levelOver(boolean lose)
    {
        levelOver = true;
        gameOver = lose;
        lvlTransition.init();
    }

    /**
     * End the level
     */
    public void endLevel()
    {
        boolean done = false;
        while(!done)
        {
            try {
                gameObjects.remove(1); // Get rid of everything besides the player
            }
            catch(IndexOutOfBoundsException e) {
                done = true;
            }
        }
        ui = null;
        createDoor();
    }

    /**
     * Create a door to the next level
     */
    public void createDoor() {
        Door door = new Door(Display.getWidth() / 2.0f, Display.getHeight() - 100, 41, 84, "./res/player.png");

        addObj(door);
    }

}
