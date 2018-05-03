package com.base.game.gameobject.entity;

import com.base.game.Game;
import com.base.game.gameobject.projectile.HeatSeekingProjectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.scenes.Scene;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

public class Boss388 extends Boss {
    // Different attack delays
    private Delay heatSeekingDelay;
    private Delay fourCornersDelay;

    private boolean isInitialAttack;
    private boolean transformed;

    /**
     * Creates a new Boss388
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
    public Boss388(float xPos, float yPos, int numFrames, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, numFrames, width, height, speed, health, attackDamage,imgPath);

        standardProjectiles = new ArrayList<>(); // Initalizes the list
        heatSeekingProjectiles = new ArrayList<>();

        isInitialAttack = true;

        //Delay between attacks from the Boss
        heatSeekingDelay = new Delay(15000);
        fourCornersDelay = new Delay(2000);

        //Initiates the Attacks from the boss
        fourCornersDelay.start();
    }

    @Override
    /**
     * Updates the boss
     */
    public void update()
    {
        super.update();
        if(!transformed && stats.getHealth() == 5)
        {
            Scene scene = new Scene("res/scripts/minden.bsh", Game.game.getCurrLevel().getPlayer(), Game.game.getCurrLevel().getBoss(), Game.game.getCurrLevel());
            scene.run();
            currAnimation.nextFrame();
            heatSeekingDelay.start();
            heatSeekingDelay.end();
            transformed = true;
            stats.setMaxHealth(100);
            stats.setMaxHealth();
        }
    }

    @Override
    /**
     * Runs the attack pattern
     */
    public void attack() {
        if(isInitialAttack){
            heatSeekingDelay.start();
            isInitialAttack = false;
        }
        if(heatSeekingDelay.isOver()){ // Wall attack
            heatSeekingAbility(10,50,4, 45);
            heatSeekingDelay.start();
        }
        if(fourCornersDelay.isOver()){
            fourCornersAbility(10,10, 10, 15);
            fourCornersDelay.start();
        }
    }

    /**
     * Check if the UI must be updated
     * @return whether or not the UI should be updated
     */
    public boolean checkForUpdateUI()
    {
        return transformed;
    }
}
