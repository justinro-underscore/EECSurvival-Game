package com.base.game.gameobject.button;

import com.base.engine.*;

public class GameButton extends GameObject {
    private String btnTexture; // The current texture of the button
    private TextRenderer label; // The button's label
  
    private Runnable onPressed; // Function to be ran

    private boolean isAlreadyPressed;
    private boolean isPressed;

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
        onPressed = func;

        btnTexture = "res/assets/button.png"; // Button should start out released

        label = new TextRenderer(content, width, height, true, 3, false, xPos, yPos);

        init(xPos, yPos, 300, 81, 2, false, btnTexture, 0, 0, width, height); // Create the object
    }

    /**
     * Update the button's status
     */
    public void update() {
        Vector2f mousePos = InputHandler.getMousePos(); // Get the mouse's position

        // Set the sprite's texture
        if (Physics.checkCollision(this, (int)mousePos.x, (int)mousePos.y))
            isPressed = true;
        else
            isPressed = false;

        // So we're not constantly updating the sprite
        if(isPressed != isAlreadyPressed)
        {
            setTexture();
            isAlreadyPressed = isPressed;
        }

        // Check to see if the button has been pressed
        if (InputHandler.isMouseDown() && isPressed)
        {
            isPressed = false;
            new Thread(onPressed).start(); // Run the function
        }
    }

    public void render()
    {
        super.render();
        label.render();
    }
}
