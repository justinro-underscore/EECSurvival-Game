package com.base.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Adapted from https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
 * Retrives a sprite from a sprite sheet by calling texture renderer
 */
public class SpriteRetriever {

    /**
     * Turns the large sprite sheet into a buffered image
     * @param file the file name of the sprite sheet
     * @return The sprite sheet in the form of a buffered image.
     */
    public static Sprite[] loadSprite(String file, int x, int y, int theframes, int width, int height)
    {
        Sprite[] frames = new Sprite[theframes];
        int TILE_HEIGHT = height;
        //private static BufferedImage spriteSheet;
        int TILE_WIDTH = width;

        BufferedImage spriteSheet = null;

        try {
            spriteSheet = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < theframes; i++) {
                frames[i] = new Sprite(TextureLoader.loadTexture(spriteSheet.getSubimage( x+i * TILE_WIDTH,  y, TILE_WIDTH, TILE_HEIGHT)));
            }
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }

        //sprite we are going to use in an animation
        return(frames);
    }
}
