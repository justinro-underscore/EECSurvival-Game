package com.base.engine;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Adapted from LWJGL 3 tutorial: https://tutorialedge.net/java/lwjgl3/lwjgl-3-keyboard-input-handler-tutorial/
 * Handles the input from the user (mouse and keyboard)
 */
public class InputHandler {
    private static boolean[] keys = new boolean[65536];

    private static boolean isMousePressed = false;
    private static boolean isMouseReleased = false;

    private static double mouseXPos = 0;
    private static double mouseYPos = 0;

    /**
     * Callback for keyPress
     * @param window window to detect
     * @param key key that was detected
     * @param scancode scandcode of key
     * @param action action of key (pressed/released)
     * @param mods mods of the key
     */
    public void invokeKey(long window, int key, int scancode, int action, int mods) {
        if (key == -1) {
            return;
        }

        EventQueue.invokeCallback(new Event("Keyboard", Integer.toString(key), false));
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

    /**
     * Callback function for when mouse action is detected
     * @param window current window to detect events from
     * @param button button detected
     * @param action action of button (pressed/released)
     * @param mods mods of button
     */
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

    /**
     * forces a key down
     * @param keyCode key to be pressed
     */
    public static void forceKeyDown(int keyCode) {
        keys[keyCode] = true;
    }

    /**
     * forces a key to be released
     * @param keyCode key ot be released
     */
    public static void forceKeyRelease(int keyCode) {
        keys[keyCode] = false;
    }

    /**
     * forces a mouse click
     */
    public static void forceMouseDown() {
        isMousePressed = true;
        isMouseReleased = false;
    }

    /**
     * forces mouse release
     */
    public static void forceMouseReleased() {
        isMousePressed = false;
        isMouseReleased = true;
    }

    /**
     * clears all keys and mouse values
     */
    public static void clear() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }

        isMousePressed = false;
        isMouseReleased = false;
    }
}
