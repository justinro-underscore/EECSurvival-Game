package com.base.engine;

import java.awt.*;

/**
 * Physics engine
 * Checks collisions on various objects
 */
public class Physics {
    /**
     * Check if two GameObjects have collided
     * @param obj1 The first GameObject to check
     * @param obj2 The second GameObject to check
     * @return whether or not the GameObjects intersect
     */
    public static boolean checkCollision(GameObject obj1, GameObject obj2) {
        Rectangle r1 = new Rectangle((int) (obj1.getX() - (obj1.getWidth()/2.0f)), (int) (obj1.getY()  - (obj1.getHeight()/2.0f)), obj1.getWidth(), obj1.getHeight());

        return checkCollision(r1, obj2);
    }

    /**
     * Check if a GameObject is in a given field
     * @param field The field of collision detection
     * @param obj The GameObject being tested
     * @return whether or not the GameObject is in the given field
     */
    public static boolean checkCollision(Rectangle field, GameObject obj) {
        Rectangle r = new Rectangle((int) (obj.getX() - (obj.getWidth()/2.0f)), (int) (obj.getY()  - (obj.getHeight()/2.0f)), obj.getWidth(), obj.getHeight());

        return field.intersects(r);
    }

    /**
     * Check if a point is within a GameObject
     * @param obj The GameObject to check
     * @param x The x value of the point
     * @param y The y value of the point
     * @return whether or not the point is in the GameObject
     */
    public static boolean checkCollision(GameObject obj, int x, int y) {
        Rectangle r = new Rectangle((int) (obj.getX() - (obj.getWidth()/2.0f)), (int) (obj.getY()  - (obj.getHeight()/2.0f)), obj.getWidth(), obj.getHeight());

        return r.contains(x, y);
    }
}


