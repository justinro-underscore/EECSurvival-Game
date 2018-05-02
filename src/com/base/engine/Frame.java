package com.base.engine;

import java.awt.image.BufferedImage;

//From https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system

//A frame is just a texture ID
public class Frame {

    private int frame;
    private int duration;

    public Frame(int frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
