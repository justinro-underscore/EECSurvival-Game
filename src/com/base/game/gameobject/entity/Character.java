package com.base.game.gameobject.entity;

import com.base.engine.Sprite;
import com.base.engine.GameObject;

public class Character extends GameObject
{
    protected int health;
    protected int attackDamage;
    protected int attackSpeed; // In milliseconds

    protected Character(float xPos, float yPos, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        init(xPos, yPos, sprite);

        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }


}
