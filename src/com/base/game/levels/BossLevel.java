package com.base.game.levels;

import com.base.engine.Vector2f;
import com.base.game.Game;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.projectile.StandardProjectile;
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
    public int getHealth(boolean isPlayer)
    {
        return (isPlayer ? player.getHealth() : boss.getHealth());
    }

    /**
     * TODO Change this
     * Kills the boss for an easy peasy presentation
     */
    public void killBoss()
    {
        StandardProjectile s = new StandardProjectile(boss.getX(), boss.getY(), 10, 10, "", new Vector2f(0, 1), 400, 1);
        Game.game.addObj(s);
    }
}