package com.base.engine;

import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Render {
    private float a; // Alpha value - deals with transparency (Only works with images)

    /**
     * Creates a render from parameters
     */
    public Render() {
        a = 1.0f; // Initial value is fully opaque
    }

    /**
     * Renders the render as an image on screen
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render TODO IS IT CENTERED?
     */
    public void render(int height, int width, float xPos, float yPos, int textureID) {
        glPushMatrix(); // Create a new image matrix
        {
            glTranslatef(xPos, yPos, 0); // Translate so it is easier to create the image at a coordinate
            if(textureID != -1) { // If the render uses an image...
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

                renderTex(height, width, textureID); // Render the image

                glDisable(GL_BLEND);
            }
            else
                renderRBG(height, width); // If no image is specified, create a standard white rectangle
        }
        glPopMatrix();
    }

    /**
     * Renders the render to the screen WITH AN IMAGE
     * @param textureID The ID of the image being loaded
     */
    private void renderTex(int height, int width, int textureID) {
        glColor4f(1, 1, 1, a); // This is so we can change the opacity of the render
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL11.GL_QUADS); // Create the render
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

    /**
     * Renders the render to the screen WITHOUT AN IMAGE
     */
    private void renderRBG(int height, int width) {
        glColor4f(1, 1, 1, a); // Can change this directly if you want different default color

        glBegin(GL11.GL_QUADS); // Create the render
        {
            glVertex2f(0, 0);
            glVertex2f(width, 0);
            glVertex2f(width, height);
            glVertex2f(0, height);
        }
        glEnd();
    }

    /**
     * Sets the render's alpha value
     * @param a the desired alpha value
     * @return whether or not the set worked
     */
    public boolean setAlpha(float a) {
        if(a >= 0.0f && a <= 1.0f) {
            this.a = a;
            return true;
        }
        return false;
    }
}
