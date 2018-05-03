package com.base.game.gameobject.projectile;

import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Vector2f;
import com.base.game.Game;
import com.base.game.gameobject.entity.Player;
import com.base.game.utilities.Util;

import java.util.ArrayList;
import java.util.Vector;

public class HeatSeekingProjectile extends Projectile
{
    float heatSeekingRange;

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
     * @param boss boolean if player is boss
     * @param heatSeekingRange range to start heatseeking
     */
    public HeatSeekingProjectile(float xPos, float yPos, int width, int height, String imgPath, Vector2f shootAngle, int damage, float speed, boolean boss, float heatSeekingRange){
        super(xPos, yPos, width, height, imgPath, shootAngle, damage, speed, boss,1);
        this.heatSeekingRange = heatSeekingRange;
    }

    @Override
    /**
     * Updates the projectile object every frame, and it searches for the Player
     */
    public void update(){
        //Updates

        ArrayList<GameObject> closeObjects = Game.game.getCurrLevel().getCloseObjects(this, heatSeekingRange); // Get any objects close to the character (cuts down on load time)
        for(GameObject obj : closeObjects)
        {
            if(obj instanceof Player)
            {
                shootAngle = new Vector2f (-((xPos + width/2) - obj.getX()), -((yPos + height/2) - obj.getY())).normalize();
                break;
            }
        }
        xPos += shootAngle.x * speed; // Move in x direction
        yPos += shootAngle.y * speed; // Move in y direction
        if (Util.offScreen(this, width, height)) { // If projectile goes offscreen...
            remove(); // Remove the projectile
        }
    }
}
