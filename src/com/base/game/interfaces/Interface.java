package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.TextureLoader;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public abstract class Interface {
    private int textureID;

    public void init(String fileName) {
        try {
            textureID = TextureLoader.loadTexture(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        glColor3f(1.0f, 1.0f, 1.0f);
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(1, 1);
            glVertex2f(0, 0);

            glTexCoord2f(0, 1);
            glVertex2f(Display.getWidth(), 0);

            glTexCoord2f(0, 0);
            glVertex2f(Display.getWidth(), Display.getHeight());

            glTexCoord2f(1, 0);
            glVertex2f(0, Display.getHeight());
        }
        glEnd();
    }
}
