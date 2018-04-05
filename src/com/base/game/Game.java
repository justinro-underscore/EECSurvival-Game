package com.base.game;

import com.base.engine.*;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;
import com.base.game.utilities.Delay;
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
    private UI ui;

    private boolean levelOver;
    private boolean gameOver;
    private Delay thinkTime;
    private Sprite blackScreen;
    private Sprite gameOverScreen;
    private float endScreenTransparency;

    public Game() {
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();

        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "./res/player.png", 4f, 100, 5);

        boss = new Boss(Display.getWidth() / 2 - 35, Display.getHeight() - 70, 70, 70, "", 2f,400, 5);

        addObj(player);
        addObj(boss);

        ui = new UI(100);

        levelOver = false;
        gameOver = false;
        thinkTime = new Delay(500);
        blackScreen = new Sprite(Display.getWidth(), Display.getHeight(), "./res/black.png");
        gameOverScreen = new Sprite(Display.getWidth() / 2, Display.getHeight() / 2, "./res/GameOver.png");
        endScreenTransparency = 0;
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
            if (thinkTime.isOver()) {
                if (endScreenTransparency < 1)
                    endScreenTransparency += 0.005;

                if(endScreenTransparency < 0.75f)
                    blackScreen.setAlpha(endScreenTransparency);
                else
                    blackScreen.setAlpha(0.75f);
                blackScreen.render(0, 0);

                if (gameOver)
                    gameOver();
                else
                    nextLevel();
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
//        glBegin(GL11.GL_QUADS);
//            glVertex2f(field.x, field.y);
//            glVertex2f(field.x + field.width, field.y);
//            glVertex2f(field.x + field.width, field.y + field.height);
//            glVertex2f(field.x, field.y + field.height);
//        glEnd();
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
        if(lose)
        {
            gameOver = true;
            thinkTime.start();
        }
    }

    private void gameOver()
    {
        gameOverScreen.setAlpha(endScreenTransparency);
        gameOverScreen.render(Display.getWidth() / 4f, Display.getHeight() / 4f);
    }

    private void nextLevel()
    {
        // Go to next level
    }
}
