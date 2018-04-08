package com.base.game.gameobject.projectile;

import com.base.engine.Sprite;
import com.base.engine.Vector2f;

public class StandardProjectile extends Projectile
{
    /**
     * Creates a standard projectile (shoots in a straight line)
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param shootAngle the initial angle of the projectile
     * @param damage damage dealt to the character
     * @param speed speed of the projectile
     */
    public StandardProjectile(float xPos, float yPos, int width, int height, String imgPath, Vector2f shootAngle, int damage, float speed)
    {
        super(xPos, yPos, width, height, imgPath, shootAngle, damage, speed); // Call the super class's contructor
    }
}
