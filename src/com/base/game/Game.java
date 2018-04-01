package com.base.game;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Sprite;
import com.base.game.gameobject.character.Player;

import java.util.ArrayList;

public class Game {
    public static Game game;
    private ArrayList<GameObject> gameObj;
    private ArrayList<GameObject> toAdd;
    private ArrayList<GameObject> toRemove;

    public Game() {
        gameObj = new ArrayList<GameObject>();
        toAdd = new ArrayList<GameObject>();
        toRemove = new ArrayList<GameObject>();
        Sprite playerSprite = new Sprite(0.0f, 0.0f, 1.0f, 30, 30);
        Player player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 30, 30, playerSprite,100, 5, 4);
        addObj(player);
    }

    public void update() {
        gameObj.forEach((obj) -> {
            obj.update();
        });
        if(!toAdd.isEmpty())
        {
            while(!toAdd.isEmpty())
                gameObj.add(toAdd.remove(0));
        }
        if(!toRemove.isEmpty())
        {
            while(!toRemove.isEmpty())
                gameObj.remove(toRemove.remove(0));
        }
    }

    public void render() {
        gameObj.forEach((obj) -> {
            obj.render();
        });
    }

    public void addObj(GameObject obj) {
        toAdd.add(obj);
    }

    public void removeObj(GameObject obj) {
        toRemove.add(obj);
    }
}
