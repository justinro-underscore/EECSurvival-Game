package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Boss extends Character { // TODO Make this class abstract, and extend new bosses
    // Different attack delays
    private Delay wallAttackDelay;
    private Delay targetAttackDelay;
    private Delay burstAttackDelay;

    private ArrayList<StandardProjectile> projectiles; // List of projectiles

    /**
     * Creates a new boss
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Boss(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, width, height, imgPath, speed, health, attackDamage,true);

        projectiles = new ArrayList<>(); // Initalizes the list

        //Delay between attacks from the Boss
        wallAttackDelay = new Delay(3000);
        targetAttackDelay = new Delay(800);
        burstAttackDelay = new Delay(1200);

        //Initiates the Attacks from the boss
        wallAttackDelay.start();
        targetAttackDelay.start();
        burstAttackDelay.start();
    }

    /**
     * Updates the boss object every frame
     */
    public void update() {
        //Constantly checking the collision of bullets and whether the boss is killed in that instance.
        checkCharacterCollision();
        checkDeath();

        attack(); // Runs its attack pattern

        for (int i = 0; i < projectiles.size(); i++){
            Game.game.getCurrLevel().addObj(projectiles.get(i)); // Create all of the projectiles
        }
        projectiles.clear();

        xPos += stats.getSpeed(); // Move
        if (Math.abs(getX() - Display.getWidth() / 2.0f) >= Display.getWidth() / 4.0f) // Change direction
            stats.setSpeed(-(stats.getSpeed()));
    }

    /**
     * Runs the attack pattern
     */
    public void attack() {
        if(wallAttackDelay.isOver()){ // Wall attack
            wallOfFireAbility(15,15,8);
            wallAttackDelay.start();
        }
        if(targetAttackDelay.isOver()){ // Target attack
            targetPlayerAbility(15,15,5);
            targetAttackDelay.start();
        }
        if(burstAttackDelay.isOver()) { // Burst attack
            if(Math.random() * 2 > 1){
                shootAbility(10,10,10);
            }
            burstAttackDelay.start();
        }
    }

    /**
     * Burst attack
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of projectiles being made
     */
    public void shootAbility(int proWidth, int proHeight, int numberOfPros) {
        for (int i = 1; i <= numberOfPros; i++) {
            projectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, proWidth, proHeight, "", new Vector2f(-(numberOfPros/2) + i - 1 , -1), 5, 5,true));
        }
    }

    /**
     * Target attack
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of projectiles being made
     */
    public void targetPlayerAbility(int proWidth, int proHeight, int numberOfPros) {
        projectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, proWidth, proHeight, "", new Vector2f(Game.game.getCurrLevel().getPlayerX() - (getX() - (proWidth / 2)), Game.game.getCurrLevel().getPlayerY() - (yPos - proHeight)), 5, 5,true));
    }

    /**
     * Wall attack
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of projectiles being made
     */
    public void wallOfFireAbility(int proWidth, int proHeight, int numberOfPros) {
        for (int i = 1; i <= numberOfPros; i++){
            projectiles.add(new StandardProjectile((Display.getWidth() / numberOfPros) * i - (Display.getWidth() / (numberOfPros * 2)), yPos - proHeight, proWidth, proHeight, "", new Vector2f(0, -1), 5, 5,true));
        }
    }

    /**
     * Checks character specific collisions
     * @param obj the object being collided with
     */
    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        // Boss specific collisions
    }

    /**
     * Check if the level has been won
     */
    protected void checkDeath()
    {
        if(stats.getIsDead())
        {
            Game.game.getCurrLevel().levelOver(false); // Level has been won!
        }
    }
}
