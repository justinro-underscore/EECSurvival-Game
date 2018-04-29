package com.base.game.gameobject.entity;

import com.base.engine.GameObject;

public class Empty extends GameObject {
    public Empty(float x, float y, int width, int height) {
        init(x, y, width, height, "", false);
    }

    @Override
    public void update() {

    }
}
