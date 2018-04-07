package com.base.game.levels;

import com.base.engine.Display;
import com.base.engine.Sprite;
import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class Level extends LevelTemplate {
    private Boss boss;

    public Level(String filePath, Boss boss) {
        init(filePath);

        if (boss != null) {
            this.boss = boss;

            ui = new UI(player.getHealth(), boss.getHealth());
            addObj(boss);
        } else {
            createDoor();
        }
    }

    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
    }
}