package com.base.game.gameobject.entity;

public class EmptyBoss extends Boss {
    /**
     * Creates a new boss
     *
     * @param xPos         x-coordinate of the sprite
     * @param yPos         y-coordinate of the sprite
     * @param width        width
     * @param height       height
     * @param imgPath      file path to the image representing the sprite
     * @param speed        the speed of the character
     * @param health       starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public EmptyBoss(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, 1, width, height, speed, health, attackDamage,imgPath);
    }

    @Override
    /**
     * attack
     */
    public void attack() {}
}
