package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.gameobject.button.GameButton;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu extends Interface {
    private GameButton startButton;
    private GameButton quitButton;

    // TODO: implement button delay of 10ms

    @Override
    public void init(String fileName) {
        super.init(fileName);

        startButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 + 100), 400, 80,
                "./res/start_release.png", "./res/start_press.png", () -> Display.start());
        quitButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 400, 80,
                "./res/quit_release.png", "./res/quit_press.png", () -> Display.quit());
    }

    public void update() {
        startButton.update();
        quitButton.update();
    }

    @Override
    public void render() {
        super.render();

        startButton.render();
        quitButton.render();
    }
}
