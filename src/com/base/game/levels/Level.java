package com.base.game.levels;

import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class Level extends LevelTemplate {
    private Boss boss;

    /**
     * Create the level
     * @param filePath the file path for the image of the background
     * @param boss the level specific boss
     */
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

    /**
     * Get the health of the player
     * @param isPlayer the player whose health we want
     * @return the player's health
     */
    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
    }
}