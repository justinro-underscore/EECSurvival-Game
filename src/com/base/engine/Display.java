package com.base.engine;

import com.base.game.Game;
import com.base.game.interfaces.MainMenu;
import com.base.game.utilities.Time;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.io.File;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Adapted from LWJGL getting started: https://www.lwjgl.org/guide
 * Creates a display using openGl and initializes the game
 * Manages the gameloop
 */
public class Display {
    // The window handle
    private static long window;

    private static String title;
    private static int width;
    private static int height;

    private InputHandler inputHandler;

    /**
     * run the display
     * @param name of the game
     */
    public void run(String name) {
        Display.title = name;

        Time.init();
        EventQueue.init();
        init();
        Audio.init();
        gameLoop();

        Audio.cleanUp();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * initialize the game
     */
    private void initGame() {
        Game.game = new Game();
        inputHandler = new InputHandler();
    }

    /**
     * initialize everything
     */
    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable

        // Create the window
        GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        width = mode.width();

        height = mode.height();

        window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetWindowAspectRatio(window, width, height);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            inputHandler.invokeKey(window, key, scancode, action, mods);
        });

        glfwSetCursorPosCallback(window, (long window, double xPos, double yPos) -> {
            inputHandler.invokeMouseMovement(window, xPos, yPos);
        });

        glfwSetMouseButtonCallback(window, (long window, int button, int action, int mods) -> {
            inputHandler.invokeMouseButton(window, button, action, mods);
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    /**
     * The generic game loop
     */
    private void gameLoop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, 0, height, 1, -1);
        glMatrixMode(GL_MODELVIEW);

       initGame();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        while ( !glfwWindowShouldClose(window) ) {
            Time.update();
            EventQueue.update();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            Game.game.run();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    /**
     * quit the game
     */
    public static void quit() {
        glfwSetWindowShouldClose(window, true);
    }

    /**
     * get the height of the screen
     * @return the height of the screen
     */
    public static int getHeight() {
        return height;
    }

    /**
     * get the width of the screen
     * @return width of the screen
     */
    public static int getWidth() {
        return width;
    }
}
