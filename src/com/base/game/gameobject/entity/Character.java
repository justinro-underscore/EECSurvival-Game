package com.base.game.gameobject.entity;

import com.base.engine.Physics;
import com.base.engine.GameObject;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.Game;

import java.util.ArrayList;

public abstract class Character extends GameObject
{
    protected float speed; // Speed of the Character
    protected int health; // Health of the Character
    protected int attackDamage; // How much damage the character deals
    protected boolean isDead; // Whether or not the character is dead

    /**
     * Abstract constructor for Character
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the render
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    protected Character(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        init(xPos, yPos, width, height, imgPath); // Call super initialize method

        this.speed = speed;
        this.health = health;
        this.attackDamage = attackDamage;
        isDead = false; // Character should not start out dead
    }

    /**
     * Checks to see if the character has collided with close objects
     */
    protected void checkCharacterCollision()
    {
        ArrayList<GameObject> closeObjects = Game.game.getCloseObjects(this, 5); // Get any objects close to the character (cuts down on load time)
        for(GameObject obj : closeObjects)
        {
            if(Physics.checkCollision(this, obj)) // If the character is touching a GameObject
            {
                if(obj instanceof Projectile) // If the object is a projectile...
                {
                    loseHealth(((Projectile) obj).getDamage()); // Lose specified amount of health
                    obj.remove(); // Delete the projectile
                }
                checkCharacterCollisionSpecific(obj); // Go to subclass specific collisions
            }
        }
    }

    /**
     * Take a specified amount of health off of character's health bar
     * @param hit amount of damage to take
     */
    protected void loseHealth(int hit)
    {
        health -= hit;
        if(health <= 0) // If health drops below 0
        {
            health = 0;
            isDead = true; // You dead, son
        }
    }

    /**
     * Returns the character's health
     * @return health of character
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Subclasses should specify what specific things they collide with
     * @param obj the object being collided with
     */
    abstract protected void checkCharacterCollisionSpecific(GameObject obj);

    /**
     * Characters have different things happen when they die
     */
    abstract protected void checkDeath();

    /**
     * Characters should implement their own attack methods
     */
    abstract protected void attack();
}
