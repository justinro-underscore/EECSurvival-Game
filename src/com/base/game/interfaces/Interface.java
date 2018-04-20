package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.Sprite;

public abstract class Interface {
    private Sprite background;

    /**
     * Initialize the interface
     * @param filePath the filename of the background image
     */
    public void init(String filePath) {
        background = new Sprite(Display.getWidth(), Display.getHeight(), filePath);
    }

    /**
     * Render the interface
     */
    public void render() {
        background.render(0, 0);
    }
}
