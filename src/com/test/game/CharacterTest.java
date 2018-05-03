package com.test.game;

import com.base.game.gameobject.entity.EmptyPlayer;
import com.base.game.gameobject.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the character functionality of the game
 */
class CharacterTest {
    private Player player;

    /**
     * Creates new player every test
     */
    @BeforeEach
    void setUp() {
        player = new EmptyPlayer(10, 10, 10, 10, 10, 10, 10,"res/SpriteSheets/walkcyclevarious.png");
    }

    /**
     * Decrements player health and checks change
     */
    @Test
    void loseHealth() {
        int startHealth = player.getHealth();
        player.takeDMG(1);
        int finalHealth = player.getHealth();

        assertTrue(startHealth > finalHealth);
    }

    /**
     * Tests adding health to player
     */
    @Test
    void gainHealth() {
        int startHealth = player.getHealth();
        player.addHealth(10);
        int finalHealth = player.getHealth();

        assertTrue(startHealth < finalHealth);
    }

    /**
     * Tests the death of the player
     */
    @Test
    void checkDeath() {
        player.takeDMG(10);
        assertTrue(player.isDead());
    }
}