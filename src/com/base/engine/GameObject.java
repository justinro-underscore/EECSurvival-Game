package com.base.engine;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

public abstract class GameObject
{
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected boolean toRemove;

    protected void init(int xPos, int yPos, Sprite sprite)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();

        this.sprite = sprite;

        toRemove = false;
    }

    public void update() {}

    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            sprite.render();
        }
        glPopMatrix();
    }

    public void remove() {
        toRemove = true;
    }

    public boolean isRemoved() {
        return toRemove;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
