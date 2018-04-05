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

    private int konami; // Cheat code

    public Player(float xPos, float yPos, Sprite sprite, String imgPath, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, imgPath, health, attackDamage, attackSpeed);

        timeSinceLastFire = 0;
    }

    public void update() {
        checkCharacterCollision();
        checkDeath();

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

        enterCheatCode();
    }

    private void enterCheatCode()
    {


        if((konami == 0 || konami == 2) && InputHandler.isAnyKeyDown(GLFW_KEY_UP) ||
                (konami == 4 || konami == 6) && InputHandler.isAnyKeyDown(GLFW_KEY_DOWN) ||
                konami == 8 && InputHandler.isAnyKeyDown(GLFW_KEY_LEFT) ||
                konami == 10 && InputHandler.isAnyKeyDown(GLFW_KEY_RIGHT) ||
                konami == 12 && InputHandler.isAnyKeyDown(GLFW_KEY_LEFT) ||
                konami == 14 && InputHandler.isAnyKeyDown(GLFW_KEY_RIGHT) ||
                konami == 16 && InputHandler.isAnyKeyDown(GLFW_KEY_B) ||
                konami == 18 && InputHandler.isAnyKeyDown(GLFW_KEY_A))
            konami = 0;
        else if(konami == 0 && InputHandler.isKeyDown(GLFW_KEY_UP))
            konami++;
        else if(konami == 1 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 2 && InputHandler.isKeyDown(GLFW_KEY_UP))
            konami++;
        else if(konami == 3 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 4 && InputHandler.isKeyDown(GLFW_KEY_DOWN))
            konami++;
        else if(konami == 5 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 6 && InputHandler.isKeyDown(GLFW_KEY_DOWN))
            konami++;
        else if(konami == 7 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 8 && InputHandler.isKeyDown(GLFW_KEY_LEFT))
            konami++;
        else if(konami == 9 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 10 && InputHandler.isKeyDown(GLFW_KEY_RIGHT))
            konami++;
        else if(konami == 11 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 12 && InputHandler.isKeyDown(GLFW_KEY_LEFT))
            konami++;
        else if(konami == 13 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 14 && InputHandler.isKeyDown(GLFW_KEY_RIGHT))
            konami++;
        else if(konami == 15 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 16 && InputHandler.isKeyDown(GLFW_KEY_B))
            konami++;
        else if(konami == 17 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 18 && InputHandler.isKeyDown(GLFW_KEY_A)) {
            konami++;
            Game.game.executeCheat();
        }
    }

    private void createProjectile() {
        if(timeSinceLastFire == 0) {
            int proWidth = 5;
            int proHeight = 5;

            Sprite spr = new Sprite(1.0f, 0.0f, 0.0f, proWidth, proHeight);
            StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, spr, projectileImg, new Vector2f(0, 1), 5, 8);

            Game.game.addObj(pro);
            timeSinceLastFire = 25;
        }
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

    protected void checkDeath()
    {
        if(isDead)
        {
            Game.game.gameOver();
        }
    }
}
