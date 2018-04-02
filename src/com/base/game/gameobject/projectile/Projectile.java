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
    protected float speed;

    protected Projectile(float xPos, float yPos, Sprite sprite, GameVector shootAngle, int damage, float speed)
    {
        init(xPos, yPos, sprite);
        this.shootAngle = shootAngle;
        this.damage = damage;
        this.speed = speed;
    }


    @Override
    public void update()
    {
        float xChange;
        float yChange;
        if(shootAngle.x < 0){
            xChange = (float) (Math.ceil((double)shootAngle.x) * speed);
        }
        else{
            xChange = (float) (Math.floor((double)shootAngle.x) * speed);
        }
        if(shootAngle.y < 0){
            yChange = (float) (Math.ceil((double)shootAngle.y) * speed);
        }
        else{
            yChange = (float) (Math.floor((double)shootAngle.y) * speed);
        }

        if(((double) shootAngle.x <= 0 && xChange + xPos + width >= 0) || ((double) shootAngle.x > 0 && xChange + xPos <= Display.getWidth()))
            xPos += xChange;
        else {
            remove();
            return;
        }

        if(((double) shootAngle.y <= 0 && yChange + yPos + height >= 0) || ((double) shootAngle.y > 0 && yChange + yPos <= Display.getHeight()))
            yPos += yChange;
        else {
            remove();
            return;
        }
    }
}
