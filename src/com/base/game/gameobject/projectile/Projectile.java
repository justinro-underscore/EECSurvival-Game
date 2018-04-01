package com.base.game.gameobject.projectile;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.game.Game;
import com.base.engine.GameObject;
import com.base.engine.GameVector;

public class Projectile extends GameObject
{
    protected GameVector shootAngle;
    protected int damage;
    protected int speed;

    protected Projectile(int xPos, int yPos, int width, int height, Sprite sprite, GameVector shootAngle, int damage, int speed)
    {
        super(xPos, yPos, width, height, sprite);
        this.shootAngle = shootAngle;
        this.damage = damage;
        this.speed = speed;
    }


    @Override
    public void update()
    {
        int xChange = (int) (Math.floor((double)shootAngle.x) * speed);
        int yChange = (int) (Math.floor((double)shootAngle.y) * speed);
        if(((double) shootAngle.x <= 0 && xChange + xPos + width >= 0) || ((double) shootAngle.x > 0 && xChange + xPos <= Display.getWidth()))
            xPos += xChange;
        else {
            Game.game.removeObj(this);
            return;
        }

        if(((double) shootAngle.y <= 0 && yChange + yPos + height >= 0) || ((double) shootAngle.y > 0 && yChange + yPos <= Display.getHeight()))
            yPos += yChange;
        else {
            Game.game.removeObj(this);
            return;
        }
    }
}
