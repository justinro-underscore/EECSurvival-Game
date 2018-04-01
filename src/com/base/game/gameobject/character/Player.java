package com.base.game.gameobject.character;

import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.engine.Sprite;
import com.base.game.Game;
import com.base.engine.GameVector;
import com.base.game.gameobject.projectile.StandardProjectile;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Character
{
    int timeSinceLastFire;

    public Player(int xPos, int yPos, int width, int height, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, width, height, sprite, health, attackDamage, attackSpeed);
        timeSinceLastFire = 0;
    }

    @Override
    public void update()
    {
        if(timeSinceLastFire > 0)
            timeSinceLastFire--;
        getInput();
    }

    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += 5;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= 5;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += 5;
        }

        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -=5;
        }

        if(InputHandler.isKeyDown(GLFW_KEY_SPACE)) {
            if(timeSinceLastFire == 0) {
                int proWidth = 5;
                int proHeight = 5;
                Sprite spr = new Sprite(1.0f, 0.0f, 0.0f, proWidth, proHeight);
                StandardProjectile pro = new StandardProjectile(xPos + (width / 2) + (proWidth / 2), yPos + height, proWidth, proHeight, spr, new GameVector(0, 1), 5, 8);
                Game.game.addObj(pro);
                timeSinceLastFire = 25;
            }
        }
    }
}
