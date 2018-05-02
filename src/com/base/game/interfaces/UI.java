package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.Game;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class UI extends Interface // TODO Add all UI functionality
{
    private Rectangle playerHealthBar;
    private Animation theDigit;
    private float playerHealthFactor;
    private SpriteRetriever retriever;
    private Rectangle bossHealthBar;
    private float bossHealthFactor;
    private final int PLAYER_HEALTH_BAR_WIDTH = Display.getWidth() / 2 - 20;
    private final int BOSS_HEALTH_BAR_WIDTH = Display.getWidth() - 40;

    private Sprite[] digits; // Holds all digits
    private Sprite[] playerHealthDigits; // Holds the player's health as digits

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

        digits = new Sprite[10];

        // Populate the digits array with pictures of the digits


            theDigit= new Animation(10, 0,0,"res/digits/digits.png",50,50,25,25);
            for(int i = 0; i < 10; i++)
            {
                digits[i] = theDigit.getCurrentFrame();
                theDigit.nextFrame();
            }


 

        playerHealthDigits = new Sprite[3];
        // Initialize player's health to nothing using the default index

        for(int i = 0; i < 3; i++)
        {
            playerHealthDigits[i] = theDigit.getCurrentFrame();
            playerHealthDigits[i].setAlpha(0.0f);
        }
    }

    /**
     * Update the player and boss health based off of damage and consumable input
     */
    public void update()
    {
        int playerHealth = Game.game.getHealth(true);
        playerHealthBar.width = (int)(playerHealth * playerHealthFactor);
        bossHealthBar.width = (int)(Game.game.getHealth(false) * bossHealthFactor);

        setPlayerHealthDigits(playerHealth);
    }

    /**
     * Sets the player's health as text
     * @param playerHealth Health of the player
     */
    private void setPlayerHealthDigits(int playerHealth)
    {
        if(playerHealth / 100 != 0)
        {
            playerHealthDigits[0] = digits[playerHealth / 100]; // If the player's health is not a multiple of 100, don't show
            playerHealthDigits[0].setAlpha(1.0f);
        }
        else
            playerHealthDigits[0].setAlpha(1.0f);

        if((playerHealth / 100 != 0) || (playerHealth / 10 != 0))
        {
            playerHealthDigits[1] = digits[(playerHealth % 100) / 10];
            playerHealthDigits[1].setAlpha(1.0f);
        }
        else
            playerHealthDigits[1].setAlpha(0.0f);

        playerHealthDigits[2] = digits[playerHealth % 10];
    }

    /**
     * Render the health bars
     */
    public void render()
    {
        showPlayerHealth();
        showBossHealth();
        showPlayerHealthNumber();
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

    /**
     * Display the player health as text
     */
    private void showPlayerHealthNumber()
    {
        // Background on the text
//        glColor4f(1, 1, 1, 0.0f);
//        glBegin(GL11.GL_QUADS);
//            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 20, playerHealthBar.y);
//            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 95, playerHealthBar.y);
//            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 95, playerHealthBar.y + 25);
//            glVertex2f(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + 20, playerHealthBar.y + 25);
//        glEnd();

        // Shows the player health digits
      for(int i = 1; i < 3; i++)
        {
            playerHealthDigits[i].render(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + (25 * i), playerHealthBar.y,25,25);
        }
    }
}