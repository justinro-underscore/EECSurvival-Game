package com.base.game.gameobject.entity;

import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.engine.GameObject;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.Game;

import java.util.ArrayList;

public abstract class Character extends GameObject
{
    protected int health;
    protected int attackDamage;
    protected int attackSpeed; // In milliseconds
    protected boolean isDead;

    protected Character(float xPos, float yPos, int width, int height, String imgPath, int health, int attackDamage, int attackSpeed) {
        init(xPos, yPos, width, height, imgPath);

        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        isDead = false;
    }

    protected void checkCharacterCollision()
    {
        ArrayList<GameObject> closeObjects = Game.game.getCloseObjects(this, 5);
        for(GameObject obj : closeObjects)
        {
            if(Physics.checkCollision(this, obj))
            {
                if(obj instanceof Projectile)
                {
                    loseHealth(((Projectile) obj).getDamage());
                    obj.remove();
                }
                checkCharacterCollisionSpecific(obj);
            }
        }
    }

    protected void loseHealth(int hit)
    {
        health -= hit;
        if(health <= 0)
        {
            health = 0;
            isDead = true;
        }
    }

    public int getHealth()
    {
        return health;
    }

    abstract protected void checkCharacterCollisionSpecific(GameObject obj);
    abstract protected void checkDeath();
}
