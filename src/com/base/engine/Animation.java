package com.base.engine;

import com.base.game.utilities.Delay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Animation class:
 * Collection of sprites that have a delay between frames
 * Can be started, stopped, updated, and rendered
 */
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
     * Creates a new animation using the passed sprite sheet
     * @param nFrames Number of frames of the animation
     * @param x Start x coord of the image on the sprite sheet
     * @param y Start y coord of the image on the sprite sheet
     * @param file Sprite sheet to use
     * @param widthofImage width of image to be used when getting image from sprite sheet
     * @param heightofImage height of image ot be used when getting image from sprite sheet
     * @param width width of sprite
     * @param height height of image
     */
    public Animation(int nFrames, int x, int y,String file,int widthofImage, int heightofImage, int width, int height ) {
        this.IMGwidth= widthofImage;
        this.IMGheight = heightofImage;

        this.width = width;
        this.height = height;

        frames = SpriteRetriever.loadSprite(file,x,y,nFrames, IMGwidth,IMGheight);
        n_frames = nFrames;

        currFrame = 0;

        frameDelay = new Delay(100); //TODO: change to parameter
        frameDelay.restart();
        isStopped = false;
    }

    /**
     * Starts the animation
     */
    public void start() {
        if (!isStopped)
            return;

        if (n_frames == 0)
            return;

        isStopped = false;
        frameDelay.start();

    }

    /**
     * Stops the animation at current frame
     */
    public void stop() {
        if (n_frames == 0)
            return;

        frameDelay.stop();
        isStopped = true;
    }

    /**
     * Renders the current frame at the x y pos
     * @param xPos x pos to render frame at
     * @param yPos y pos to render frame at
     */
    public void render(float xPos, float yPos)
    {
        frames[currFrame].render(xPos, yPos,width,height);
    }

    /**
     * Updates the animation to the next frame if the animation has been started
     */
    public void update() {
        if (isStopped)
            return;

        if (frameDelay.isOver()) {
            nextFrame();
            frameDelay.start();
        }
    }

    /**
     * Checks if the animation is stopped
     * @return Returns true if the animation is stopped
     */
    public boolean isStopped() {
        return isStopped;
    }

    /**
     * Restarts the animation frame delay
     */
    public void restart() {
        frameDelay.restart();
        isStopped = false;
    }

    /**
     * Sets the frame delay between frames
     * @param time_ms time to delay between frames
     */
    public void setFrameDelay(int time_ms) {
        frameDelay.restart(time_ms);
    }

    /**
     * Changes the animation to the next frame
     */
    public void nextFrame() {
        frameDelay.start();

        currFrame++;
        if (currFrame == frames.length) {
            currFrame = 0;
        }
    }

    /**
     * Returns the current frame of the animation
     * @return currentFrame
     */
    public Sprite getCurrentFrame()
    {
        return frames[currFrame];
    }
}
