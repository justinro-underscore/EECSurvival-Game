package com.base.game.gameobject.entity;

import com.base.game.gameobject.projectile.HeatSeekingProjectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

public class Boss268 extends Boss {
    // Different attack delays
    private Delay wallAttackDelay;
    private Delay targetAttackDelay;
    private Delay burstAttackDelay;

    /**
     * Creates a new Boss268
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param numFrames number of frames in the animation
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Boss268(float xPos, float yPos, int numFrames, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, numFrames, width, height, speed, health, attackDamage,imgPath);
        standardProjectiles = new ArrayList<>(); // Initalizes the list
        heatSeekingProjectiles = new ArrayList<>();

        //Delay between attacks from the Boss
        wallAttackDelay = new Delay(3000);
        targetAttackDelay = new Delay(800);
        burstAttackDelay = new Delay(1200);

        //Initiates the Attacks from the boss
        wallAttackDelay.start();
        targetAttackDelay.start();
        burstAttackDelay.start();
    }

    @Override
    /**
     * Runs the attack pattern
     */
    public void attack() {
        if(wallAttackDelay.isOver()){ // Wall attack
            topDownWallOfFireAbility(10,10,8, 5, 10);
            wallAttackDelay.start();
        }
        if(targetAttackDelay.isOver()){ // Target attack
            targetPlayerAbility(10,10, 5, 10);
            targetAttackDelay.start();
        }
        if(burstAttackDelay.isOver()) { // Burst attack
            if(Math.random() * 2 > 1){
                shootAbility(10,10,10, 5, 10);
            }
            burstAttackDelay.start();
        }
    }
}
