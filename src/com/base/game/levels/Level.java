package com.base.game.levels;

import com.base.engine.*;
import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;
import com.base.game.gameobject.entity.Boss;
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

    private GameButton quit;

    //List of Game Objects
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> toAdd;
    protected ArrayList<GameObject> toRemove;

    //The Player
    protected Player player;
    protected UI ui;
    private SpriteRetriever retriever;
    private boolean levelOver;
    private boolean gameOver;

    protected boolean stopSpawningConsumables;

    private LevelTransition lvlTransition;

    /**
     * Passes the background path with inheritance
     * @param backgroundPath Takes in the path that is available to the user
     * @param width width of img
     * @param height height of img
     * @param player player for level
     */
    public void init(String backgroundPath, int width, int height, Player player) {
        this.player = player;
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        //Creates a background
        background = new Animation(1,0,0, backgroundPath,width, height,Display.getWidth(),Display.getHeight());

        addObj(player);

        levelOver = false;
        gameOver = false;
        stopSpawningConsumables = false;
        lvlTransition = new LevelTransition();

        events = new ArrayList<>();

        quit = new GameButton((float)(Display.getWidth()/2 - 875), (float)(Display.getHeight()/2), 400, 80, "Menu",
                () -> {
                    InputHandler.clear();
                    Game.backToMenu();
                });
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

        if (LevelManager.isRenderQuit()) {
            quit.update();
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

        if(LevelManager.isRenderQuit()) {
            quit.render();
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
     * Remove the consumable item if it is present
     */
    protected void removeConsumableIfPresent()
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            if(gameObjects.get(i) instanceof ConsumableItem){
                toRemove.add(gameObjects.get(i));
                return;
            }
        }
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
        removeConsumableIfPresent();
        stopSpawningConsumables = true;
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
        stopSpawningConsumables = true;
        Game.game.getCurrLevel().getPlayer().setMaxHealth();
        createDoor();
    }

    /**
     * Creates a door at the end after defeating the boss.
     */
    public void createDoor() {
        Door door = new Door((Display.getWidth() / 2.0f) - 50, Display.getHeight() - 150, 148, 125, "res/assets/ExitDoor.png",1);

        addObj(door);
    }

    public Player getPlayer(){
        return player;
    }

    /**
     * If the level is a boss level, reset the UI
     */
    public void resetUI()
    {
        if(this instanceof BossLevel)
            this.resetUI();
    }

    public abstract Boss getBoss();
}
