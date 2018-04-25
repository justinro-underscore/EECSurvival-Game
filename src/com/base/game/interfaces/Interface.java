package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.engine.SpriteRetriever;

public abstract class Interface {
    private Sprite background;
    private SpriteRetriever retriever;
    /**
     * Initialize the interface
     * @param filePath the filename of the background image
     */
    public void init(String filePath) {
        background = retriever.getSprite(Display.getWidth() , Display.getHeight(), retriever.loadSprite(filePath));
    }

    /**
     * Render the interface
     */
    public void render() {
        background.render(0, 0);
    }
}
