package com.base.game.gameobject.entity;

import com.base.engine.GameObject;

public class Empty extends GameObject {
    public Empty(float x, float y, int width, int height) {
        init(x, y, 0, 0, 1, false, "res/assets/white.png", 1, 1, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
