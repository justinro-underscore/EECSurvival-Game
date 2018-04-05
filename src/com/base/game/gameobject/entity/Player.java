package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private Delay attackDelay; // Delay between attacks

    /**
     * Creates a player object (should only be done once)
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Player(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, width, height, imgPath, speed, health, attackDamage); // Call Character superclass's constructor

        attackDelay = new Delay(500); // Time (in milliseconds) between attacks
        attackDelay.restart(); // Run this method so we can immediately fire
    }

    /**
     * Updates the player object every frame
     */
    public void update() {
        checkCharacterCollision();
        checkDeath();

        getInput();
    }

    /**
     * Gets input from the InputHandler
     */
    public void getInput() {
        // Go up
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += speed;
        }

        // Go down
        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= speed;
        }

        // Go right
        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += speed;
        }

        // Go left
        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -= speed;
        }

        // Shoot
        if(InputHandler.isKeyDown(GLFW_KEY_SPACE) && attackDelay.isOver()) {
            attack();
        }
    }

    /**
     *  Specifies how the player attacks
     */
    protected void attack() {
        // TODO Change this to something more modular
        int proWidth = 5; // Projectile width
        int proHeight = 5; // Projectile height

        StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, proWidth, proHeight, "", new Vector2f(0, 1), 5, 8); // Create the projectile

        Game.game.addObj(pro);
        attackDelay.start(); // Make sure the player can't rapid fire
    }

    /**
     * Checks character specific collisions
     * @param obj the object being collided with
     */
    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        if(obj instanceof Boss) // If the player touches the boss...
        {
            loseHealth(1); // Continually lose one hitpoint
        }
    }

    /**
     * Check to see if the player has died
     */
    protected void checkDeath() {
        if (isDead) // If the player is dead...
        {
            Game.game.levelOver(true); // Run levelOver
        }
    }
}
