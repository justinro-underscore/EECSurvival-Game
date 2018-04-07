package com.base.game.levels;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Boss;

public class Level extends LevelTemplate {
    public Level(String filePath) {
        init(filePath);
    }

//    public void init() {
//        Sprite bossSprite = new Sprite(1.0f, 0.0f, 0.0f, 70, 70);
//        Boss boss = new Boss(Display.getWidth() / 2 - 35, Display.getHeight() - 70, bossSprite, "", 400, 5, 5);
//
//        addObj(boss);
//    }
}