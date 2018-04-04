package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.engine.TextureLoader;
import com.base.engine.Vector2f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu extends Interface {
    // TODO: implement these as enums
    private int startButtonTextureID;
    private int startButtonTextureID_press;
    private int startButtonTextureID_release;

    private int quitButtonTextureID;
    private int quitButtonTextureID_press;
    private int quitButtonTextureID_release;

    private Rectangle startButton;
    private Rectangle quitButton;

    // TODO: implement button delay of 10ms

    @Override
    public void init(String fileName) {
        super.init(fileName);

        startButton = new Rectangle(Display.getWidth()/2 - 450,Display.getHeight()/2 - 40,400,80);
        quitButton = new Rectangle(Display.getWidth()/2 + 50,Display.getHeight()/2 - 40,400,80);

        generateButtons();
        startButtonTextureID = startButtonTextureID_release;
        quitButtonTextureID = quitButtonTextureID_release;
    }

    private void generateButtons() {
        try {
            startButtonTextureID_release = TextureLoader.loadTexture("./res/start_release.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            startButtonTextureID_press = TextureLoader.loadTexture("./res/start_press.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            quitButtonTextureID_release = TextureLoader.loadTexture("./res/quit_release.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            quitButtonTextureID_press = TextureLoader.loadTexture("./res/quit_press.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        Vector2f mousePos = InputHandler.getMousePos();
        if (startButton.contains(mousePos.x, mousePos.y)) {
            startButtonTextureID = startButtonTextureID_press;
        } else {
            startButtonTextureID = startButtonTextureID_release;
        }

        if (InputHandler.isMouseReleased() && startButtonTextureID == startButtonTextureID_press) {
            startButtonTextureID = startButtonTextureID_release;
            Display.start();
        }

        if (quitButton.contains(mousePos.x, mousePos.y)) {
            quitButtonTextureID = quitButtonTextureID_press;
        } else {
            quitButtonTextureID = quitButtonTextureID_release;
        }

        if (InputHandler.isMouseReleased() && quitButtonTextureID == quitButtonTextureID_press) {
            quitButtonTextureID = quitButtonTextureID_release;
            Display.quit();
        }
    }

    @Override
    public void render() {
        super.render();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        drawStart();
        drawQuit();

        glDisable(GL_BLEND);
    }

    private void drawStart() {
        glBindTexture(GL_TEXTURE_2D, startButtonTextureID);

        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(0, 1);
            glVertex2f(startButton.x, startButton.y);

            glTexCoord2f(1, 1);
            glVertex2f(startButton.x + startButton.width, startButton.y);

            glTexCoord2f(1, 0);
            glVertex2f(startButton.x + startButton.width, startButton.y + startButton.height);

            glTexCoord2f(0, 0);
            glVertex2f(startButton.x, startButton.y + startButton.height);
        }
        glEnd();
    }

    private void drawQuit() {
        glBindTexture(GL_TEXTURE_2D, quitButtonTextureID);

        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(0, 1);
            glVertex2f(quitButton.x, quitButton.y);

            glTexCoord2f(1, 1);
            glVertex2f(quitButton.x + quitButton.width, quitButton.y);

            glTexCoord2f(1, 0);
            glVertex2f(quitButton.x + quitButton.width, quitButton.y + quitButton.height);

            glTexCoord2f(0, 0);
            glVertex2f(quitButton.x, quitButton.y + quitButton.height);
        }
        glEnd();
    }
}
