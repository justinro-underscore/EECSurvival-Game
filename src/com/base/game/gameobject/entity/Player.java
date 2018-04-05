package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private int textureID;
    private Delay attackDelay;

    public Player(float xPos, float yPos, Sprite sprite, String imgPath, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, imgPath, health, attackDamage, attackSpeed);

        attackDelay = new Delay(500);
        attackDelay.restart();
    }

    public void update() {
        checkCharacterCollision();
        checkDeath();

        if(health <= 0)
        {
            System.out.println("YOU'RE DEAD");
        }

        getInput();
    }

    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += 4f;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= 4f;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += 4f;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -= 4f;
        }

        if(InputHandler.isKeyDown(GLFW_KEY_SPACE) && attackDelay.isOver()) {
            createProjectile();
        }
    }

    private void createProjectile() {
            int proWidth = 5;
            int proHeight = 5;

            Sprite spr = new Sprite(1.0f, 0.0f, 0.0f, proWidth, proHeight);
            StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, spr, "", new Vector2f(0, 1), 5, 8);

            Game.game.addObj(pro);
            attackDelay.start();
    }

    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        if(obj instanceof Boss)
        {
            health --;
            if(health < 0)
                health = 0;
        }
    }

    protected void checkDeath() {
        if (isDead) {
            Game.game.gameOver();
        }
    }
}
