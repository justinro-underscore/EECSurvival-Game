package com.base.engine;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

public abstract class GameObject
{
    protected float xPos;
    protected float yPos;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected boolean toRemove;

    protected void init(float xPos, float yPos, Sprite sprite)
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
            sprite.render(-1);
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

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
}
