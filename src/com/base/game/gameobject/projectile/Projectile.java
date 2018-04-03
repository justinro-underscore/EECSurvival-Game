package com.base.game.gameobject.projectile;

import com.base.engine.Sprite;
import com.base.engine.Vector2f;
import com.base.engine.GameObject;
import com.base.game.utilities.Util;

public class Projectile extends GameObject
{
    protected Vector2f shootAngle;
    protected int damage;
    protected float speed;

    protected Projectile(float xPos, float yPos, Sprite sprite, String imgPath, Vector2f shootAngle, int damage, float speed)
    {
        init(xPos, yPos, sprite, imgPath);
        this.shootAngle = shootAngle.normalize();
        this.damage = damage;
        this.speed = speed;
    }


    @Override
    public void update()
    {
        float xChange = shootAngle.x * speed;
        float yChange = shootAngle.y * speed;

        xPos += xChange;
        yPos += yChange;

        if (Util.offScreen(getX(), getY(), width, height)) {
                remove();
        }
    }

    public int getDamage()
    {
        return damage;
    }
}
