package com.base.engine;

import com.base.game.utilities.Delay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {
    public Sprite[] frames;
    private int currFrame;
    private int n_frames;
    private Delay frameDelay;
    private boolean isStopped;
    private int IMGwidth;
    private int IMGheight;
    private int width;
    private int height;

    /**
     * Initializes the animation
     * @param nFrames how many sprites will make up the animation
     * @param TILE_SIZE the size of the tile in the given spritesheet.
     */
    public Animation(int nFrames, int x, int y,String file,int widthofImage, int heightofImage, int width, int height ) {
        this.IMGwidth= widthofImage;
        this.IMGheight = heightofImage;

        this.width =width;
        this.height=height;
        frames = SpriteRetriever.loadSprite(file,x,y,nFrames, IMGwidth,IMGheight);
        n_frames = nFrames;
        currFrame = 0;
        frameDelay = new Delay(200); //TODO do we want to make this variable?
        frameDelay.restart();

        isStopped = false;
    }

    /**
     * Adds a new sprite to the animation
     * @param xCord the X coordinate of the sprite in the sprite sheet.
     * @param yCord the Y coordinate of the sprite in the sprite sheet.
     * @param fileName the sprite sheet.
     */


    public void start() {
        if (!isStopped)
            return;

        if (n_frames == 0)
            return;

        isStopped = false;
        frameDelay.start();
    }

    public void stop() {
        if (n_frames == 0)
            return;

        frameDelay.stop();
        isStopped = true;
    }

    public void render(float xPos, float yPos)
    {
        frames[currFrame].render(xPos, yPos,width,height);
    }

    public void update() {
        if (isStopped)
            return;

        if (frameDelay.isOver()) {
            nextFrame();
            frameDelay.start();
        }
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void restart() {
        frameDelay.restart();
        isStopped = false;
    }

    public void nextFrame() {
        currFrame++;
        if (currFrame == frames.length) {
            currFrame = 0;
        }
    }
    public Sprite getCurrentFrame()
    {
        return frames[currFrame];
    }
}
