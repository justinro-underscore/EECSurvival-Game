package com.base.game.gameobject.item;

import com.base.engine.GameObject;

public abstract class Item extends GameObject
{
    protected int despawnTime;

    /**
     * Generic constructor for an item
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param despawnTime the time it takes for a item to dematerialize and be unattainable 
     */
    protected Item(float xPos, float yPos, int width, int height, String imgPath, int despawnTime){
        init(xPos, yPos, width, height, imgPath);

        this.despawnTime = despawnTime;
    }
}
