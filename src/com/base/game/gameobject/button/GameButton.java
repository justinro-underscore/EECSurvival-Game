package com.base.game.gameobject.button;

import com.base.engine.*;

public class GameButton extends GameObject {
    private String btnTexture; // The current texture of the button
    private String oldBtnTexture; // Put in place so we will not keep updating the sprite
    private TextRenderer label; // The button's label
  
    private Runnable onPressed; // Function to be ran

    private boolean hover;

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

        label = new TextRenderer(content, width, height, true, 3, false, xPos, yPos);

        onPressed = func;
        hover = false;

        init(xPos, yPos, 0, 0, 2, false, imgPathReleased, width, height, width, height); // Create the object
    }

    /**
     * Update the button's status
     */
    public void update() {
        Vector2f mousePos = InputHandler.getMousePos(); // Get the mouse's position
        //currAnimation.render(Display.getWidth() / 2, Display.getHeight() / 2);

        // Set the render's texture
        if (Physics.checkCollision(this, (int) mousePos.x, (int) mousePos.y)) {
            if (InputHandler.isMouseDown()) {
                currAnimation.nextFrame();
                new Thread(onPressed).start(); // Run the function           }
            } else
                hover = false;
      }


//        // So we're not constantly updating the render
//        if(!oldBtnTexture.equals(btnTexture))
//        {
//
//            oldBtnTexture = btnTexture;
//        }
//
//        // Check to see if the button has been pressed
//        if (InputHandler.isMouseDown() && btnTexture == btnPressed)
//        {
//            btnTexture = btnReleased;
//            currAnimation.nextFrame();
//            new Thread(onPressed).start(); // Run the function
//        }
        }
    }

    public void render()
    {
        super.render();
        label.render();
    }
}
