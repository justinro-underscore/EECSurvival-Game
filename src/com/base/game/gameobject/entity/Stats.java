package com.base.game.gameobject.entity;

public class Stats {
    private float speed;
    private int health;
    private int attackDamage;
    private boolean isDead;
    private int maxHealth;

    public Stats(float speed, int health, int attackDamage){
        this.speed = speed;
        this.health = health;
        this.attackDamage = attackDamage;
        isDead = false; // Character should not start out dead
        maxHealth = health;
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float newSpeed){
        speed = newSpeed;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int newHealth){
        health = newHealth;
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public void setAttackDamage(int newAttackDamage){
        attackDamage = newAttackDamage;
    }

    public boolean getIsDead(){
        return isDead; //isDead is true if the player is donezo
    }

    public void setIsDead(boolean isHeDead){
        isDead = isHeDead;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
}
