package com.base.game.utilities;

/**
 * Adapted from rpg opengl tutorial series by TheBennyBox
 */
public class Time {
    private static final float DAMPING = 12000000f;

    private static long currTime;
    private static long lastTime;

    /**
     * Initialize the time
     */
    public static void init() {
        lastTime = getTime();
        currTime = getTime();
    }

    /**
     * Get the current time
     * @return the current time in milliseconds
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * Get the delta time
     * @return the delta time
     */
    public static float getDeltaTime() {
        return (currTime - lastTime) / DAMPING;
    }

    /**
     * Update the current time
     */
    public static void update() {
        lastTime = currTime;
        currTime = getTime();
    }
}
