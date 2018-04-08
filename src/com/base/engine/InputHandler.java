package com.base.engine;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Adapted from LWJGL 3 tutorial: https://tutorialedge.net/java/lwjgl3/lwjgl-3-keyboard-input-handler-tutorial/
 */
public class InputHandler {
    private static boolean[] keys = new boolean[65536];

    private static boolean isMousePressed = false;
    private static boolean isMouseReleased = false;

    private static double mouseXPos = 0;
    private static double mouseYPos = 0;

    public void invokeKey(long window, int key, int scancode, int action, int mods) {
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

    /**
     * Checks to see if there is any key being pressed at the moment
     * @return true if any key is down, false if there is nothing being pressed
     */
    public static boolean isAnyKeyDown()
    {
        for(boolean key : keys)
        {
            if(key) // If one key is down...
                return true;
        }
        return false;
    }

    /**
     * Checks to see if there is any key being pressed at the moment (ignoring one keycode)
     * @param keycodeIgnore The keycode to be ignored
     * @return true if any key is down, false if there is nothing being pressed
     */
    public static boolean isAnyKeyDown(int keycodeIgnore)
    {
        for(int i = 0; i < keys.length; i++)
        {
            if(keys[i] && (i != keycodeIgnore))
                return true;
        }
        return false;
    }
    
    public void invokeMouseButton(long window, int button, int action, int mods) {
        if (button != GLFW_MOUSE_BUTTON_1)
            return;

        isMouseReleased = false;
        isMousePressed = false;

        if (action == GLFW_PRESS) {
            isMousePressed = true;
        }else if (action == GLFW_RELEASE) {
            isMouseReleased = true;
        }
    }

    /**
     * invoke mouse movement
     * @param window the window
     * @param xPos the x position
     * @param yPos the y position
     */
    public void invokeMouseMovement(long window, double xPos, double yPos) {
        mouseXPos = xPos;
        mouseYPos = Math.abs(yPos- Display.getHeight());
    }

    /**
     * is the mouse down
     * @return true if the mouse is down
     */
    public static boolean isMouseDown() {
        return isMousePressed;
    }

    /**
     * is the mouse released
     * @return if the the mouse is released
     */
    public static boolean isMouseReleased() {
        return isMouseReleased;
    }

    /**
     * get the mouse position
     * @return the mouse position
     */
    public static Vector2f getMousePos() {
        return new Vector2f((float) mouseXPos, (float) mouseYPos);
    }
}
