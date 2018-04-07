package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.Render;

public abstract class Interface {
    private Render background;

    public void init(String filePath) {
        background = new Render(Display.getWidth(), Display.getHeight(), filePath);
    }

    public void render() {
        background.render(0, 0);
    }
}
