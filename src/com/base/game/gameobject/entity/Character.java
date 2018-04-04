package com.base.game.gameobject.entity;

import com.base.engine.Physics;
import com.base.engine.Sprite;
import com.base.engine.GameObject;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.interfaces.Game;

import java.util.ArrayList;

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

    protected void checkCharacterCollision()
    {
        ArrayList<GameObject> closeObjects = Game.game.getCloseObjects(this, 5);
        for(GameObject obj : closeObjects)
        {
            if(Physics.checkCollision(this, obj))
            {
                if(obj instanceof Projectile)
                {
                    System.out.println("Ouch");
                    health -= ((Projectile) obj).getDamage();
                    obj.remove();
                }
            }
        }
    }
}
