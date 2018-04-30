package com.base.game.interfaces;

import com.base.engine.Animation;
import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.engine.SpriteRetriever;

public abstract class Interface {
    private Animation background;
    private SpriteRetriever retriever;
    /**
     * Initialize the interface
     * @param filePath the filename of the background image
     */
    public void init(String filePath) {
//        background = new Animation(1,1,1,"res/SpriteSheets/testSpriteSheet.png",1,1);
    }

    /**
     * Render the interface
     */
    public void render() {
//        background.render(0, 0);
    }
}
