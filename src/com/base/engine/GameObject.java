package com.base.engine;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject
{
    protected float xPos;
    protected float yPos;
    protected int width;
    protected int height;

    protected int textureID;
    protected Sprite sprite;
    protected boolean toRemove;

    protected void init(float xPos, float yPos, int width, int height, String imgPath)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        if (!imgPath.equals(""))
        {
            try { textureID = TextureLoader.loadTexture(imgPath); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else
            textureID = -1;
        sprite = new Sprite(width, height, imgPath);

        toRemove = false;
    }

    abstract public void update();

    public void render() {
        sprite.render(xPos, yPos);
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
        return xPos + (width/2.0f);
    }

    public float getY() {
        return yPos + (height/2.0f);
    }
}
