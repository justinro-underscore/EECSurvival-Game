package com.base.game.utilities;

import com.base.engine.Display;

public class Util {
    public static boolean offScreen(float xPos, float yPos, int width, int height) {
        return (xPos + width / 2.0f <= 0 || xPos - width / 2.0f >= Display.getWidth()
                || yPos + height / 2.0f <= 0 || yPos - height / 2.0f >= Display.getHeight());
    }
}
