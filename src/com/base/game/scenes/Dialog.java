package com.base.game.scenes;

import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.engine.Sprite;
import com.base.engine.TextRenderer;
import com.base.engine.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

public class Dialog {
    private Sprite background; // The background to the text
    private TextRenderer text; // The text that is displayed
    private boolean isOver; // If the dialog should be removed

    /**
     * Creates a new dialog box with a set size
     * @param content The content of the dialog box
     * @param fontSize The size of the font (1 to 3 [See TextRenderer for more info])
     */
    public Dialog(String content, int fontSize)
    {
        background = SpriteRetriever.loadSprite("res/assets/white.png", 0, 0, 1, 50, 50)[0];

        text = new TextRenderer(content, 950, 100, false, fontSize, true, Display.getWidth() / 2 - 475, 125);
        text.startTypewriterDelay(); // Start showing the text
        isOver = false;
    }

    /**
     * If the dialog should be closed
     * @return Whether or not the dialog box should be closed
     */
    public boolean isOver()
    {
        return isOver;
    }

    /**
     * Updates the dialog box
     */
    public void update()
    {
        if(InputHandler.isKeyDown(GLFW_KEY_ENTER) && text.getIsOver()) // If the user presses enter, exit the dialog box
            isOver = true;
        text.update(); // Update the text
    }

    /**
     * Renders the dialog box
     */
    public void render()
    {
        background.render(Display.getWidth() / 2 - 500, 100, 1000, 150);
        text.render();
    }
}
