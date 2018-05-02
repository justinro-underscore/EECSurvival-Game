package com.base.game.levels;

import com.base.game.Game;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.interfaces.UI;

public class EmptyLevel extends Level {
    private Boss boss;

    /**
     * Empty Level Creator
     * @param filePath takes in a file path for the empty level

     */
    public EmptyLevel(String filePath, int width, int height, Player player, boolean hasDoor) {
        init(filePath, width, height, player);

        if (hasDoor)
            createDoor();
    }

    public Boss getBoss(){
        return null;
    }
}