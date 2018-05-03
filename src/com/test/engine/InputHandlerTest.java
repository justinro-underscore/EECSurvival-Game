package com.test.engine;

import com.base.engine.InputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Tests the functionality of the input handler class
 */
class InputHandlerTest {
    /**
     * Clears all input before each test
     */
    @BeforeEach
    void setUp() {
        InputHandler.clear();
    }

    /**
     * Tests if key is pressed
     */
    @Test
    void isKeyDown() {
        InputHandler.forceKeyDown(GLFW_KEY_W);
        assertTrue(InputHandler.isKeyDown(GLFW_KEY_W));

        InputHandler.forceKeyRelease(GLFW_KEY_W);
        assertFalse(InputHandler.isKeyDown(GLFW_KEY_W));

        assertFalse(InputHandler.isKeyDown(GLFW_KEY_ESCAPE));
    }

    /**
     * Tests if any key has been pressed
     */
    @Test
    void isAnyKeyDown() {
        InputHandler.forceKeyDown(GLFW_KEY_W);
        assertTrue(InputHandler.isAnyKeyDown());
    }

    /**
     * Tests if any key is down ignoring a given key code
     */
    @Test
    void isAnyKeyDown1() {
        InputHandler.forceKeyDown(GLFW_KEY_W);
        assertFalse(InputHandler.isAnyKeyDown(GLFW_KEY_W));

        InputHandler.forceKeyDown(GLFW_KEY_W);
        assertTrue(InputHandler.isAnyKeyDown(GLFW_KEY_ESCAPE));
    }

    /**
     * Checks if mouse is pressed
     */
    @Test
    void isMouseDown() {
        InputHandler.forceMouseDown();
        assertTrue(InputHandler.isMouseDown());

        InputHandler.forceMouseReleased();
        assertFalse(InputHandler.isMouseDown());
    }

    /**
     * Checks if mouse is released
     */
    @Test
    void isMouseReleased() {
        InputHandler.forceMouseDown();
        assertFalse(InputHandler.isMouseReleased());

        InputHandler.forceMouseReleased();
        assertTrue(InputHandler.isMouseReleased());
    }
}