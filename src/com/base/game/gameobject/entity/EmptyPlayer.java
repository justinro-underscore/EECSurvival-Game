package com.base.game.gameobject.entity;

public class EmptyPlayer extends Player {

    /**
     * Empty player for testing purposed
     * @param xPos x position on the screen
     * @param yPos y position on the screen
     * @param width width of the object
     * @param height height of the object
     * @param speed speed of the player
     * @param health health of the player
     * @param attackDamage attack damage of the player
     * @param image the characters sprite
     */

    public EmptyPlayer(float xPos, float yPos, int width, int height, float speed, int health, int attackDamage,String image) {
        super(xPos, yPos, width, height, speed, health, attackDamage, image);
    }
}
