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

public class TestLevel extends Level {
    private boolean isAutomated;
    private ArrayList<Integer> keyLog;
    private Robot robot;
    private Delay keyDelay;
    private PrintWriter writer;
    private int currentKey;
    private ConsumableItem consumableItem2;

    public TestLevel(boolean isAutomated) {
        this.isAutomated = isAutomated;

        keyLog = new ArrayList<>();
        keyDelay = new Delay(100);
        keyDelay.restart();

        currentKey = VK_W;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Player player = new Player(Display.getWidth() / 2 - 30, Display.getHeight() / 2 - 30, 41, 82, "res/assets/player.png", 4f, 20, 5);

        init("res/assets/bricks.jpg", player);

        Boss boss = new EmptyBoss(Display.getWidth() / 2 - 35, Display.getHeight() - 150, 70, 70, "", 3f,60, 8);
        Projectile projectile = new StandardProjectile(100, 100, 100, 100, "", new Vector2f(1,1), 5, 0, true);
        consumableItem2 = new ConsumableItem(400,400, 50, 50, "", -1, 5);

        ui = new UI(player.getHealth(), boss.getHealth());
        addObj(boss);
        addObj(projectile);
        addObj(consumableItem2);
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
            try {
                writer = new PrintWriter("crashLog" + Time.getTime() + ".txt", "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            writer.println(keyLog);
            writer.close();
        }
    }

    @Override
    public void render() {
        super.render();
    }

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

    @Override
    public void levelOver(boolean lose) {
        if (lose) {
            player.respawn();
        }
    }
}
