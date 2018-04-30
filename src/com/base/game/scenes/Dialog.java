package com.base.game.scenes;

import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.engine.Sprite;
import com.base.engine.TextRenderer;
import com.base.game.utilities.Delay;
import jdk.internal.util.xml.impl.Input;

import java.awt.image.BufferedImage;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

public class Dialog {
    private Sprite background;
    private TextRenderer text;
    private boolean isOver;

    public Dialog(String content, int fontSize)
    {
        background = new Sprite(1000, 150, "");
        text = new TextRenderer(content, 950, 100, fontSize, true, Display.getWidth() / 2 - 475, 125);
        text.startTypewriterDelay();
        isOver = false;
    }

    public boolean isOver()
    {
        return isOver;
    }

    public void update()
    {
        if(InputHandler.isKeyDown(GLFW_KEY_ENTER) && text.getIsOver())
            isOver = true;
        text.update();
    }

    public void render()
    {
        background.render(Display.getWidth() / 2 - 500, 100);
        text.render();
    }
}
