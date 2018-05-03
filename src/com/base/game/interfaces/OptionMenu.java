package com.base.game.interfaces;

import com.base.engine.Audio;
import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.engine.TextRenderer;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;
import com.base.game.levels.LevelManager;
import org.lwjgl.opengl.GL11;

import static com.base.engine.Audio.setMasterGain;
import static org.lwjgl.opengl.GL11.*;

public class OptionMenu extends Interface {

    private TextRenderer audioOptions;
    private GameButton muteButton;
    private GameButton unmuteButton;
    private GameButton closeButton;
    private GameButton mainMenuButton;

    //private int music;
    private boolean startedAudio;

    /**
     * Initialize the main menu screen
     */
    public void init(String fileName, int widthOfImage, int heightOfImage) {
        super.init(fileName, widthOfImage, heightOfImage);

        startedAudio = false;

        audioOptions = new TextRenderer("Music Options", 400, 40, true, 2, false, (float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 + 200));

        muteButton = new GameButton((float)(Display.getWidth()/2 - 425), (float)(Display.getHeight()/2 + 100), 400, 80, "Mute", this::pauseMusic);
        unmuteButton = new GameButton((float)(Display.getWidth()/2 + 25), (float)(Display.getHeight()/2 + 100), 400, 80, "Unmute", this::startAudio);
        closeButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 400, 80, "Resume",
                () -> {
                    InputHandler.clear();
                    LevelManager.resume();
                    Game.start();
                });

        mainMenuButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 300), 400, 80, "Menu",
                () -> {
                    InputHandler.clear();
                    Game.backToMenu();
                });
    }

    public void startAudio() {
        Audio.setMasterGain(100);

        startedAudio = true;
    }

    /**
     * Perform actions based off of the user clicking the buttons
     */
    public void update()
    {
        unmuteButton.update();
        muteButton.update();
        closeButton.update();
        mainMenuButton.update();
    }

    @Override
    /**
     * Render the Option Menu
     */
    public void render() {
        //calls the inherited render function
        super.render();

        renderAudioOptions();
        closeButton.render();
        mainMenuButton.render();
    }

    /**
     * Renders the test options
     */
    private void renderAudioOptions()
    {
        // Outline of a box that holds the test options
        glLineWidth(4);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4f(0.5f, 0.5f, 0.5f, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f((float)(Display.getWidth()/2 - 465), (float)(Display.getHeight()/2 + 60));
            glVertex2f((float)(Display.getWidth()/2 + 465), (float)(Display.getHeight()/2 + 60));
            glVertex2f((float)(Display.getWidth()/2 + 465), (float)(Display.getHeight()/2 + 220));
            glVertex2f((float)(Display.getWidth()/2 - 465), (float)(Display.getHeight()/2 + 220));
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        // Covers up the line
        glColor4f(0.721568627f, 0.717647059f, 0.749019608f, 0.0f);
        glBegin(GL11.GL_QUADS);
            glVertex2f((float)(Display.getWidth()/2 - 170), (float)(Display.getHeight()/2 + 200));
            glVertex2f((float)(Display.getWidth()/2 + 170), (float)(Display.getHeight()/2 + 200));
            glVertex2f((float)(Display.getWidth()/2 + 170), (float)(Display.getHeight()/2 + 240));
            glVertex2f((float)(Display.getWidth()/2 - 170), (float)(Display.getHeight()/2 + 240));
        glEnd();

        audioOptions.render(); // The text
        // The buttons
        muteButton.render();
        unmuteButton.render();
    }

    public void pauseMusic() {
        startedAudio = false;
        Audio.setMasterGain(0);
    }
}
