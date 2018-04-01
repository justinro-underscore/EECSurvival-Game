package com.base.game;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Player;

public class Game {
    private Player player;

    public static Game game;

    public Game() {
        Sprite playerSprite = new Sprite(0.0f, 0.0f, 1.0f, 30, 30);
        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, playerSprite);
    }

    public void update() {
        player.update();
    }

    public void render() {
        player.render();
    }
}
