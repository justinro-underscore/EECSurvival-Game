package com.base.game;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Player;

import java.util.ArrayList;

public class Game {
    public static Game game;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> toAdd;
    private ArrayList<GameObject> toRemove;

    public Game() {
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

        Sprite playerSprite = new Sprite(0.0f, 0.0f, 1.0f, 30, 30);
        Player player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, playerSprite, 5, 5, 5);

        addObj(player);
    }

    public void update() {
        for (GameObject object : gameObjects) {
            if (!object.isRemoved())
                object.update();
            else
                toRemove.add(object);
        }

        if(!toAdd.isEmpty())
        {
            while(!toAdd.isEmpty())
                gameObjects.add(toAdd.remove(0));
        }

        if (!toRemove.isEmpty()) {
            for (GameObject object : toRemove) {
                gameObjects.remove(object);
            }

            toRemove.clear();
        }
    }

    public void render() {
        gameObjects.forEach(GameObject::render);
    }

    public void addObj(GameObject obj) {
        toAdd.add(obj);
    }
}
