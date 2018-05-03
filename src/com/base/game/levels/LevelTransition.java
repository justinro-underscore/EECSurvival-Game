package com.base.game.levels;

import com.base.engine.Animation;
import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.engine.SpriteRetriever;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;
import com.base.game.utilities.Delay;

public class LevelTransition
{
    private Delay blackFadeInTime; // Time between completion of level and start black fade in
    private Delay blackFadeOutTime; // Time between black fade in and black fade out
    private Delay whiteFadeInTime; // Time between black fade out and white fade in
    private Delay whiteFadeOutTime; // Time between white fade in and white fade out
    private boolean blackFade; // Whether or not the screen is fading to black
    private Animation blackScreen;
    private Animation nextLevelScreen;
    private Animation gameOverScreen;
    private Animation whiteScreen;
    private float endScreenTransparency; // The transparency of the screens
    private GameButton quitButton;

    /**
     * Creates a Level Transition Object to handle level transitions
     */
    public LevelTransition()
    {
        //retriever = new SpriteRetriever(16, 16);

        blackFadeInTime = new Delay(500);
        blackFadeOutTime = new Delay(1000);
        whiteFadeInTime = new Delay(500);
        whiteFadeOutTime = new Delay(750);
        blackFade = true; // Start on black fade
        blackScreen = new Animation(1,0,0,"res/assets/black.png",50,50,Display.getWidth(),Display.getHeight());

        nextLevelScreen= new Animation(1,0,0,"res/assets/BossDefeated.png",820,484,Display.getWidth(),Display.getHeight());

        gameOverScreen= new Animation(1,0,0,"res/assets/gameOver.png",820,484,Display.getWidth(),Display.getHeight());

        whiteScreen= new Animation(1,0,0,"res/assets/white.png",50,50,Display.getWidth(),Display.getHeight());

        endScreenTransparency = 0;
    }

    /**
     * Initialize the level transition
     */
    public void init()
    {
        blackFadeInTime.start();
    }

    /**
     * Render the game screens
     * @param gameOver Whether or not the player has lost
     * @return true when the level transition is done
     */
    public boolean render(boolean gameOver)
    {
        if(!gameOver) {
            if (blackFade) {
                if (!blackFadeOutTime.isOver())
                    fadeInBlack(gameOver);
                else
                    fadeOutBlack();
            } else {
                if (!whiteFadeOutTime.isOver())
                    fadeInWhite();
                else {
                    fadeOutWhite();
                    if (endScreenTransparency < 0) // When white transition is gone
                        return true;
                }
            }
        }
        else
        {
            fadeInBlack(gameOver); // Game over screen
        }
        return false;
    }

    /**
     * Fades in the black screen & text
     * @param gameOver true to show Game Over text, false to show level complete
     */
    private void fadeInBlack(boolean gameOver)
    {
        if(blackFadeInTime.isOver()) // If it's ready to fade in
        {
            // Update transparency
            if (endScreenTransparency < 1)
                endScreenTransparency += 0.005;

            if (endScreenTransparency >= 0.994 && endScreenTransparency < 1) {
                blackFadeOutTime.start(); // Happens here so that it only starts it once, right before Next Level screen gets fully opaque
                if(gameOver) {
                    quitButton = new GameButton((float) (Display.getWidth() / 2 - 200), 50f, 400, 80,"Menu", Game::backToMenu);
                }
            }

            if (gameOver || endScreenTransparency < 0.85f) // If gameOver, go to full black, if not go to 85% opaque
                blackScreen.getCurrentFrame().setAlpha(endScreenTransparency);

            blackScreen.render(0, 0);

            if(!gameOver) { // If not gameOver, show level complete. If yes, show game over
                nextLevelScreen.getCurrentFrame().setAlpha(endScreenTransparency);
                nextLevelScreen.render(0, 0);
            }
            else {
                gameOverScreen.getCurrentFrame().setAlpha(endScreenTransparency);
                gameOverScreen.render(0, 0);
                if(endScreenTransparency >= 1) {
                    quitButton.update();
                    quitButton.render();
                }
            }
        }
    }

    /**
     * Fade out the black screen & text
     */
    private void fadeOutBlack()
    {
        if (endScreenTransparency > 0)
            endScreenTransparency -= 0.005;
        else { // When endScreenTransparency hits the bottom
            blackFade = false; // End black fade
            whiteFadeInTime.start(); // Start the white fade in timer
        }

        if (endScreenTransparency < 0.85f)
            blackScreen.getCurrentFrame().setAlpha(endScreenTransparency);
        blackScreen.render(0, 0);

        nextLevelScreen.getCurrentFrame().setAlpha(endScreenTransparency);
        nextLevelScreen.render(0, 0);
    }

    /**
     * Fade in the white screen
     */
    private void fadeInWhite()
    {
        if(whiteFadeInTime.isOver())
        {
            if (endScreenTransparency < 1)
                endScreenTransparency += 0.05; // Fade in more quickly

            if (endScreenTransparency >= 0.994 && endScreenTransparency < 1) {
                whiteFadeOutTime.start(); // Happens here so that it only starts it once, right before Next Level screen gets fully opaque
                Game.game.getCurrLevel().endLevel(); // Delete everything
            }

            whiteScreen.getCurrentFrame().setAlpha(endScreenTransparency);
            whiteScreen.render(0, 0);
        }
    }

    /**
     * Fade out the white screen
     */
    private void fadeOutWhite()
    {
        if (endScreenTransparency > 0)
            endScreenTransparency -= 0.02; // Fade out not as quickly

        whiteScreen.getCurrentFrame().setAlpha(endScreenTransparency);
        whiteScreen.render(0, 0);
    }
}
