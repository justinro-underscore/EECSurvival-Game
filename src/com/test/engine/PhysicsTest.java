package com.test.engine;

import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.game.gameobject.entity.Empty;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the collision methods of the physics class
 */
class PhysicsTest {

    /**
     * Tests collisions between gameObjects
     */
    @Test
    void checkCollision() {
        GameObject obj1 = new Empty(10, 10, 10, 10);
        GameObject obj2 = new Empty(10, 10, 10, 10);
        GameObject obj3 = new Empty(100, 100, 10, 10);

        assertTrue(Physics.checkCollision(obj1, obj2));
        assertFalse(Physics.checkCollision(obj1, obj3));
    }

    /**
     * Checks collision between point and gameObject
     */
    @Test
    void checkCollision1() {
        GameObject obj1 = new Empty(10, 10, 10, 10);

        assertTrue(Physics.checkCollision(obj1, 12, 12));
        assertFalse(Physics.checkCollision(obj1, 100, 100));
    }
}