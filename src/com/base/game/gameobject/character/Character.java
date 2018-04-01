package com.base.game.gameobject.character;

import com.base.engine.Sprite;
import com.base.engine.GameObject;

public class Character extends GameObject
{
    protected int health;
    protected int attackDamage;
    protected int attackSpeed; // In milliseconds

    protected Character(int xPos, int yPos, int width, int height, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, width, height, sprite);
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }


}
