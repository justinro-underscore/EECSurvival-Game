package com.base.game.levels;

import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class BossLevel extends Level {
    private Boss boss;


    /**
     * Boss Level Creator
     * @param filePath Boss Image
     * @param boss Boss Object

     */
    public BossLevel(String filePath, Boss boss) {
        init(filePath);
        this.boss = boss;

        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);
    }

    @Override
    /**
     * get the health of the passed in player
     */
    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
    }
}