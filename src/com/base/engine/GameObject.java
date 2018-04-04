package com.base.engine;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject
{
    protected float xPos;
    protected float yPos;
    protected int width;
    protected int height;

    protected Sprite sprite;
    protected int textureID;
    protected boolean toRemove;

    protected void init(float xPos, float yPos, Sprite sprite, String imgPath)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();

        this.sprite = sprite;
        if (!imgPath.equals(""))
        {
            try { textureID = TextureLoader.loadTexture(imgPath); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else
            textureID = -1;

        toRemove = false;
    }

    public void update() {}

    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            if(textureID != -1) {
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

                sprite.render(textureID);

                glDisable(GL_BLEND);
            }
            else
                sprite.render(textureID);
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
        return xPos + (width/2.0f);
    }

    public float getY() {
        return yPos + (height/2.0f);
    }
}
