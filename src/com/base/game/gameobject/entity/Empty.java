package com.base.game.gameobject.entity;

import com.base.engine.GameObject;

public class Empty extends GameObject {
    public Empty(float x, float y, int width, int height) {
        init(x, y, width, height, 1, false, "",0,0, 1, 1);
    }

    @Override
    public void update() {

    }
}
