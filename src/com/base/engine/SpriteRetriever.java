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
    private TextureLoader loader;

    /**
     *
     * @param TILE_WIDTH asdasd
     * @param TILE_HEIGHT a sdasd
     */
    public SpriteRetriever(int TILE_WIDTH, int TILE_HEIGHT)
    {
        this.TILE_WIDTH = TILE_WIDTH;
        this.TILE_HEIGHT = TILE_HEIGHT;
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
            textureID = loader.loadTexture(spriteSheet.getSubimage(xGrid * TILE_WIDTH, yGrid * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT));
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
