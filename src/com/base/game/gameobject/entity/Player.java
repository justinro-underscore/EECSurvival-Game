package com.base.game.gameobject.entity;

import com.base.engine.*;

import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends GameObject {
    private int textureID;

    public Player(int xPos, int yPos, Sprite sprite, String fileName) {
        init(xPos, yPos, sprite);

        if (!Objects.equals(fileName, "")) {
            try {
                textureID = TextureLoader.loadTexture(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            textureID = -1;
        }

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
            xPos -= 5;
        }
    }

    public void render() {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            sprite.render(textureID);

            glDisable(GL_BLEND);
        }
        glPopMatrix();
    }
}
