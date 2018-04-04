package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.interfaces.Game;
import com.base.game.gameobject.projectile.StandardProjectile;

import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private int textureID;
    private int timeSinceLastFire;

    public Player(float xPos, float yPos, Sprite sprite, String imgPath, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, imgPath, health, attackDamage, attackSpeed);

        timeSinceLastFire = 0;
    }

    public void update() {
        checkCharacterCollision();

        if(health <= 0)
        {
            System.out.println("YOU'RE DEAD");
        }

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
            StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, spr, "", new Vector2f(0, 1), 5, 8);

            Game.game.addObj(pro);
            timeSinceLastFire = 25;
        }
    }
}
