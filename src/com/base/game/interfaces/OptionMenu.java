package com.base.game.interfaces;

import com.base.engine.Audio;
import com.base.engine.Display;
import com.base.engine.InputHandler;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;
import com.base.game.levels.LevelManager;

import static com.base.engine.Audio.setMasterGain;

public class OptionMenu extends Interface {

    private GameButton muteButton;
    private GameButton unmuteButton;
    private GameButton closeButton;
    private GameButton mainMenuButton;

    //private int music;
    private boolean startedAudio;

    /**
     * Initialize the main menu screen
     */
    public void init(String fileName) {
        super.init(fileName, 200, 200);

        startedAudio = false;
//        music = Audio.loadSound("res/audio/Ove_Melaa_Times.ogg");

        muteButton = new GameButton((float)(Display.getWidth()/2), (float)(Display.getHeight()/2 + 100), 400, 80, "Mute", this::pauseMusic);

        unmuteButton = new GameButton((float)(Display.getWidth()/2 - 400), (float)(Display.getHeight()/2 + 100), 400, 80, "Unmute", this::startAudio);
        closeButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 400, 80, "Resume",
                () -> {
                    InputHandler.clear();
                    Game.start();
                });

        mainMenuButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 300), 400, 80, "Menu",
                () -> {
                    InputHandler.clear();
                    LevelManager.reLoadLevels();
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

        muteButton.render();
        unmuteButton.render();
        closeButton.render();
        mainMenuButton.render();
    }

    public void pauseMusic() {
        startedAudio = false;
        Audio.setMasterGain(0);
    }
}
