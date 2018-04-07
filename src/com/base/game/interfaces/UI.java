package com.base.game.interfaces;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.game.Game;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class UI extends Interface // TODO Add all UI functionality
{
    private Rectangle playerHealthBar;
    private float playerHealthFactor;
    private Rectangle bossHealthBar;
    private float bossHealthFactor;
    private final int PLAYER_HEALTH_BAR_WIDTH = Display.getWidth() / 2 - 20;
    private final int BOSS_HEALTH_BAR_WIDTH = Display.getWidth() - 40;

    /**
     * Create the user interface
     * @param playerHealth the player's health
     * @param bossHealth the boss's health
     */
    public UI(int playerHealth, int bossHealth)
    {
        playerHealthBar = new Rectangle(10, 10, PLAYER_HEALTH_BAR_WIDTH, 20);
        playerHealthFactor = (float)PLAYER_HEALTH_BAR_WIDTH / playerHealth;
        bossHealthBar = new Rectangle(20, Display.getHeight() - 60, BOSS_HEALTH_BAR_WIDTH, 40);
        bossHealthFactor = (float)BOSS_HEALTH_BAR_WIDTH / bossHealth;
    }

    /**
     * Update the player and boss health based off of damage and consumable input
     */
    public void update()
    {
        playerHealthBar.width = (int)(Game.game.getHealth(true) * playerHealthFactor);
        bossHealthBar.width = (int)(Game.game.getHealth(false) * bossHealthFactor);
    }

    /**
     * Render the health bars
     */
    public void render()
    {
        showPlayerHealth();
        showBossHealth();
    }

    /**
     * Display the player health
     */
    private void showPlayerHealth()
    {
        // Health remaining
        glColor4f(1, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(playerHealthBar.x, playerHealthBar.y);
            glVertex2f(playerHealthBar.x + playerHealthBar.width, playerHealthBar.y);
            glVertex2f(playerHealthBar.x + playerHealthBar.width, playerHealthBar.y + playerHealthBar.height);
            glVertex2f(playerHealthBar.x, playerHealthBar.y + playerHealthBar.height);
        glEnd();

        // Health taken
        glColor4f(0, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(playerHealthBar.x + playerHealthBar.width, playerHealthBar.y);
            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH, playerHealthBar.y);
            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH, playerHealthBar.y + playerHealthBar.height);
            glVertex2f(playerHealthBar.x + playerHealthBar.width, playerHealthBar.y + playerHealthBar.height);
        glEnd();

        // Health bar outline
        glLineWidth(5);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4f(0, 0, 1, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(playerHealthBar.x - 2, playerHealthBar.y - 2);
            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 2, playerHealthBar.y - 2);
            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 2, playerHealthBar.y + playerHealthBar.height + 2);
            glVertex2f(playerHealthBar.x - 2, playerHealthBar.y + playerHealthBar.height + 2);
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }

    /**
     * Display the boss health
     */
    private void showBossHealth()
    {
        // Health remaining
        glColor4f(1, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(bossHealthBar.x, bossHealthBar.y);
            glVertex2f(bossHealthBar.x + bossHealthBar.width, bossHealthBar.y);
            glVertex2f(bossHealthBar.x + bossHealthBar.width, bossHealthBar.y + bossHealthBar.height);
            glVertex2f(bossHealthBar.x, bossHealthBar.y + bossHealthBar.height);
        glEnd();

        // Health taken
        glColor4f(0, 0, 0, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(bossHealthBar.x + bossHealthBar.width, bossHealthBar.y);
            glVertex2f(bossHealthBar.x + BOSS_HEALTH_BAR_WIDTH, bossHealthBar.y);
            glVertex2f(bossHealthBar.x + BOSS_HEALTH_BAR_WIDTH, bossHealthBar.y + bossHealthBar.height);
            glVertex2f(bossHealthBar.x + bossHealthBar.width, bossHealthBar.y + bossHealthBar.height);
        glEnd();

        // Health bar outline
        glLineWidth(5);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4f(0, 0, 1, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(bossHealthBar.x - 2, bossHealthBar.y - 2);
            glVertex2f(bossHealthBar.x + BOSS_HEALTH_BAR_WIDTH + 2, bossHealthBar.y - 2);
            glVertex2f(bossHealthBar.x + BOSS_HEALTH_BAR_WIDTH + 2, bossHealthBar.y + bossHealthBar.height + 2);
            glVertex2f(bossHealthBar.x - 2, bossHealthBar.y + bossHealthBar.height + 2);
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }
}
