package com.base.engine;

import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
    private float a; // Alpha value - Only works with texture

    private int width;
    private int height;
    private int textureID;

    public Sprite(int width, int height, String imgPath) {
        a = 1.0f;
        this.width = width;
        this.height = height;

        if (!imgPath.equals(""))
        {
            try { textureID = TextureLoader.loadTexture(imgPath); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else
            textureID = -1;
    }

    public void render(float xPos, float yPos) {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            if(textureID != -1) {
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

                renderTex(textureID);

                glDisable(GL_BLEND);
            }
            else
                renderRBG();
        }
        glPopMatrix();
    }

    private void renderTex(int textureID) {
        glColor4f(1, 1, 1, a);
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(1, 0);
            glVertex2f(width , height);

            glTexCoord2f(0, 0);
            glVertex2f(0, height);

            glTexCoord2f(0, 1);
            glVertex2f(0, 0);

            glTexCoord2f(1, 1);
            glVertex2f(width, 0);
        }
        glEnd();
    }

    private void renderRBG() {
        glColor4f(1, 1, 1, a);

        glBegin(GL11.GL_QUADS);
        {
            glVertex2f(0, 0);
            glVertex2f(width, 0);
            glVertex2f(width, height);
            glVertex2f(0, height);
        }
        glEnd();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setAlpha(float a) {
        this.a = a;
    }
}
