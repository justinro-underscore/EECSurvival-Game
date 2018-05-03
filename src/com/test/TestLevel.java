package com.test;

import com.base.engine.Display;
import com.base.engine.Vector2f;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.EmptyBoss;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.interfaces.UI;
import com.base.game.levels.Level;
import com.base.game.utilities.Delay;
import com.base.game.utilities.Time;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.awt.event.KeyEvent.*;

/**
 * Test level to be used for acceptance testing
 */
public class TestLevel extends Level {
    private boolean isAutomated;
    private ArrayList<Integer> keyLog;
    private Robot robot;
    private Delay keyDelay;
    private PrintWriter writer;
    private int currentKey;
    private ConsumableItem consumableItem2;

    private Projectile projectile;
    private Boss boss;

    /**
     * Creates a new test level that is automated or not automated
     * @param isAutomated if test is automated or not
     */
    public TestLevel(boolean isAutomated) {
        this.isAutomated = isAutomated;

        keyLog = new ArrayList<>();
        keyDelay = new Delay(200);
        keyDelay.restart();

        currentKey = VK_W;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Player player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 80, 80, 4f, 20, 5,"res/SpriteSheets/walkcyclevarious.png");

        init("res/assets/classroom.png", 1280, 960, player);

        boss = new EmptyBoss(Display.getWidth() / 2 - 35, Display.getHeight() - 280, 170, 215, "res/assets/bardasWhite.png", 3f,60, 8);
        projectile = new StandardProjectile(100, 100, 50, 50, "res/assets/white.png", new Vector2f(1,1), 5, 0, true);
        consumableItem2 = new ConsumableItem(400,400, 50, 50, "res/assets/white.png", -1, 5);

        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);
        addObj(projectile);
        addObj(consumableItem2);
    }

    /**
     * Either uses Level's update or overriden automated updating
     */
    @Override
    public void update() {
        if (!isAutomated) {
            super.update();
            return;
        }

        try {
            autoUpdate();
        } catch (Exception e) {
            try {
                writer = new PrintWriter("crashLog" + Time.getTime() + ".txt", "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            writer.println(keyLog);
            writer.close();
        }
    }

    /**
     * Uses Level's render method
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Auto moves the character
     */
    private void autoUpdate() {
        robot.keyPress(VK_SPACE);

        if (keyDelay.isOver()) {
            robot.keyRelease(currentKey);
            keyDelay.start();

            return;
        }

        int dir = ThreadLocalRandom.current().nextInt(0, 4);

        switch (dir) {
            case 0:
                currentKey = VK_W;
                break;
            case 1:
                currentKey = VK_S;
                break;
            case 2:
                currentKey = VK_A;
                break;
            case 3:
                currentKey = VK_D;
                break;
        }

        robot.keyPress(currentKey);
        keyLog.add(currentKey);
        super.update();
    }

    /**
     * Checks if level is over
     * @param lose boolean value that checks if the level is over
     */
    @Override
    public void levelOver(boolean lose) {
        if (lose) {
            player.respawn();
        }
    }

    /**
     * Returns the boss of the level
     * @return null for now
     */
    @Override
    public Boss getBoss() {
        return null;
    }

    /**
     * Returns either the player or boss health
     * @param isPlayer if we want the player or boss health
     * @return player or boss health
     */
    @Override
    public int getHealth(boolean isPlayer) {
        if (isPlayer)
            return player.getHealth();

        return boss.getHealth();
    }
}
