package com.base.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//From https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system

public class SpriteRetriever {

    //private static BufferedImage spriteSheet;
    private static int TILE_SIZE;
    private TextureLoader loader;

    /*
    *  When you make the sprite retriever, having a variable TILE_SIZE lets
    *  us utilize multiple different sprite sheets if we don't happen to have
    *  a standardized sprite sheet.
    */
    public SpriteRetriever(int TILE_SIZE)
    {
        this.TILE_SIZE = TILE_SIZE;
    }

    /**
     * Turns the large sprite sheet into a buffered image
     * @param file the file name of the sprite sheet
     * @return The sprite sheet in the form of a buffered image.
     */
    public static BufferedImage loadSprite(String file)
    {

        BufferedImage spriteSheet = null;

        try
        {
            spriteSheet = ImageIO.read(new File(file));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return spriteSheet;
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
    public  Sprite getSprite(int xGrid, int yGrid, BufferedImage spriteSheet)
    {
        if (spriteSheet == null)
        {
            spriteSheet = loadSprite("testSpriteSheet");
        }
        int textureID = -1;
        try
        {
            textureID = loader.loadTexture(spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //sprite we are going to use in an animation
        Sprite sprite = new Sprite(textureID);
        return(sprite);
    }

}
