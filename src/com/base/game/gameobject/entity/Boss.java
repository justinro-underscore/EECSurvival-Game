package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.interfaces.Game;
import com.base.game.gameobject.projectile.StandardProjectile;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Boss extends Character {
    private int timeSinceLastFire;
    private ArrayList<StandardProjectile> projectiles;

    public Boss(float xPos, float yPos, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, health, attackDamage, attackSpeed);

        projectiles = new ArrayList<>();
        timeSinceLastFire = 200;
    }

    public void update() {
        attack();

        for (int i = 0; i < projectiles.size(); i++){
            Game.game.addObj(projectiles.get(i));
        }
        projectiles.clear();

        float xChange = 2;
        if(timeSinceLastFire < 150 && timeSinceLastFire >= 50){
            xPos += xChange;
        }
        else{
            xPos -= xChange;
        }
        if(timeSinceLastFire > 0)
            timeSinceLastFire--;
        else
            timeSinceLastFire = 200;
    }

    public void attack() {
        if(timeSinceLastFire == 0){
            wallOfFireAbility(5,5,6);
        }
        if(timeSinceLastFire % 125 == 0 && timeSinceLastFire != 0){
            wallOfFireAbility(5,5,4);
        }
        if(timeSinceLastFire % 73 == 0) {
            if(Math.random() * 2 > 1){
                shootAbility(5,5,7);
            }
        }
    }

    public void shootAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);

        for (int i = 1; i <= numberOfPros; i++) {
            projectiles.add(new StandardProjectile(xPos + (width / 2)- (proWidth / 2), yPos - proHeight, spr, new Vector2f(-(numberOfPros/2) + i - 1 , -1), 5, 2));
        }
    }

    public void wallOfFireAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);

        for (int i = 1; i <= numberOfPros; i++){
            projectiles.add(new StandardProjectile((Display.getWidth() / numberOfPros) * i - (Display.getWidth() / (numberOfPros * 2)), yPos - proHeight, spr, new Vector2f(0, -1), 5, 2));
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
}
