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

    private float speed;

    public Player(float xPos, float yPos, int width, int height, String imgPath, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, width, height, imgPath, health, attackDamage, attackSpeed);

        speed = 4f;

        attackDelay = new Delay(500);
        attackDelay.restart();
    }

    public void update() {
        checkCharacterCollision();
        checkDeath();

        getInput();
    }

    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += speed;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= speed;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += speed;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -= speed;
        }

        if(InputHandler.isKeyDown(GLFW_KEY_SPACE) && attackDelay.isOver()) {
            createProjectile();
        }
    }

    private void createProjectile() {
        int proWidth = 5;
        int proHeight = 5;

        StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, proWidth, proHeight, "", new Vector2f(0, 1), 5, 8);

        Game.game.addObj(pro);
        attackDelay.start();
    }

    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        if(obj instanceof Boss)
        {
            loseHealth(1);
        }
    }

    protected void checkDeath() {
        if (isDead) {
            Game.game.levelOver(true);
        }
    }
}
