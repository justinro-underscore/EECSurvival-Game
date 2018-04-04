package com.base.engine;

import java.awt.*;

public class Physics {
    public static boolean checkCollision(GameObject obj1, GameObject obj2) {
        Rectangle r1 = new Rectangle((int) (obj1.getX() - (obj1.getWidth()/2.0f)), (int) (obj1.getY()  - (obj1.getHeight()/2.0f)), obj1.getWidth(), obj1.getHeight());
        Rectangle r2 = new Rectangle((int) (obj2.getX() - (obj2.getWidth()/2.0f)), (int) (obj2.getY()  - (obj2.getHeight()/2.0f)), obj2.getWidth(), obj2.getHeight());

        return r1.intersects(r2);
    }

    public static boolean checkCollision(Rectangle field, GameObject obj) {
        Rectangle r = new Rectangle((int) (obj.getX() - (obj.getWidth()/2.0f)), (int) (obj.getY()  - (obj.getHeight()/2.0f)), obj.getWidth(), obj.getHeight());

        return field.intersects(r);
    }
}


