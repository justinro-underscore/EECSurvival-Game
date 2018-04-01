package com.base.engine;

import java.awt.*;

public class Physics {
    public boolean checkCollision(GameObject obj1, GameObject obj2) {
        Rectangle r1 = new Rectangle(obj1.getX(), obj1.getY(), obj1.getWidth(), obj1.getHeight());
        Rectangle r2 = new Rectangle(obj2.getX(), obj2.getY(), obj2.getWidth(), obj2.getHeight());

        return r1.intersects(r2);
    }
}
