package com.base.game.gameobject.entity;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.object.Door;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.utilities.Delay;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Character {
    private Delay attackDelay; // Delay between attacks
    private int konami;
    private int fireSfx;
    private String spriteFile = "testSpriteSheet";

    // These are animation states
    private Animation walkDown;
    private Animation walkLeft;
    private Animation walkRight;
    private Animation walkUp;

    /**
     * Creates a player object (should only be done once)
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the render
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Player(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, width, height, imgPath, speed, health, attackDamage,false); // Call Character superclass's constructor

        fireSfx = Audio.loadSound("res/audio/fire.ogg");

        attackDelay = new Delay(500); // Time (in milliseconds) between attacks
        attackDelay.restart(); // Run this method so we can immediately fire

        //Initialize all of the animations
        //TODO needs to be put into the super's animation array
        walkDown = new Animation(2,32);
        walkDown.addSprite(1,1, spriteFile);
        walkDown.addSprite(3,1, spriteFile);

        walkLeft = new Animation(2,32);
        walkLeft.addSprite(1,2, spriteFile);
        walkLeft.addSprite(3,2, spriteFile);

        walkRight = new Animation(2,32);
        walkRight.addSprite(1,3, spriteFile);
        walkRight.addSprite(3,3, spriteFile);

        walkUp = new Animation(2, 32);
        walkUp.addSprite(1,4, spriteFile);
        walkUp.addSprite(3,4, spriteFile);
    }

    @Override
    public void render() {
        walk.render(xPos, yPos);
    }

    /**
     * Updates the player object every frame
     */
    public void update() {
        checkCharacterCollision();
        checkDeath();

        getInput();

        walk.update();
    }

    /**
     * Gets input from the InputHandler
     */
    public void getInput() {
        if (InputHandler.isKeyDown(GLFW_KEY_W) || InputHandler.isKeyDown(GLFW_KEY_A) || InputHandler.isKeyDown(GLFW_KEY_S) || InputHandler.isKeyDown(GLFW_KEY_D)) {
            walk.start();
        } else {
            walk.stop();
        }

        // Go up
        if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
            yPos += speed;
        }

        // Go down
        if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
            yPos -= speed;
        }

        // Go right
        if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
            xPos += speed;
        }

        // Go left
        if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
            xPos -= speed;
        }

        // Shoot *Can't shoot while sprinting
        if (InputHandler.isKeyDown(GLFW_KEY_SPACE) && attackDelay.isOver() && !InputHandler.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            attack();
        }

        //Sprint Functionality
        if (InputHandler.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            //Detects the input from the user for spring direction
            if (InputHandler.isKeyDown(GLFW_KEY_W) && yPos < Display.getHeight() - height) {
                yPos += speed * 1.1;
            } else if (InputHandler.isKeyDown(GLFW_KEY_S) && yPos > 0) {
                yPos -= speed * 1.1;
            } else if (InputHandler.isKeyDown(GLFW_KEY_D) && xPos < Display.getWidth() - width) {
                xPos += speed * 1.2;
            } else if (InputHandler.isKeyDown(GLFW_KEY_A) && xPos > 0) {
                xPos -= speed * 1.2;
            }
        }

        enterCheatCode();
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
            Game.game.executeCheat();
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
        StandardProjectile pro = new StandardProjectile(getX() - (proWidth / 2), yPos + height, proWidth, proHeight, "", proDir, 5, 8 , false); // Create the projectile

        Game.game.addObj(pro);
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
            Game.game.nextLevel();
        }
    }

    /**
     * Check to see if the player has died
     */
    protected void checkDeath() {
        if (isDead) // If the player is dead...
        {
            Game.game.levelOver(true); // Run levelOver
        }
    }
}

/*

 */