package com.base.game.gameobject.entity;

import com.base.engine.Display;
import com.base.engine.InputHandler;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player {
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    public Player(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
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
        glBegin(GL11.GL_QUADS);
            glVertex2f(xPos, yPos);
            glVertex2f(xPos + width, yPos);
            glVertex2f(xPos + width,yPos + height);
            glVertex2f(xPos,yPos + height);
        glEnd();
    }
}
