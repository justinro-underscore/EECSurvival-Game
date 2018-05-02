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

    public Animation(int nFrames, int x, int y,String file,int widthofImage, int heightofImage, int width, int height ) {
        this.IMGwidth= widthofImage;
        this.IMGheight = heightofImage;

        this.width =width;
        this.height=height;

        frames = SpriteRetriever.loadSprite(file,x,y,nFrames, IMGwidth,IMGheight);
        n_frames = nFrames;

        currFrame = 0;

        frameDelay = new Delay(100); //TODO: change to parameter
        frameDelay.restart();
        isStopped = false;
    }

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

    public void setFrameDelay(int time_ms) {
        frameDelay.restart(time_ms);
    }

    public void nextFrame() {
        frameDelay.start();

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
