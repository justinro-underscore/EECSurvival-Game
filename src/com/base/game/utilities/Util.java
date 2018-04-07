package com.base.game.utilities;

import com.base.engine.Display;

public class Util {
    /**
     * Determine whether an object is off screen
     * @param xPos the x-position of the object in question
     * @param yPos the y-position of the object in question
     * @param width the width of the object in question
     * @param height the height of the object in question
     * @return whether or not the object is off screen
     */
    public static boolean offScreen(float xPos, float yPos, int width, int height) {
        return (xPos + width / 2.0f <= 0 || xPos - width / 2.0f >= Display.getWidth()
                || yPos + height / 2.0f <= 0 || yPos - height / 2.0f >= Display.getHeight());
    }
}
