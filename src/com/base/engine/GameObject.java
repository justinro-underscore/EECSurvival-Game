package com.base.engine;

import java.awt.image.BufferedImage;

public abstract class GameObject
{
    //TODO Im not sure how you want to handle initializing the animation
    protected float xPos; // x-coordinate (middle of render)
    protected float yPos; // y-coordinate (middle of render)
    protected int width;
    protected int height;
    protected boolean boss;
    //protected Sprite sprite;
    protected Animation currAnimation; //A list of the possible animation (i.e. walk left, walk right, walk up, walk down, shoot, etc..)
    protected boolean toRemove; // A boolean that tells whether or not the object should be removed
    // We use this in order to avoid ConcurrentModifactionErrors (removing the object in the for loop)

    /**
     * Initializes the GameObject
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param width width of the render
     * @param height height of the render
     * @param numFrames how many animations the gameObject will have.
     */
    protected void init(float xPos, float yPos, int width, int height, int numFrames, boolean theBoss,String imgPath, int xSprite, int ySprite,int screenWidth,int screenHeight)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = xSprite;
        this.height = ySprite;
        this.boss = theBoss;
        currAnimation = new Animation(numFrames,width,height,imgPath,xSprite,ySprite,screenWidth,screenHeight);
        //sprite = new Sprite(width, height, imgPath); // Creates the sprite

        toRemove = false; // We shouldn't remove it as soon as we create it...
    }

    /**
     * Update function should be implemented by subclasses
     */
    abstract public void update();

    /**
     * Renders the GameObject's render (defaults to the first sprite)
     */
    public void render() {
        currAnimation.render(xPos, yPos);
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

    /**
     * Resets the texture of the sprite
     * @param  path to the new texture
     *
    public void setTexture(String imgPath) {
        sprite.setTexture(imgPath);
    }
    */

    public boolean getBoss(){return boss;}
}
