package com.base.engine;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.InputHandler;
import com.base.engine.Sprite;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainMenu {
    public void render() {
        glColor3f(1.0f, 0.0f, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(0, 0);
            glVertex2f(Display.getWidth(), 0);
            glVertex2f(Display.getWidth(), Display.getHeight());
            glVertex2f(0, Display.getHeight());
        glEnd();
    }
}
