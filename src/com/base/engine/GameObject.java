package com.base.engine;

import java.awt.image.BufferedImage;

public abstract class GameObject
{
    protected float xPos; // x-coordinate (middle of render)
    protected float yPos; // y-coordinate (middle of render)
    protected int width;
    protected int height;
    protected String imgPath;

    protected TextureLoader loader;
    protected Render render;
    protected SpriteRetriever retriever = new SpriteRetriever(32);//TODO MAKE THIS VARIABLE (standardize sprite sheet cell size orrrrr add a variable to each objects initialization)
    protected boolean toRemove; // A boolean that tells whether or not the object should be removed
    // We use this in order to avoid ConcurrentModifactionErrors (removing the object in the for loop)

    /**
     * Initializes the GameObject
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param width width of the render
     * @param height height of the render
     * @param imgPath large png we need to chop up
     */
    protected void init(float xPos, float yPos, int width, int height, String imgPath)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.imgPath = imgPath;

        //turns the large image into a buffered image
        //BufferedImage spriteSheet = retriever.loadSprite(imgPath);

        //Now we need to CUT 'ER UP
        //BufferedImage subimage;
        //subimage = retriever.getSprite(someX, someY,spriteSheet);
        //someID = loader.loadTexture(subimage);

        render = new Render(); // Creates the render

        toRemove = false; // We shouldn't remove it as soon as we create it...
    }

    /**
     * Update function should be implemented by subclasses
     */
    abstract public void update();

    /**
     * Renders the GameObject's render
     */
    public void render() {
        render.render(height, width, xPos, yPos, textureID);
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
     * Gets the render's height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the render's width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the render's x-position (anchored from center of render)
     * @return xPos
     */
    public float getX() {
        return xPos + (width/2.0f);
    }

    /**
     * Gets the render's y-position (anchored from center of render)
     * @return yPos
     */
    public float getY() {
        return yPos + (height/2.0f);
    }


}
