package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Boss extends Character {
    private Delay wallAttackDelay;
    private Delay targetAttackDelay;
    private Delay burstAttackDelay;

    private float movSpeed;

    private ArrayList<StandardProjectile> projectiles;

    public Boss(float xPos, float yPos, Sprite sprite, String imgPath, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, imgPath, health, attackDamage, attackSpeed);

        movSpeed = -2;

        projectiles = new ArrayList<>();

        wallAttackDelay = new Delay(3000);
        targetAttackDelay = new Delay(800);
        burstAttackDelay = new Delay(1200);

        wallAttackDelay.start();
        targetAttackDelay.start();
        burstAttackDelay.start();
    }

    public void update() {
        attack();

        for (int i = 0; i < projectiles.size(); i++){
            Game.game.addObj(projectiles.get(i));
        }
        projectiles.clear();

        xPos += movSpeed;
        if (Math.abs(getX() - Display.getWidth() / 2.0f) >= Display.getWidth() / 4.0f)
            movSpeed = -movSpeed;
    }

    public void attack() {
        if(wallAttackDelay.isOver()){
            wallOfFireAbility(5,5,6);
            wallAttackDelay.start();
        }
        if(targetAttackDelay.isOver()){
            targetPlayerAbility(5,5,1);
            targetAttackDelay.start();
        }
        if(burstAttackDelay.isOver()) {
            if(Math.random() * 2 > 1){
                shootAbility(5,5,7);
            }
            burstAttackDelay.start();
        }
    }

    public void shootAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);

        for (int i = 1; i <= numberOfPros; i++) {
            projectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, spr, "", new Vector2f(-(numberOfPros/2) + i - 1 , -1), 5, 2));
        }
    }

    public void targetPlayerAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);

        projectiles.add(new StandardProjectile(getX() - (proWidth / 2), yPos - proHeight, spr, "", new Vector2f(Game.game.getPlayerX() - (getX() - (proWidth / 2)), Game.game.getPlayerY() - (yPos - proHeight)), 5, 2));
    }

    public void wallOfFireAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);

        for (int i = 1; i <= numberOfPros; i++){
            projectiles.add(new StandardProjectile((Display.getWidth() / numberOfPros) * i - (Display.getWidth() / (numberOfPros * 2)), yPos - proHeight, spr, "", new Vector2f(0, -1), 5, 2));
        }
    }

    public void render() {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            sprite.render(-1);
        }
        glPopMatrix();
    }

    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        // Boss specific collisions
    }

    protected void checkDeath()
    {
        // You win!
    }
}
