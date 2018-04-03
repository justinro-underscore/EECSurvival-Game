package com.base.game.utilities;

/**
 * Adapted from rpg opengl tutorial series by TheBennyBox
 */
public class Time {
    private static final float DAMPING = 12000000f;

    private static long currTime;
    private static long lastTime;

    public static void init() {
        lastTime = getTime();
        currTime = getTime();
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static float getDeltaTime() {
        return (currTime - lastTime) / DAMPING;
    }

    public static void update() {
        lastTime = currTime;
        currTime = getTime();
    }
}
