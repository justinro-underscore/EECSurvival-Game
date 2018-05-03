package com.base.game.gameobject.projectile;

import com.base.engine.Vector2f;
import com.base.engine.GameObject;
import com.base.game.utilities.Util;

public abstract class Projectile extends GameObject
{
    protected Vector2f shootAngle; // The angle of movement of the projectile
    protected int damage; // Damage to be done
    protected float speed; // Speed of the projectile
    protected boolean boss;
    /**
     * Creates a projectile object
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the render
     * @param shootAngle the initial angle of the projectile
     * @param damage damage dealt to the character
     * @param speed speed of the projectile
     * @param theBoss boolean if object is boss
     * @param frames number of frames for img animation
     */
    protected Projectile(float xPos, float yPos, int width, int height, String imgPath, Vector2f shootAngle, int damage, float speed, boolean theBoss, int frames)
    {
        init(xPos, yPos, 0, 0, frames,theBoss,imgPath,width,height,width,height); // Call super class's initialization
        this.shootAngle = shootAngle.normalize();
        this.damage = damage;
        this.speed = speed;
        this.boss = theBoss;
    }

    /**
     * Updates the projectile object every frame
     */
    public void update()
    {
        //Updates
        xPos += shootAngle.x * speed; // Move in x direction
        yPos += shootAngle.y * speed; // Move in y direction

        if (Util.offScreen(this, width, height)) { // If projectile goes offscreen...
            remove(); // Remove the projectile
        }
    }

    /**
     * Returns the damage dealt
     * @return the amount of damage
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * get the boolean boss
     * @return the boolean boss
     */
    public boolean getBoss() {return boss;}
}
