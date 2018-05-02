package com.base.game.gameobject.button;

import com.base.engine.*;

public class GameButton extends GameObject
{
    private String btnTexture; // The current texture of the button
    private String oldBtnTexture; // Put in place so we will not keep updating the sprite
    private TextRenderer label; // The button's label
    private Runnable onPressed; // Function to be ran

    private String btnReleased; // The image path to the released texture SHOULD NOT BE CHANGED
    private String btnPressed; // The image path to the pressed texture SHOULD NOT BE CHANGED

    /**
     * Creates a new button
     * @param xPos x-coordinate
     * @param yPos y-coordinate
     * @param width width of the sprite
     * @param height height of the sprite
     * @param content The content of the button's label
     * @param func the function to be run when pressed
     */
    public GameButton(float xPos, float yPos, int width, int height, String content, Runnable func)
    {
        // Set the constant variables
        btnReleased = "res/assets/button_release.png";
        btnPressed = "res/assets/button_press.png";

        btnTexture = btnReleased; // Button should start out released
        oldBtnTexture = btnTexture;

        label = new TextRenderer(content, width, height, true, 3, false, xPos, yPos);

        onPressed = func;

        init(xPos, yPos, width, height, btnTexture,false); // Create the object
    }

    /**
     * Update the button's status
     */
    public void update()
    {
        Vector2f mousePos = InputHandler.getMousePos(); // Get the mouse's position

        // Set the sprite's texture
        if (Physics.checkCollision(this, (int)mousePos.x, (int)mousePos.y))
            btnTexture = btnPressed;
        else
            btnTexture = btnReleased;

        // So we're not constantly updating the sprite
        if(!oldBtnTexture.equals(btnTexture))
        {
            setTexture(btnTexture);
            oldBtnTexture = btnTexture;
        }

        // Check to see if the button has been pressed
        if (InputHandler.isMouseDown() && btnTexture == btnPressed)
        {
            btnTexture = btnReleased;
            new Thread(onPressed).start(); // Run the function
        }
    }

    public void render()
    {
        super.render();
        label.render();
    }
}
