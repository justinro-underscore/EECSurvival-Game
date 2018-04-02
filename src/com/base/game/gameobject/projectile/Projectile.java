package com.base.game.gameobject.projectile;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.engine.Vector2f;
import com.base.engine.GameObject;

public class Projectile extends GameObject
{
    protected Vector2f shootAngle;
    protected int damage;
    protected float speed;

    protected Projectile(float xPos, float yPos, Sprite sprite, Vector2f shootAngle, int damage, float speed)
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

        xPos += xChange;
        yPos += yChange;

        if (xPos <= 0 || xPos >= Display.getWidth()|| yPos <= 0 || yPos >= Display.getHeight()) {
                remove();
        }
    }
}
