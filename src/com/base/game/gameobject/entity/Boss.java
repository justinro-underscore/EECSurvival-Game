package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.HeatSeekingProjectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.levels.LevelManager;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

public class Boss extends Character { // TODO Make this class abstract, and extend new bosses

    protected ArrayList<StandardProjectile> standardProjectiles; // List of standardProjectiles
    protected ArrayList<HeatSeekingProjectile> heatSeekingProjectiles; // List of heatSeekingProjectiles

    private int burstSfx;
    private int deathSfx;

    /**
     * Creates a new boss
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param numFrames number of frames in the animation
     * @param width width
     * @param height height
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     * @param image image of the boss
     */
    public Boss(float xPos, float yPos, int numFrames, int width, int height, float speed, int health, int attackDamage, String image) {
        super(xPos, yPos, numFrames, width, height, speed, health, attackDamage, true, image);
      
        deathSfx = Audio.loadSound("res/audio/congratulations.ogg");
      
        standardProjectiles = new ArrayList<>();
        heatSeekingProjectiles = new ArrayList<>();
    }

    /**
     * Updates the boss object every frame, will be overridden
     * in base classes
     */
    public void update() {
        //Constantly checking the collision of bullets and whether the boss is killed in that instance.
        checkCharacterCollision();
        checkDeath();

        attack(); // Runs its attack pattern

        for (int i = 0; i < standardProjectiles.size(); i++){
            Game.game.getCurrLevel().addObj(standardProjectiles.get(i)); // Create all of the standardProjectiles
        }
        standardProjectiles.clear();
        for (int i = 0; i < heatSeekingProjectiles.size(); i++){
            Game.game.getCurrLevel().addObj(heatSeekingProjectiles.get(i)); // Create all of the standardProjectiles
        }
        heatSeekingProjectiles.clear();

        xPos += stats.getSpeed(); // Move
        if (Math.abs(getX() - Display.getWidth() / 2.0f) >= Display.getWidth() / 4.0f) // Change direction
            stats.setSpeed(-(stats.getSpeed()));
    }

    /**
     * Runs the attack pattern
     */
    public void attack() {}

    /**
     * Burst attack
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of standardProjectiles being made
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     */
    public void shootAbility(int proWidth, int proHeight, int numberOfPros, float speed, int damage) {
        for (int i = 1; i <= numberOfPros; i++) {
            standardProjectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, proWidth, proHeight, "res/assets/PaperBall.png" , new Vector2f(-(numberOfPros/2) + i - 1 , -1), damage, speed,true));
        }
    }

    /**
     * Shoot at player's current position
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     */
    public void targetPlayerAbility(int proWidth, int proHeight, float speed, int damage) {
        standardProjectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(Game.game.getCurrLevel().getPlayerX() - (getX() - (proWidth / 2)), Game.game.getCurrLevel().getPlayerY() - (yPos - proHeight)), damage, speed,true));
    }

    /**
     * Attack using heat seeking bullets
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     */
    public void heatSeekingAbility(int proWidth, int proHeight, float speed, int damage) {
        heatSeekingProjectiles.add(new HeatSeekingProjectile(getX() - (proWidth / 2), yPos - proHeight, proWidth, proHeight, "res/assets/redPen.png", new Vector2f(0, -1), damage, speed,true, 300));
    }

    /**
     * Top down attack with evenly spaced out projectiles
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of standardProjectiles being made
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     */
    public void topDownWallOfFireAbility(int proWidth, int proHeight, int numberOfPros, float speed, int damage) {
        for (int i = 1; i <= numberOfPros; i++){
            standardProjectiles.add(new StandardProjectile((Display.getWidth() / numberOfPros) * i - (Display.getWidth() / (numberOfPros * 2)), yPos - proHeight, proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(0, -1), damage, speed,true));
        }
    }

    /**
     * Top down attack with evenly spaced out projectiles
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param numberOfPros Number of standardProjectiles being made
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     * @param goingRight if true, projectiles going right; if not, they go left
     */
    public void leftOrRightWallOfFireAbility(int proWidth, int proHeight, int numberOfPros, float speed, int damage, boolean goingRight) {
        if(goingRight){
            for (int i = 1; i <= numberOfPros; i++){
                standardProjectiles.add(new StandardProjectile(0, (Display.getHeight() / numberOfPros) * i - (Display.getHeight() / (numberOfPros * 2)), proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(1, 0), damage, speed,true));
            }
        }
        else{
            for (int i = 1; i <= numberOfPros; i++){
                standardProjectiles.add(new StandardProjectile(Display.getWidth(), (Display.getHeight() / numberOfPros) * i - (Display.getHeight() / (numberOfPros * 2)), proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(-1, 0), damage, speed,true));
            }
        }
    }

    /**
     * Top down attack with evenly spaced out projectiles
     * @param proWidth Projectile width
     * @param proHeight Projectile height
     * @param speed speed of the bullets
     * @param damage damage of the bullets
     */
    public void fourCornersAbility(int proWidth, int proHeight, float speed, int damage) {
        standardProjectiles.add(new StandardProjectile(0, 0, proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2), damage, speed,true));
        standardProjectiles.add(new StandardProjectile(0, Display.getHeight(), proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(Display.getWidth() / 2, -Display.getHeight() / 2), damage, speed,true));
        standardProjectiles.add(new StandardProjectile(Display.getWidth(), Display.getHeight(), proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(-Display.getWidth() / 2, -Display.getHeight() / 2), damage, speed,true));
        standardProjectiles.add(new StandardProjectile(Display.getWidth(), 0, proWidth, proHeight, "res/assets/PaperBall.png", new Vector2f(-Display.getWidth() / 2, Display.getHeight() / 2), damage, speed,true));
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
            LevelManager.pause();
            Audio.playBuffer(deathSfx);

            Game.game.getCurrLevel().levelOver(false); // Level has been won!
        }
    }

    /**
     * Check if the UI must be updated (only works for Boss388)
     * @return whether or not the UI should be updated, or true if not Boss388
     */
    public boolean checkForUpdateUI()
    {
        if(this instanceof Boss388)
            return checkForUpdateUI();
        else
            return true; // So it will stop checking immediately
    }
}
