package com.base.game;

import com.base.engine.*;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.interfaces.UI;
import com.base.game.utilities.Delay;
import com.base.game.utilities.LevelTransition;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Game {
    public static Game game;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> toAdd;
    private ArrayList<GameObject> toRemove;

    private Player player;
    private Boss boss;
    private ConsumableItem consumableItem;
    private UI ui;

    private boolean levelOver;
    private boolean gameOver;
    private LevelTransition lvlTransition;

    public Game() {
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "./res/player.png", 4f, 20, 5);

        boss = new Boss(Display.getWidth() / 2 - 35, Display.getHeight() - 150, 70, 70, "", 2f,67, 5);

        consumableItem = new ConsumableItem(Display.getWidth() - 50,0, 50, 50, "", 5000, 5);

        addObj(player);
        addObj(boss);
        addObj(consumableItem);

        ui = new UI(player.getHealth(), boss.getHealth());

        levelOver = false;
        gameOver = false;
        lvlTransition = new LevelTransition();
    }

    public void update() {
        if(!levelOver) {
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
                for (GameObject object : toRemove) {
                    gameObjects.remove(object);
                }

                toRemove.clear();
            }
        }
    }

    public void render() {
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

    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
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
        // TODO Create door
    }
}
