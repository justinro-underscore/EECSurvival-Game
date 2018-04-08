package com.base.game.levels;

import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class EmptyLevel extends Level {
    private Boss boss;

    /**
     * Empty Level Creator
     * @param filePath takes in a file path for the empty level

     */
    public EmptyLevel(String filePath) {
        init(filePath);
        createDoor();
    }
}