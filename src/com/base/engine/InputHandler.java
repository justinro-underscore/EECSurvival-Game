package com.base.engine;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Adapted from LWJGL 3 tutorial: https://tutorialedge.net/java/lwjgl3/lwjgl-3-keyboard-input-handler-tutorial/
 */
public class InputHandler extends GLFWKeyCallback {
    public static boolean[] keys = new boolean[65536];

    @Override
    public String getSignature() {
        return null;
    }

    @Override
    public void callback(long args) {

    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key == -1) {
            return;
        }
        keys[key] = action != GLFW_RELEASE;
    }

    /**
     * Static boolean method to detect if a given key is pressed
     * @param keycode Key to test
     * @return true if key is pressed down
     */
    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}
