package com.base.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//From https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system

public class SpriteRetriever {

    //private static BufferedImage spriteSheet;
    private static int TILE_SIZE;

    public SpriteRetriever(int TILE_SIZE)
    {
        this.TILE_SIZE = TILE_SIZE;
    }

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    /**
     * Creates a subimage from a spriteSheet
     * @param xGrid the x coordinate of a cell
     * @param yGrid the y coordinate of a cell
     * @param spriteSheet the big ole sprite sheet
     * @return hands back a subimage of the sprite sheet cell
     */
    //Takes in a sprite sheet and the coordinates of one cell on the sheet and creates a subimage
    public static BufferedImage getSprite(int xGrid, int yGrid, BufferedImage spriteSheet) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("testSpriteSheet");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
