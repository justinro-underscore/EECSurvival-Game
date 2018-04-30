package com.test;

import com.base.engine.Display;
import com.base.engine.GameObject;
import com.base.engine.Vector2f;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.EmptyBoss;
import com.base.game.gameobject.entity.Player;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.interfaces.UI;
import com.base.game.levels.Level;
import com.base.game.utilities.Delay;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.awt.event.KeyEvent.*;

public class TestLevel extends Level {
    private boolean isAutomated;
    private ArrayList<Integer> keyLog;
    private Robot robot;
    private Delay keyDelay;

    public TestLevel(boolean isAutomated) {
        this.isAutomated = isAutomated;

        keyLog = new ArrayList<>();
        keyDelay = new Delay(100);
        keyDelay.restart();

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Player player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "res/assets/player.png", 4f, 20, 5);

        init("res/assets/bricks.jpg", player);

        Boss boss = new EmptyBoss(Display.getWidth() / 2 - 35, Display.getHeight() - 150, 70, 70, "", 3f,60, 8);
        Projectile projectile = new StandardProjectile(100, 100, 100, 100, "", new Vector2f(1,1), 5, 0, true);

        //TODO: respawn projectile and item and boss
        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);
        addObj(projectile);
    }

    @Override
    public void update() {
        if (!isAutomated) {
            super.update();
            return;
        }

        try {
            autoUpdate();
        } catch (Exception e) {
            //TODO: output keyLog
        }
    }

    @Override
    public void render() {
        super.render();
    }

    private void autoUpdate() {
        robot.keyPress(VK_SPACE);

        if (keyDelay.isOver()) {
            robot.keyRelease(VK_W);
            robot.keyRelease(VK_D);
            robot.keyRelease(VK_A);
            robot.keyRelease(VK_S);

            keyDelay.start();
            return;
        }

        int dir = ThreadLocalRandom.current().nextInt(0, 4);

        switch (dir) {
            case 0:
                robot.keyPress(VK_W);
                keyLog.add(VK_W);

                break;
            case 1:
                robot.keyPress(VK_S);
                keyLog.add(VK_S);

                break;
            case 2:
                robot.keyPress(VK_A);
                keyLog.add(VK_A);

                break;
            case 3:
                robot.keyPress(VK_D);
                keyLog.add(VK_D);

                super.update();
                break;
        }

        super.update();
    }

    @Override
    public void levelOver(boolean lose) {
        if (lose) {
            player.respawn();
        }
    }
}
