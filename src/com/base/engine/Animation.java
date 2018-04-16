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

    public Animation(String fileURI, int nFrames) {
        frames = new Sprite[nFrames];
        currFrame = 0;
        frameDelay = new Delay(200);
        frameDelay.restart();

        isStopped = false;
        loadSprites(fileURI);
    }

    private void loadSprites(String fileURI) {
        BufferedImage img = null;
        String filePath = "res/" + fileURI;
        for (int i = 0; i < frames.length; i++) {
            try {
                img = ImageIO.read(new File(filePath + "_" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            frames[i] = new Sprite(img.getWidth(), img.getHeight(), filePath + "_" + i + ".png");
        }

        n_frames = frames.length;
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
