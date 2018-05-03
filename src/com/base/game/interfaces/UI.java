package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.Game;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class UI extends Interface
{
    private Sprite playerHealthBarBack;
    private Sprite playerHealthBarFull;
    private Sprite playerHealthBarCurr;
    private Sprite bossHealthBarBack;
    private Sprite bossHealthBarFull;
    private Sprite bossHealthBarCurr;
    private Rectangle playerHealthBar;
    private Animation theDigit;
    private float playerHealthFactor;
    private Rectangle bossHealthBar;
    private float bossHealthFactor;
    private final int PLAYER_HEALTH_BAR_WIDTH = Display.getWidth() / 2 - 20;
    private final int BOSS_HEALTH_BAR_WIDTH = Display.getWidth() - 40;

    private Sprite[] digits; // Holds all digits
    private Sprite[] playerHealthDigits; // Holds the player's health as digits

    /**
     * Create the user interface
     * @param playerMaxHealth the player's health
     * @param bossMaxHealth the boss's health
     */
    public UI(int playerMaxHealth, int bossMaxHealth)
    {
        playerHealthBarBack = SpriteRetriever.loadSprite("res/assets/blue.png", 0, 0, 1, 50, 50)[0];
        playerHealthBarFull = SpriteRetriever.loadSprite("res/assets/black.png", 0, 0, 1, 50, 50)[0];
        playerHealthBarCurr = SpriteRetriever.loadSprite("res/assets/red.png", 0, 0, 1, 50, 50)[0];
        playerHealthBar = new Rectangle(10, 10, PLAYER_HEALTH_BAR_WIDTH, 20);
        playerHealthFactor = (float)PLAYER_HEALTH_BAR_WIDTH / playerMaxHealth;
        bossHealthBarBack = SpriteRetriever.loadSprite("res/assets/blue.png", 0, 0, 1, 50, 50)[0];
        bossHealthBarFull = SpriteRetriever.loadSprite("res/assets/black.png", 0, 0, 1, 50, 50)[0];
        bossHealthBarCurr = SpriteRetriever.loadSprite("res/assets/red.png", 0, 0, 1, 50, 50)[0];
        bossHealthBar = new Rectangle(20, Display.getHeight() - 60, BOSS_HEALTH_BAR_WIDTH, 40);
        bossHealthFactor = (float)BOSS_HEALTH_BAR_WIDTH / bossMaxHealth;

        digits = new Sprite[11];

        // Populate the digits array with pictures of the digits
        theDigit = new Animation(11, 0,0,"res/digits/digits.png",50,50,25,25);
        for(int i = 0; i < 10; i++)
        {
            digits[i] = theDigit.getCurrentFrame();
            theDigit.nextFrame();
        }
        digits[10] = theDigit.getCurrentFrame(); // The 10th frame is just an empty sprite

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
        int playerHealth = Game.game.getCurrLevel().getHealth(true);
        playerHealthBar.width = (int)(playerHealth * playerHealthFactor);
        bossHealthBar.width = (int)(Game.game.getCurrLevel().getHealth(false) * bossHealthFactor);

        setPlayerHealthDigits(playerHealth);
    }

    /**
     * Sets the player's health as text
     * @param playerHealth Health of the player
     */
    private void setPlayerHealthDigits(int playerHealth)
    {
        playerHealthDigits[0] = digits[playerHealth / 100 != 0 ? playerHealth / 100 : 10]; // If the player's health is not a multiple of 100, don't show
        playerHealthDigits[1] = digits[(playerHealth / 100 != 0) || (playerHealth / 10 != 0) ? (playerHealth % 100) / 10 : 10];
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
        playerHealthBarBack.render(playerHealthBar.x - 4, playerHealthBar.y - 4, PLAYER_HEALTH_BAR_WIDTH + 8, playerHealthBar.height + 8); // Health bar outline
        playerHealthBarFull.render(playerHealthBar.x, playerHealthBar.y, PLAYER_HEALTH_BAR_WIDTH, playerHealthBar.height); // Health taken
        playerHealthBarCurr.render(playerHealthBar.x, playerHealthBar.y, playerHealthBar.width, playerHealthBar.height); // Health remaining
    }

    /**
     * Display the boss health
     */
    private void showBossHealth()
    {
        bossHealthBarBack.render(bossHealthBar.x - 4, bossHealthBar.y - 4, BOSS_HEALTH_BAR_WIDTH + 8, bossHealthBar.height + 8); // Health bar outline
        bossHealthBarFull.render(bossHealthBar.x, bossHealthBar.y, BOSS_HEALTH_BAR_WIDTH, bossHealthBar.height); // Health taken
        bossHealthBarCurr.render(bossHealthBar.x, bossHealthBar.y, bossHealthBar.width, bossHealthBar.height); // Health remaining
    }

    /**
     * Display the player health as text
     */
    private void showPlayerHealthNumber()
    {
        // Shows the player health digits
        for(int i = 0; i < 3; i++)
        {
            playerHealthDigits[i].render(playerHealthBar.x + PLAYER_HEALTH_BAR_WIDTH + (25 * i) + 5, playerHealthBar.y - 3,25,25);
        }
    }
}