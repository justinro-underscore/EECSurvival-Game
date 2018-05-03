package com.base.game.gameobject.button;

import com.base.engine.*;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;

public class GameButton extends GameObject {
    private String btnTexture; // The current texture of the button
    private TextRenderer label; // The button's label

    private Runnable onPressed; // Function to be ran

    private boolean isAlreadyPressed;
    private boolean isPressed;
    private boolean clicked;

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

        init(xPos, yPos, 0, 0, 2, false, btnTexture, 300, 81, width, height); // Create the object
    }

    /**
     * Update the button's status
     */
    public void update() {
        Vector2f mousePos = InputHandler.getMousePos(); // Get the mouse's position

        // Set the sprite's texture
        isPressed = Physics.checkCollision(this, (int) mousePos.x, (int) mousePos.y);

        // So we're not constantly updating the sprite
        if(isPressed != isAlreadyPressed)
        {
            if(clicked)
                clicked = false;
            else
                setTexture();
            isAlreadyPressed = isPressed;
        }

        // Check to see if the button has been pressed
        if (InputHandler.isMouseDown() && isPressed && !clicked)
        {
            setTexture();
            clicked = true;
            new Thread(onPressed).start(); // Run the function
        }
    }
    
    /**
     * Renders the Game button onto the screen
     */
    public void render()
    {
        super.render();
        label.render();

        glLineWidth(2);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4f(0, 0, 1, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f(xPos, yPos);
            glVertex2f(xPos + width, yPos);
            glVertex2f(xPos + width, yPos + height);
            glVertex2f(xPos, yPos + height);
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }
}
