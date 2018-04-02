package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private int timeSinceLastFire;

    public Player(float xPos, float yPos, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, health, attackDamage, attackSpeed);

        timeSinceLastFire = 0;
    }

    public void update() {
        getInput();

        if(timeSinceLastFire > 0)
            timeSinceLastFire--;
        getInput();
    }

    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += 3;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= 3;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += 3;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -= 3;
        }

        if(InputHandler.isKeyDown(GLFW_KEY_SPACE)) {
            createProjectile();
        }
    }

    private void createProjectile() {
        if(timeSinceLastFire == 0) {
            int proWidth = 5;
            int proHeight = 5;

            Sprite spr = new Sprite(1.0f, 0.0f, 0.0f, proWidth, proHeight);
            StandardProjectile pro = new StandardProjectile(xPos + (width / 2) - (proWidth / 2), yPos + height, spr, new GameVector(0, 1), 5, 8);

            Game.game.addObj(pro);
            timeSinceLastFire = 25;
        }
    }

    public void render() {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            sprite.render();
        }
        glPopMatrix();
    }
}
