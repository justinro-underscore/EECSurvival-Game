package com.base.game.gameobject.item;

import com.base.engine.GameObject;
import com.base.game.utilities.Delay;

public abstract class Item extends GameObject
{
    private Delay despawnDelay;
    private boolean canDespawn;

    /**
     * Generic constructor for an item
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param despawnTime the time it takes for a item to dematerialize and be unattainable
     * @param frames number of frames for img animation
     */
    protected Item(float xPos, float yPos, int width, int height, String imgPath, int despawnTime, int frames){
        init(xPos, yPos, 0, 0, frames,false,"res/SpriteSheets/testSpriteSheet.png",width,height, width, height);

        if (despawnTime == -1) {
            canDespawn = false;
        } else {
            canDespawn = true;
        }

        if (despawnTime == -1) {
            canDespawn = false;
        } else {
            canDespawn = true;
        }

        despawnDelay = new Delay(despawnTime);
    }

    /**
     * Increment the despawn timer
     */
    public void update(){
        if (!canDespawn)
            return;

        if(!despawnDelay.isStarted()){
            despawnDelay.start();
        }
        if(despawnDelay.isOver()){
            remove();
        }
    }
}
