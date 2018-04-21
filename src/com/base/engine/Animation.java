package com.base.engine;

import com.base.game.utilities.Delay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {
    private Sprite[] frames;
    private int currFrame;
    private int n_frames;
    private Delay frameDelay;
    private boolean isStopped;
    private SpriteRetriever retriever;

    /**
     * Initializes the animation
     * @param nFrames how many sprites will make up the animation
     * @param TILE_SIZE the size of the tile in the given spritesheet.
     */
    public Animation(int nFrames, int TILE_SIZE) {
        frames = new Sprite[nFrames];
        n_frames = nFrames;
        currFrame = 0;
        frameDelay = new Delay(200); //TODO do we want to make this variable?
        frameDelay.restart();
        retriever = new SpriteRetriever(TILE_SIZE);
        isStopped = false;
    }

    /**
     * Adds a new sprite to the animation
     * @param xCord the X coordinate of the sprite in the sprite sheet.
     * @param yCord the Y coordinate of the sprite in the sprite sheet.
     * @param fileURI the sprite sheet.
     */
    private void addSprite(int xCord, int yCord, String fileURI)
    {
        //TODO Change to general file path
        String filePath = "res/testWalk/" + fileURI;
        for(int i = 0; i < n_frames; i++)
        {
            //if we haven't given this spot in the animation a sprite, give it one
            //(Make sure to add them in the right order)
            if(frames[i] == null)
            {
                frames[i] = retriever.getSprite(xCord, yCord, retriever.loadSprite(filePath));
            }
        }
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

    public void render(float xPos, float yPos) {
        frames[currFrame].render(xPos, yPos);
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

    private void nextFrame() {
        currFrame++;
        if (currFrame == frames.length) {
            currFrame = 0;
        }
    }
}
