package com.base.game.gameobject.entity;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.InputHandler;
import com.base.engine.Sprite;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends GameObject {
    public Player(int xPos, int yPos, Sprite sprite) {
        init(xPos, yPos, sprite);
    }

    public void update() {
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
