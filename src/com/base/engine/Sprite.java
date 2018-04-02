package com.base.engine;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
    private float r;
    private float g;
    private float b;

    private int width;
    private int height;

    public Sprite(float r, float g, float b, int width, int height) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.width = width;
        this.height = height;
    }

    public void render(int textureID) {
        if (textureID != -1) {
            renderTex(textureID);
        } else {
            renderRBG();
        }
    }

    private void renderTex(int textureID) {
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(1, 1);
            glVertex2f(0, 0);

            glTexCoord2f(0, 1);
            glVertex2f(width, 0);

            glTexCoord2f(0, 0);
            glVertex2f(width, height);

            glTexCoord2f(1, 0);
            glVertex2f(0, height);
        }
        glEnd();
    }

    private void renderRBG() {
        glColor4f(r, g, b, 0.0f);

        glBegin(GL11.GL_QUADS);
        {
            glVertex2f(0, 0);
            glVertex2f(width, 0);
            glVertex2f(width, height);
            glVertex2f(0, height);
        }
        glEnd();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
