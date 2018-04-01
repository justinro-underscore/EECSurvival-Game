package com.base.game.gameobject.projectile;

import com.base.engine.Sprite;
import com.base.engine.GameVector;

public class StandardProjectile extends Projectile
{
    public StandardProjectile(int xPos, int yPos, int width, int height, Sprite sprite, GameVector shootAngle, int damage, int speed)
    {
        super(xPos, yPos, width, height, sprite, shootAngle, damage, speed);
    }
}
