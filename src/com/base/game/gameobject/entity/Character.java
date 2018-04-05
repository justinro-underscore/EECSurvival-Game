package com.base.game.gameobject.entity;

import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.engine.GameObject;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.interfaces.Game;

import java.util.ArrayList;

public abstract class Character extends GameObject
{
    protected int health;
    protected int attackDamage;
    protected int attackSpeed; // In milliseconds
    protected boolean isDead;

    protected String projectileImg;

    protected Character(float xPos, float yPos, Sprite sprite, String imgPath, int health, int attackDamage, int attackSpeed) {
        init(xPos, yPos, sprite, imgPath);

        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        isDead = false;
        projectileImg = "";
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

    public void setTextureID(int textureID) {
        this.textureID = textureID;
        projectileImg = "./res/harry1.jpg";
    }

    abstract protected void checkCharacterCollisionSpecific(GameObject obj);
    abstract protected void checkDeath();
}
