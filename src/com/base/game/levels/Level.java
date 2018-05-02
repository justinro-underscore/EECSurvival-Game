package com.base.game.levels;

import com.base.engine.*;
import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.game.Game;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.gameobject.object.Door;
import com.base.game.interfaces.UI;
import com.base.game.scenes.Event;
import com.base.game.scenes.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Callable;

public abstract class Level {
    private Animation background;
    private ArrayList<Event> events;

    //List of Game Objects
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> toAdd;
    protected ArrayList<GameObject> toRemove;

    private ConsumableItem consumableItem;

    //The Player
    protected Player player;
    protected UI ui;
    private SpriteRetriever retriever;
    private boolean levelOver;
    private boolean gameOver;

    private LevelTransition lvlTransition;

    /**
     * Passes the background path with inheritance
     * @param backgroundPath Takes in the path that is available to the user
     */
    public void init(String backgroundPath, int width, int height, Player player) {
        this.player = player;
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        //Creates a background
        background = new Animation(1,0,0, backgroundPath,width, height,Display.getWidth(),Display.getHeight());

        //Creates the player and consumable item
        consumableItem = new ConsumableItem(Display.getWidth() - 50,0, 50, 50, "", 5000, 5);

        //Renders the two objects
        addObj(player);
        addObj(consumableItem);

        levelOver = false;
        gameOver = false;
        lvlTransition = new LevelTransition();

        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Updates the frames of the level
     */
    public void update() {
        if (levelOver) {
            return;
        }

        if (!events.isEmpty()) {
            events.get(0).exec();
            if (events.get(0).isOver()) {
                events.remove(0);
            }
        } else {
            for (GameObject object : gameObjects) {
                if (!object.isRemoved())
                    object.update();
                else
                    toRemove.add(object);
            }
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
     * Renders the objects in the level
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
     * Adds am object to the game
     * @param obj the object to add
     */
    public void addObj(GameObject obj) {
        toAdd.add(obj);
    }

    /**
     * Returns the players current health
     * @param isPlayer the player
     * @return the player's health
     */
    public int getHealth(boolean isPlayer)
    {
        return player.getHealth();
    }

    /**
     * Gets the player's x position
     * @return the x position
     */
    public float getPlayerX() {
        return player.getX();
    }

    /**
     * Gets the player's y position
     * @return the y position
     */
    public float getPlayerY() {
        return player.getY();
    }


    /**
     * Collision Detection
     * @param object The object that is being detected
     * @param range range from objects
     * @return the close objects surrounding the passed in object
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
     * Checks if the level is over
     * @param lose boolean value that checks if the level is over
     */
    public void levelOver(boolean lose)
    {
        levelOver = true;
        gameOver = lose;
        lvlTransition.init();
    }

    /**
     * Checks if its the end of the level
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
     * Creates a door at the end after defeating the boss.
     */
    public void createDoor() {
        Door door = new Door(Display.getWidth() / 2.0f, Display.getHeight() - 100, 60, 60, "res/assets/door.png",1);

        addObj(door);
    }

}
