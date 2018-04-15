package com.base.engine;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject
{
    protected float xPos; // x-coordinate (middle of sprite)
    protected float yPos; // y-coordinate (middle of sprite)
    protected int width;
    protected int height;
    protected boolean boss;
    protected Sprite sprite;
    protected boolean toRemove; // A boolean that tells whether or not the object should be removed
    // We use this in order to avoid ConcurrentModifactionErrors (removing the object in the for loop)

    /**
     * Initializes the GameObject
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param width width of the sprite
     * @param height height of the sprite
     * @param imgPath file path to the image representing the sprite
     */
    protected void init(float xPos, float yPos, int width, int height, String imgPath, boolean theBoss)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.boss = theBoss;
        sprite = new Sprite(width, height, imgPath); // Creates the sprite

        toRemove = false; // We shouldn't remove it as soon as we create it...
    }

    /**
     * Update function should be implemented by subclasses
     */
    abstract public void update();

    /**
     * Renders the GameObject's sprite
     */
    public void render() {
        sprite.render(xPos, yPos);
    }

    /**
     * Marks the GameObject for removal
     */
    public void remove() {
        toRemove = true;
    }

    /**
     * Returns if the object should be removed
     * @return toRemove
     */
    public boolean isRemoved() {
        return toRemove;
    }

    /**
     * Gets the sprite's height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the sprite's width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the sprite's x-position (anchored from center of sprite)
     * @return xPos
     */
    public float getX() {
        return xPos + (width/2.0f);
    }

    /**
     * Gets the sprite's y-position (anchored from center of sprite)
     * @return yPos
     */
    public float getY() {
        return yPos + (height/2.0f);
    }

    /**
     * Resets the texture of the sprite
     * @param imgPath file path to the new texture
     */
    public void setTexture(String imgPath) {
        sprite.setTexture(imgPath);
    }

    public boolean getBoss(){return boss;}
}
