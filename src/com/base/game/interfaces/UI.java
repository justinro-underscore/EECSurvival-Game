package com.base.game.interfaces;

import com.base.engine.GameObject;
import com.base.game.Game;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class UI extends GameObject
{
    private Rectangle healthBar;
    private int healthBarWidth;

    public UI(int health)
    {
        healthBar = new Rectangle(10, 10, health * 5, 20);
        healthBarWidth = health * 5;
    }

    public void update()
    {
        healthBar.width = Game.game.getHealth(true) * 5;
    }

    public void render()
    {
        // Health remaining
        glColor4f(1, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
        glVertex2f(healthBar.x, healthBar.y);
        glVertex2f(healthBar.x + healthBar.width, healthBar.y);
        glVertex2f(healthBar.x + healthBar.width, healthBar.y + healthBar.height);
        glVertex2f(healthBar.x, healthBar.y + healthBar.height);
        glEnd();

        // Health taken
        glColor4f(0, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(healthBar.x + healthBar.width, healthBar.y);
            glVertex2f(healthBar.x + healthBarWidth, healthBar.y);
            glVertex2f(healthBar.x + healthBarWidth, healthBar.y + healthBar.height);
            glVertex2f(healthBar.x + healthBar.width, healthBar.y + healthBar.height);
        glEnd();

        // Health bar outline
        glLineWidth(5);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4f(0, 0, 1, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(healthBar.x - 2, healthBar.y - 2);
            glVertex2f(healthBar.x + healthBarWidth + 2, healthBar.y - 2);
            glVertex2f(healthBar.x + healthBarWidth + 2, healthBar.y + healthBar.height + 2);
            glVertex2f(healthBar.x - 2, healthBar.y + healthBar.height + 2);
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }
}
