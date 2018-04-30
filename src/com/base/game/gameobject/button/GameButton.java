package com.base.game.gameobject.button;

import com.base.engine.*;

public class GameButton extends GameObject
{
    private String btnTexture; // The current texture of the button
    private String oldBtnTexture; // Put in place so we will not keep updating the render
    private Runnable onPressed; // Function to be ran

    private Animation buttonAnimation;
    private String btnReleased; // The image path to the released texture SHOULD NOT BE CHANGED
    private String btnPressed; // The image path to the pressed texture SHOULD NOT BE CHANGED
    private SpriteRetriever retriever;

    /**
     * Creates a new button
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param width width of the render
     * @param height height of the render
     * @param imgPathReleased file path to the image representing the released button
     * @param imgPathPressed file path to the image representing the released button
     * @param func the function to be run when pressed
     */
    public GameButton(float xPos, float yPos, int width, int height, String imgPathReleased, String imgPathPressed, Runnable func)
    {

        // Set the constant variables
        btnReleased = imgPathReleased;
        btnPressed = imgPathPressed;

        btnTexture = btnReleased; // Button should start out released
        oldBtnTexture = btnTexture;

        onPressed = func;

        init(xPos, yPos, width, height, 1,false,imgPathReleased,0,0); // Create the object
    }

    /**
     * Update the button's status
     */
    public void update()
    {
        Vector2f mousePos = InputHandler.getMousePos(); // Get the mouse's position

        // Set the render's texture
        if (Physics.checkCollision(this, (int)mousePos.x, (int)mousePos.y))
            btnTexture = btnPressed;
        else
            btnTexture = btnReleased;

        // So we're not constantly updating the render
        if(!oldBtnTexture.equals(btnTexture))
        {
            buttonAnimation.render(1,1);
            oldBtnTexture = btnTexture;
        }

        // Check to see if the button has been pressed
        if (InputHandler.isMouseDown() && btnTexture == btnPressed)
        {
            btnTexture = btnReleased;
            new Thread(onPressed).start(); // Run the function
        }
    }
}
