package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.projectile.StandardProjectile;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Boss extends Character {
    private int timeSinceLastFire;

    public Boss(int xPos, int yPos, Sprite sprite, int health, int attackDamage, int attackSpeed) {
        super(xPos, yPos, sprite, health, attackDamage, attackSpeed);

        timeSinceLastFire = 200;
    }

    public void update() {
        attack();
        int xChange = 2;
        if(timeSinceLastFire < 150 && timeSinceLastFire >= 50){
            xPos += xChange;
        }
        else{
            xPos -= xChange;
        }
        if(timeSinceLastFire > 0)
            timeSinceLastFire--;
    }

    public void attack() {
        if(timeSinceLastFire == 0){
            wallOfFireAbility(5,5,6);
            timeSinceLastFire = 200;
        }
        if(timeSinceLastFire % 150 == 0){
            wallOfFireAbility(5,5,4);
        }
        if(timeSinceLastFire % 125 == 0){
            shootAbility(5,5,3);
        }
    }

    public void shootAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);
        ArrayList<StandardProjectile> pros = new ArrayList<>();
        for (int i = 1; i <= numberOfPros; i++){
            pros.add(new StandardProjectile(xPos + (width / 2)- (proWidth / 2), yPos - proHeight, spr, new GameVector(-(numberOfPros/2) + i - 1, -1), 5, 2));
        }
        for (int i =0; i < numberOfPros; i++){
            Game.game.addObj(pros.get(i));
        }
    }

    public void wallOfFireAbility(int proWidth, int proHeight, int numberOfPros) {
        Sprite spr = new Sprite(0.0f, 1.0f, 0.0f, proWidth, proHeight);
        ArrayList<StandardProjectile> pros = new ArrayList<>();
        for (int i = 1; i <= numberOfPros; i++){
            pros.add(new StandardProjectile((Display.getWidth() / numberOfPros) * i - (Display.getWidth() / (numberOfPros * 2)), yPos - proHeight, spr, new GameVector(0, -1), 5, 2));
        }
        for (int i =0; i < numberOfPros; i++){
            Game.game.addObj(pros.get(i));
        }
    }

    public void render() {
        glPushMatrix();
        {
            glTranslatef(xPos, yPos, 0);
            sprite.render();
        }
        glPopMatrix();
    }
}
