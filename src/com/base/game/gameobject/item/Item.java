package com.base.game.gameobject.item;

import com.base.engine.GameObject;

public abstract class Item extends GameObject{
    public Item(float xPos, float yPos, int width, int height, String imgPath){
        init(xPos, yPos, width, height, imgPath);
    } 
}
