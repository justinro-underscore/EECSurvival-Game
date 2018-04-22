package com.base.game.scenes;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.game.utilities.Delay;

import java.awt.image.BufferedImage;

//TODO: hook up with sprite sheet for text
public class Dialog {
    private String content;
    private int length;
    private Delay wordDelay;
    private Sprite currDialog;
    private BufferedImage img;

    public Dialog(String content) {
        this.content = content;

        length = 1;
        wordDelay = new Delay(100);
        wordDelay.restart();

        //TODO: remove this
        currDialog = new Sprite(500, 100, "");
    }

    public boolean isOver() {
        return (content.length() - 1 == length);
    }

    public void update() {
        if (length < content.length() - 1 && wordDelay.isOver()) {
            length++;

            wordDelay.start();
        }
    }

    public void render() {
        if (currDialog != null)
            currDialog.render(Display.getWidth() / 2 - 250, 80);
    }
}
