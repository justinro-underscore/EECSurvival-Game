package com.base.game.gameobject.projectile;

import com.base.engine.Sprite;
import com.base.engine.Vector2f;

public class StandardProjectile extends Projectile
{
    public StandardProjectile(float xPos, float yPos, Sprite sprite, String imgPath, Vector2f shootAngle, int damage, float speed)
    {
        super(xPos, yPos, sprite, imgPath, shootAngle, damage, speed);
    }
}
