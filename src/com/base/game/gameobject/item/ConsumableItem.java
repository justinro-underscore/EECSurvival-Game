package com.base.game.gameobject.item;

public class ConsumableItem extends Item
{
    private int addedHealth;

    /**
     * Constructor for a consumable item
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param despawnTime the time it takes for a item to dematerialize and be unattainable
     * @param addedHealth the health that will be added to the character who collides with the object
     */
    public ConsumableItem(float xPos, float yPos, int width, int height, String imgPath, int despawnTime, int addedHealth){
        super(xPos, yPos,1, 1, imgPath, despawnTime,1);

        this.addedHealth = addedHealth;
    }

    /**
     * Get the amount of health the consumable item will add to the character
     * @return the health that the consumable item contains
     */
    public int getAddedHealth(){
        return this.addedHealth;
    }
}
