package com.base.game.levels;

import com.base.engine.Vector2f;
import com.base.game.Game;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.interfaces.UI;
import com.base.game.scenes.Scene;

public class BossLevel extends Level {
    private Boss boss;
    private Scene scene;
    private boolean isCutsceneOver;

    /**
     * Boss Level Creator
     * @param filePath Boss Image
     * @param boss Boss Object

     */
    public BossLevel(String filePath, int width, int height, Boss boss, Player player, String scriptPath) {
        init(filePath, width, height, player);
        this.boss = boss;

        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);

        scene = new Scene(scriptPath, player, boss, this);
        isCutsceneOver = false;
    }

    @Override
    public void update() {
        if (!isCutsceneOver) {
            isCutsceneOver = true;
            scene.run();
        }

        super.update();
    }

    @Override
    /**
     * get the health of the passed in player
     */
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
        StandardProjectile s = new StandardProjectile(boss.getX(), boss.getY(), 10, 10, "res/assets/white.png", new Vector2f(0, 1), 400, 1 , false);
        Game.game.getCurrLevel().addObj(s);
    }
}