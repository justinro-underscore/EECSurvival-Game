package com.base.game.gameobject.entity;

import com.base.engine.GameObject;

public class Empty extends GameObject {
    /**
     * Empty game object used for testing purposed
     * @param x x position
     * @param y y position
     * @param width how wide is the object
     * @param height how high is the object
     */
    public Empty(float x, float y, int width, int height) {
        init(x, y, 0, 0, 1, false, "res/assets/white.png", 1, 1, width, height);
    }

    @Override
    /**
     * update the empty object
     */
    public void update() {

    }

    @Override
    /**
     * Render the empty object
     */
    public void render() {

    }
}
