package com.base.game.interfaces;

import com.base.engine.Animation;
import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.engine.SpriteRetriever;

public abstract class Interface {
    protected final Sprite container = SpriteRetriever.loadSprite("res/assets/container.png", 0, 0, 1, 1320, 300)[0];
    private Animation background;

    /**
     * Initialize the interface
     * @param filePath the filepath
     * @param widthOfImage width of the image
     * @param heightOfImage height of the image
     */
    public void init(String filePath,int widthOfImage, int heightOfImage) {
        background = new Animation(1,0,0,filePath,widthOfImage,heightOfImage,Display.getWidth(),Display.getHeight());
        background.getCurrentFrame().render(0,0,Display.getWidth(),Display.getHeight());
    }

    /**
     * Render the interface
     */
    public void render() {
       background.render(0, 0);
    }
}
