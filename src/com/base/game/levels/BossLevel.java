package com.base.game.levels;

import com.base.engine.Display;
import com.base.engine.Vector2f;
import com.base.game.Game;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.interfaces.UI;
import com.base.game.scenes.Scene;
import com.base.game.utilities.Delay;
import com.base.game.utilities.Util;

public class BossLevel extends Level {
    private Boss boss;
    private Scene scene;
    private boolean isCutsceneOver;
    private boolean shouldCheckForUIUpdate;

    private ConsumableItem consumableItem;
    private Delay consumableItemDelay;

    /**
     * Boss Level Creator
     * @param filePath Boss Image
     * @param width width of img
     * @param height height of img
     * @param boss Boss Object
     * @param player player obj
     * @param scriptPath path to custom cutscene script
     */
    public BossLevel(String filePath, int width, int height, Boss boss, Player player, String scriptPath) {
        init(filePath, width, height, player);
        this.boss = boss;

        ui = new UI(player.getMaxHealth(), boss.getMaxHealth());
        shouldCheckForUIUpdate = true;

        addObj(boss);

        scene = new Scene(scriptPath, player, boss, this);

        consumableItemDelay = new Delay(16000);
    }

    @Override
    /**
     * Update boss level
     */
    public void update() {
        if (!isCutsceneOver) {
            isCutsceneOver = true;
            scene.run();
            consumableItemDelay.start();
        }

        if(consumableItemDelay.isOver() && !stopSpawningConsumables){
            consumableItem = new ConsumableItem(Util.randomWithRange(50, Display.getWidth() - 100), Util.randomWithRange(100, Display.getHeight() - 250), 50, 50, "", 8000, 5);
            addObj(consumableItem);
            consumableItemDelay.start();
        }

        if(shouldCheckForUIUpdate && boss.checkForUpdateUI())
        {
            resetUI();
            shouldCheckForUIUpdate = false;
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

    /**
     * Get the boss
     * @return the boss
     */
    public Boss getBoss(){
        return boss;
    }

    /**
     * Resets the UI of the level
     */
    public void resetUI()
    {
        ui = new UI(player.getMaxHealth(), boss.getMaxHealth());
    }
}