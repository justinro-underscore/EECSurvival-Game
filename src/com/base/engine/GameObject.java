package com.base.engine;

import com.base.engine.Sprite;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class GameObject
{
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;

    private Sprite sprite;

    public GameObject(int xPos, int yPos, int width, int height, Sprite sprite)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        this.sprite = sprite;
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
}
