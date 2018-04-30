package com.base.engine;

import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
    private float a; // Alpha value - deals with transparency (Only works with images)

    private int width;
    private int height;
    private int textureID; // The ID associated with the sprite's image (texture)

    /**
     * Creates a sprite from parameters
     * @param textureID the texture that will be rendered.
     */
    public Sprite(int textureID) {
        a = 1.0f; // Initial value is fully opaque
        //this.width = width;
        //this.height = height;
        this.textureID = textureID;
    }

    /**
     * Renders the sprite as an image on screen
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite TODO IS IT CENTERED?
     */
    public void render(float xPos, float yPos, int width, int height) {
        this.width=width;
        this.height=height;
        glPushMatrix(); // Create a new image matrix
        {
            glTranslatef(xPos, yPos, 0); // Translate so it is easier to create the image at a coordinate
            if(textureID != -1) { // If the sprite uses an image...
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

                renderTex(textureID); // Render the image

                glDisable(GL_BLEND);
            }
            else
                renderRBG(); // If no image is specified, create a standard white rectangle
        }
        glPopMatrix();
    }

    /**
     * Renders the sprite to the screen WITH AN IMAGE
     * @param textureID The ID of the image being loaded
     */
    private void renderTex(int textureID) {
        glColor4f(1, 1, 1, a); // This is so we can change the opacity of the sprite
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL11.GL_QUADS); // Create the sprite
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
     * Renders the sprite to the screen WITHOUT AN IMAGE
     */
    private void renderRBG() {
        glColor4f(1, 1, 1, a); // Can change this directly if you want different default color

        glBegin(GL11.GL_QUADS); // Create the sprite
        {
            glVertex2f(0, 0);
            glVertex2f(width, 0);
            glVertex2f(width, height);
            glVertex2f(0, height);
        }
        glEnd();
    }

    /**
     * Get the sprite's width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the sprites width
     * @param width the width of the sprite
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Get the sprite's height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the sprites height
     * @param height the height of the sprite
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Sets the sprite's alpha value
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

    /**
     * Resets the texture to a different image
     * @param imgPath The file path to the new texture
     *
    public void setTexture(String imgPath) {
        if (!imgPath.equals("")) // If there is no imgPath, don't create a textureID
        {
            try { textureID = TextureLoader.loadTexture(imgPath); } // Load the image
            catch (IOException e) { e.printStackTrace(); }
        }
        else
            textureID = -1;
    }
    */
}
