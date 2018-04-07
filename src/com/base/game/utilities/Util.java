package com.base.game.utilities;

import com.base.engine.Display;
import com.base.engine.GameObject;

public class Util {
    /**
     * Determine whether an object is off screen
     * @param obj the object that may or may not be off screen
     * @param width the width of the object in question
     * @param height the height of the object in question
     * @return whether or not the object is off screen
     */
    public static boolean offScreen(GameObject obj, int width, int height) {
        return (obj.getX() + width / 2.0f <= 0 || obj.getX() - width / 2.0f >= Display.getWidth()
                || obj.getY() + height / 2.0f <= 0 || obj.getY() - height / 2.0f >= Display.getHeight());
    }
}
