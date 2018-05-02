package com.base.game.gameobject.entity;

public class Stats {
    private float speed;
    private int health;
    private int attackDamage;
    private boolean isDead;
    private int maxHealth;

    /**
     * Initialize character speed, health, and attack damage
     * @param speed initial speed
     * @param health initial health
     * @param attackDamage initial attack damage
     */
    public Stats(float speed, int health, int attackDamage){
        this.speed = speed;
        this.health = health;
        this.attackDamage = attackDamage;
        isDead = false; // Character should not start out dead
        maxHealth = health;
    }

    /**
     * Get speed
     * @return the character's speed
     */
    public float getSpeed(){
        return speed;
    }

    /**
     * Set speed
     * @param newSpeed the new speed to set speed to
     */
    public void setSpeed(float newSpeed){
        speed = newSpeed;
    }

    /**
     * Get health
     * @return the character's health
     */
    public int getHealth(){
        return health;
    }

    /**
     * Set the health
     * @param newHealth the new health to set
     */
    public void setHealth(int newHealth){
        health = newHealth;
    }

    /**
     * Set the health to the max health
     */
    public void setMaxHealth() { health = maxHealth;}

    /**
     * Get the attack damage
     * @return the character's attack damage
     */
    public int getAttackDamage(){
        return attackDamage;
    }

    /**
     * Set the attack damage to something new (it's a whole new world)
     * @param newAttackDamage that brand new, super sick, attack damage
     */
    public void setAttackDamage(int newAttackDamage){
        attackDamage = newAttackDamage;
    }

    /**
     * Get the isDead variable
     * @return the character's isDead
     */
    public boolean getIsDead(){
        return isDead; //isDead is true if the player is donezo
    }

    /**
     * decide the fate of our character
     * @param isHeDead if he true, he gone
     */
    public void setIsDead(boolean isHeDead){
        isDead = isHeDead;
    }

    /**
     * Get the character's max health
     * @return the character's max health
     */
    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * Set max health to a new max health
     * @param newMaxHealth the new max health
     */
    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
}
