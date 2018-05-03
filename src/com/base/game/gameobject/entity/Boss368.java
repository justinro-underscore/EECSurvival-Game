package com.base.game.gameobject.entity;

import com.base.game.gameobject.projectile.HeatSeekingProjectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

public class Boss368 extends Boss {
    // Different attack delays
    private Delay lRwallAttackDelay;
    private Delay rLwallAttackDelay;
    private Delay burstAttackDelay;
    private Delay targetAttackDelay;

    /**
     * Creates a new Boss168
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
    public Boss368(float xPos, float yPos, int numFrames, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, numFrames, width, height, speed, health, attackDamage,imgPath);

        standardProjectiles = new ArrayList<>(); // Initalizes the list
        heatSeekingProjectiles = new ArrayList<>();

        //Delay between attacks from the Boss
        lRwallAttackDelay = new Delay(3000);
        rLwallAttackDelay = new Delay(3000);
        burstAttackDelay = new Delay(2000);
        targetAttackDelay = new Delay(1000);

        //Initiates the Attacks from the boss
        lRwallAttackDelay.start();
        rLwallAttackDelay.start();
        burstAttackDelay.start();
        targetAttackDelay.start();
    }

    @Override
    /**
     * Runs the attack pattern
     */
    public void attack() {
        if(lRwallAttackDelay.isOver()){ // Wall attack
            leftOrRightWallOfFireAbility(10,10,2, 4, 10, true);
            lRwallAttackDelay.start();
        }
        if(rLwallAttackDelay.isOver()){ // Wall attack
            leftOrRightWallOfFireAbility(10,10,3, 4, 10, false);
            rLwallAttackDelay.start();
        }
        if(burstAttackDelay.isOver()) { // Burst attack
            if(Math.random() * 2 > 1){
                shootAbility(10,10,10, 3, 10);
            }
            burstAttackDelay.start();
        }
        if(targetAttackDelay.isOver()){ // Target attack
            targetPlayerAbility(10,10, 3, 3);
            targetAttackDelay.start();
        }
    }
}
