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

    public void render() {
        glColor3f(r, g, b);

        glBegin(GL11.GL_QUADS);
            glVertex2f(0, 0);
            glVertex2f(width, 0);
            glVertex2f(width, height);
            glVertex2f(0, height);
        glEnd();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
