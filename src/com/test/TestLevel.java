package com.test;

import com.base.engine.Display;
import com.base.game.gameobject.entity.Player;
import com.base.game.levels.Level;

import java.awt.Robot;

//TODO: implement test level with one of every item -> have player randomly move in directions (if die - respawn)
//Key log and print to file if fail
public class TestLevel extends Level {
    private boolean isAutomated;

    public TestLevel(boolean isAutomated) {
        player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "res/assets/player.png", 4f, 20, 5);

        init("res/assets/bricks.jpg", player);
        this.isAutomated = isAutomated;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        super.render();
    }
}
