package com.base.game.gameobject.item;

import com.base.engine.GameObject;

public abstract class Item extends GameObject
{
    protected int despawnTime;

    protected Item(float xPos, float yPos, int width, int height, String imgPath, int despawnTime){
        init(xPos, yPos, width, height, imgPath);

        this.despawnTime = despawnTime;
    }
}
