package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.object.Door;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.scenes.Dialog;
import com.base.game.scenes.Event;
import com.base.game.utilities.Delay;
import com.base.game.utilities.Time;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private Delay attackDelay; // Delay between attacks
    private int konami;
    private int fireSfx;
    private float speedFactor;

    private int startHealth;
    private float startX;
    private float startY;

    // These are animation states
    private Animation walkDown;
    private Animation walkLeft;
    private Animation walkRight;
    private Animation walkUp;
    private Animation idle;

    /**
     * Creates a player object (should only be done once)
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param width width
     * @param height height
     * @param numOfAnimations file path to the image representing the render
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Player(float xPos, float yPos, int width, int height, int numOfAnimations, float speed, int health, int attackDamage) {
        super(xPos, yPos, width, height, numOfAnimations, speed, health, attackDamage,false); // Call Character superclass's constructor

        startX = xPos;
        startY = yPos;
        startHealth = health;

        fireSfx = Audio.loadSound("res/audio/fire.ogg");
        attackDelay = new Delay(500); // Time (in milliseconds) between attacks
        attackDelay.restart(); // Run this method so we can immediately fire

        idle = new Animation(1,64,118,"res/SpriteSheets/walkcyclevarious.png",64,59,50,50);

        //Initialize all of the animations
        walkDown = new Animation(3,384,118,"res/SpriteSheets/walkcyclevarious.png",64,59,50,50);
        walkLeft = new Animation(3,576,118,"res/SpriteSheets/walkcyclevarious.png",64,59,50,50);
        walkRight = new Animation(3,192,118,"res/SpriteSheets/walkcyclevarious.png",64,59,50,50);
        walkUp = new Animation(3,0,118,"res/SpriteSheets/walkcyclevarious.png",64,59,50,50);

        currAnimation = idle;
        speedFactor = 1.0f;
    }

    /**
     * Updates the player object every frame
     */
    @Override
    public void update() {
        super.update();

        checkCharacterCollision();
        checkDeath();

        getInput();
    }

    /**
     * Gets input from the InputHandler
     */
    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) || InputHandler.isKeyDown(GLFW_KEY_A) || InputHandler.isKeyDown(GLFW_KEY_S) || InputHandler.isKeyDown(GLFW_KEY_D)) {
            currAnimation.start();
        } else {
            currAnimation.stop();
        }

        // Go up
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            move(0,1);
            currAnimation = walkUp;
        }
        // Go down
        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            move(0,-1);
            currAnimation = walkDown;
        }
        // Go right
        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            move(1, 0);
            currAnimation = walkRight;
        }
        // Go left
        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            move(-1, 0);
            currAnimation = walkLeft;
        }

        // Shoot *Can't shoot while sprinting
        if (InputHandler.isKeyDown(GLFW_KEY_SPACE) && attackDelay.isOver() && !InputHandler.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            attack();
        }

        //Sprint Functionality
        if (InputHandler.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            //Detects the input from the user for sprint direction
            if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
                speedFactor = 2.0f;
            } else if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
                speedFactor = 2.0f;
            } else if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
                speedFactor = 2.2f;
            } else if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
                speedFactor = 2.2f;
            }
        } else {
            speedFactor = 1.0f;
        }

        enterCheatCode();
    }

    private void move(int x, int y) {
        xPos += x * stats.getSpeed() * speedFactor;
        yPos += y * stats.getSpeed() * speedFactor;
    }


    public boolean moveTo(float x, float y) {
        if (Math.ceil(Math.abs(xPos - x)) <= 10 && Math.ceil(Math.abs(yPos - y)) <= 10) {
            return true;
        }

        if (Math.ceil(Math.abs(xPos - x)) > 10) {
            if (xPos - x > 0) {
                move(-1, 0);
            } else {
                move(1, 0);
            }
        }

        if (Math.ceil(Math.abs(yPos - y)) > 10) {
            if (yPos - y > 0) {
                move(0, -1);
            } else {
                move(0, 1);
            }
        }

        return false;
    }

    /**
     * Allows you to enter in a cheat code (the konami code)
     * Up, up, down, down, left, right, left, right, B, A
     * jesus christ
     */
    private void enterCheatCode()
    {
        if((konami == 0 || konami == 2) && InputHandler.isAnyKeyDown(GLFW_KEY_UP) ||
                (konami == 4 || konami == 6) && InputHandler.isAnyKeyDown(GLFW_KEY_DOWN) ||
                konami == 8 && InputHandler.isAnyKeyDown(GLFW_KEY_LEFT) ||
                konami == 10 && InputHandler.isAnyKeyDown(GLFW_KEY_RIGHT) ||
                konami == 12 && InputHandler.isAnyKeyDown(GLFW_KEY_LEFT) ||
                konami == 14 && InputHandler.isAnyKeyDown(GLFW_KEY_RIGHT) ||
                konami == 16 && InputHandler.isAnyKeyDown(GLFW_KEY_B) ||
                konami == 18 && InputHandler.isAnyKeyDown(GLFW_KEY_A))
            konami = 0;
        else if(konami == 0 && InputHandler.isKeyDown(GLFW_KEY_UP))
            konami++;
        else if(konami == 1 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 2 && InputHandler.isKeyDown(GLFW_KEY_UP))
            konami++;
        else if(konami == 3 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 4 && InputHandler.isKeyDown(GLFW_KEY_DOWN))
            konami++;
        else if(konami == 5 && !InputHandler.isAnyKeyDown(-1))
            konami++;
        else if(konami == 6 && InputHandler.isKeyDown(GLFW_KEY_DOWN)) { // Shortened to up up down down for easier debugging
//            konami++;
//        else if(konami == 7 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 8 && InputHandler.isKeyDown(GLFW_KEY_LEFT))
//            konami++;
//        else if(konami == 9 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 10 && InputHandler.isKeyDown(GLFW_KEY_RIGHT))
//            konami++;
//        else if(konami == 11 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 12 && InputHandler.isKeyDown(GLFW_KEY_LEFT))
//            konami++;
//        else if(konami == 13 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 14 && InputHandler.isKeyDown(GLFW_KEY_RIGHT))
//            konami++;
//        else if(konami == 15 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 16 && InputHandler.isKeyDown(GLFW_KEY_B))
//            konami++;
//        else if(konami == 17 && !InputHandler.isAnyKeyDown(-1))
//            konami++;
//        else if(konami == 18 && InputHandler.isKeyDown(GLFW_KEY_A)) {
            konami++;
            Game.game.getLevelManager().executeCheat();
        }
    }

    /**
     *  Specifies how the player attacks
     */
    protected void attack() {
        // TODO Change this to something more modular
        int proWidth = 5; // Projectile width
        int proHeight = 5; // Projectile height

        Vector2f proDir = new Vector2f(0, 1);

        Audio.playBuffer(fireSfx);
        Audio.setBufferGain(fireSfx, 1.5f);
        StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, proWidth, proHeight, "res/assets/white.png", proDir, 5, 8 , false); // Create the projectile

        Game.game.getCurrLevel().addObj(pro);
        attackDelay.start(); // Make sure the player can't rapid fire
    }

    /**
     * Checks character specific collisions
     * @param obj the object being collided with
     */
    protected void checkCharacterCollisionSpecific(GameObject obj)
    {
        if(obj instanceof Boss) // If the player touches the boss...
        {
            loseHealth(1); // Continually lose one hitpoint
        }

        if (obj instanceof Door) {
            xPos = Display.getWidth() / 2 - 30;
            yPos = 100;
            Game.game.getLevelManager().nextLevel();
        }
    }

    /**
     * Check to see if the player has died
     */
    protected void checkDeath() {
        if (stats.getIsDead()) // If the player is dead...
        {
            Game.game.getCurrLevel().levelOver(true); // Run levelOver
        }
    }

    /**
     * Creates a walk event for player
     * @param x XPos to go to
     * @param y YPos to go to
     * @return True if reached destination
     */
    public Event createWalkEvent(float x, float y) {
        return new Event("walk", new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return moveTo(x, y);
            }
        });
    }

    /**
     * Respawns player
     */
    public void respawn() {
         xPos = startX;
         yPos = startY;

         this.gainHealth(startHealth);
         stats.setIsDead(false);
    }

    /**
     * Reduces player health
     * @param amt amount to decrease player health by
     */
    public void takeDMG(int amt) {
        loseHealth(amt);
    }

    /**
     * Adds health to player from external content
     * @param amt amount to increase health by
     */
    public void addHealth(int amt) {
        gainHealth(amt);
    }

    /**
     * Returns true if player is dead
     * @return player death status
     */
    public boolean isDead() {
        return stats.getIsDead();
    }
}
