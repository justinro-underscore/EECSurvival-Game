package com.base.game;

import com.base.engine.Display;
import com.base.game.gameobject.entity.Player;

public class Game {
    private Player player;

    public static Game game;

    public Game() {
        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 30, 30);
    }

    public void update() {
        player.update();
    }

    public void render() {
        player.render();
    }
}
