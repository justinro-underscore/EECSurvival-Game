package com.base.game.levels;

import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class BossLevel extends Level {
    private Boss boss;

    public BossLevel(String filePath, Boss boss) {
        init(filePath);
        this.boss = boss;

        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);
    }

    @Override
    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
    }
}