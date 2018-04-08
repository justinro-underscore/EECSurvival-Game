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

    /**
     * Invoked every time a key is pressed
     * @param window window that detected the key
     * @param key key that was pressed
     * @param scancode scan code of key
     * @param action type of key action (ie. pressed, released)
     * @param mods mods
     */
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
     * Invoked every time a mouse button is pressed
     * @param window window that detected the mouse button
     * @param button button that was pressed
     * @param action type of action (ie. pressed, released)
     * @param mods mods
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
     * Invoked every time the mouse is moved
     * @param window window that detected mouse movement
     * @param xPos xPos of mouse (0,0 starts at top left corner of screen)
     * @param yPos yPos of mouse (0,0 starts at top left corner of screen)
     */
    public void invokeMouseMovement(long window, double xPos, double yPos) {
        mouseXPos = xPos;
        mouseYPos = Math.abs(yPos- Display.getHeight());
    }

    /**
     * Checks if mouse is pressed
     * @return bool if mouse is pressed
     */
    public static boolean isMouseDown() {
        return isMousePressed;
    }

    /**
     * Checks if mouse is released
     * @return bool if mouse is released
     */
    public static boolean isMouseReleased() {
        return isMouseReleased;
    }

    /**
     * Returns current mouse position
     * @return vector2f of mouse position (0,0 is shifted to same as glfw - (0,0) is at bottom left corner)
     */
    public static Vector2f getMousePos() {
        return new Vector2f((float) mouseXPos, (float) mouseYPos);
    }
}
