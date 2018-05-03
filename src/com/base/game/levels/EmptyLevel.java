package com.base.game.levels;

import com.base.game.Game;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.interfaces.UI;

public class EmptyLevel extends Level {
    private Boss boss;

    /**
     * An empty level
     * @param filePath background image
     * @param width width of the level
     * @param height height of the level
     * @param player the player
     * @param hasDoor if it has a door
     */
    public EmptyLevel(String filePath, int width, int height, Player player, boolean hasDoor) {
        init(filePath, width, height, player);

        if (hasDoor)
            createDoor();
    }

    /**
     * Don't get the boss, this is an emptyLevel
     * @return null
     */
    public Boss getBoss(){
        return null;
    }
}