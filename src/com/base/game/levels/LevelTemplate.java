package com.base.game.levels;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.object.Door;
import com.base.game.interfaces.UI;

import java.awt.*;
import java.util.ArrayList;

public abstract class LevelTemplate {
    private Sprite background;

    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> toAdd;
    private ArrayList<GameObject> toRemove;

    protected Player player;
    protected UI ui;

    private boolean levelOver;
    private boolean gameOver;
    private LevelTransition lvlTransition;

    public void init(String backgroundPath) {
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

        background = new Sprite(Display.getWidth(), Display.getHeight(), backgroundPath);

        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "./res/player.png", 4f, 20, 5);

        addObj(player);

        levelOver = false;
        gameOver = false;
        lvlTransition = new LevelTransition();
    }

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

    public void addObj(GameObject obj) {
        toAdd.add(obj);
    }

    public float getPlayerX() {
        return player.getX();
    }

    public float getPlayerY() {
        return player.getY();
    }

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

    public void levelOver(boolean lose)
    {
        levelOver = true;
        gameOver = lose;
        lvlTransition.init();
    }

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

    public void createDoor() {
        Door door = new Door(Display.getWidth() / 2.0f, Display.getHeight() - 100, 41, 84, "./res/player.png");

        addObj(door);
    }

}
