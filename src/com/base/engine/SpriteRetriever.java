package com.base.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//From https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system

public class SpriteRetriever {

    //private static BufferedImage spriteSheet;
    private static int TILE_WIDTH;
    private static int TILE_HEIGHT;
    private static Sprite frames[];

    private TextureLoader loader;

    /**
     *
     *
     */
    public SpriteRetriever()
    {

    }

    /**
     * Turns the large sprite sheet into a buffered image
     * @param file the file name of the sprite sheet
     * @return The sprite sheet in the form of a buffered image.
     */
    public static Sprite[] loadSprite(String file, int x, int y, int theframes, int width, int height)
    {
        frames=new Sprite[theframes];
        TILE_HEIGHT = height;
        TILE_WIDTH = width;

        BufferedImage spriteSheet = null;

        try {
            spriteSheet = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(spriteSheet==null)
        {
        }
        try {
            for (int i = 0; i < theframes; i++) {
                System.out.println(i);



                frames[i] = new Sprite(TextureLoader.loadTexture(spriteSheet.getSubimage( x+i *TILE_WIDTH,  y, TILE_WIDTH, TILE_HEIGHT)));

            }


        }
        catch (IOException e)
        {

            e.printStackTrace();
        }

        //sprite we are going to use in an animation
        return(frames);

    }

    /**
     * Creates a subimage from a spriteSheet
     * @param xGrid the x coordinate of a cell
     * @param yGrid the y coordinate of a cell
     * @param spriteSheet the big ole sprite sheet
     * @return the sprite to be put in an animation
     */
    //Takes in a sprite sheet and the coordinates of one cell on the sheet and creates a subimage, binds
    //it to a texture ID, generates a sprite and returns that sprite so it can be loaded into an animation.

}
