package com.base.game.gameobject.object;

import com.base.engine.GameObject;

public class Door extends GameObject {
    /**
     * Constructor for a door
     * @param xPos x-coordinate of the door
     * @param yPos y-coordinate of the door
     * @param width width of the door
     * @param height height of the door
     * @param imgPath file path to the image representing the door
     */
    public Door(float xPos, float yPos, int width, int height, String imgPath, int frames) {
        init(xPos, yPos, width, height, frames,false);
    }

    /**
     * Empty update
     */
    public void update() {}
}
