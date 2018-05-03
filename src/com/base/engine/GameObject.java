package com.base.engine;

import com.base.game.gameobject.entity.Empty;
import com.base.game.gameobject.entity.EmptyPlayer;

/**
 * Defines all objects in the game world
 * All objects extend this class
 */
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
     * Initializes the game object
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param xStartLocation x-coordinate on spritesheet
     * @param yStartLocation y-coordinate on the spritesheet
     * @param numFrames how many animations the gameObject will have.
     * @param theBoss boolean if gameobject is a boss
     * @param imgPath img path
     * @param xImageWidth physical width of img
     * @param yImageHeight physical height of img
     * @param screenWidth display width of img
     * @param screenHeight display height of img
     */
    protected void init(float xPos, float yPos, int xStartLocation, int yStartLocation, int numFrames, boolean theBoss, String imgPath, int xImageWidth, int yImageHeight,int screenWidth,int screenHeight)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = screenWidth;
        this.height = screenHeight;
        this.boss = theBoss;

        if (!(this instanceof Empty || this instanceof EmptyPlayer))
            currAnimation = new Animation(numFrames,xStartLocation,yStartLocation,imgPath,xImageWidth,yImageHeight,screenWidth,screenHeight);

        toRemove = false; // We shouldn't remove it as soon as we create it...
    }

    /**
     * Updates the current animation
     */
    public void update() {
        currAnimation.update();
    }

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
     */
    public void setTexture() {
        currAnimation.nextFrame();
    }

    /**
     * Returns true if a gameobject is a boss
     * @return if gameobject is boss
     */
    public boolean getBoss(){return boss;}
}
