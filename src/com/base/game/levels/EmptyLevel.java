package com.base.game.levels;

import com.base.game.gameobject.entity.Boss;
import com.base.game.interfaces.UI;

public class EmptyLevel extends Level {
    private Boss boss;

    public EmptyLevel(String filePath) {
        init(filePath);
        createDoor();
    }
}