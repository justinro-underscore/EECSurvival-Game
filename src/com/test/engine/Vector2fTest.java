package com.test.engine;

import com.base.engine.Vector2f;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functionality of the vector class
 */
class Vector2fTest {
    private Vector2f v;

    /**
     * Creates a new vector <3,4> at the start of each test
     */
    @BeforeEach
    void setUp() {
        v = new Vector2f(3,4);
    }

    /**
     * Tests the length of the vector
     */
    @Test
    void length() {
        assertEquals (5, v.length());
    }

    /**
     * Tests the dot product of the vector
     */
    @Test
    void dot() {
        Vector2f v2 = new Vector2f(0, 0);
        assertEquals(0, v.dot(v2));
    }

    /**
     * Tests the angle between two vectors
     */
    @Test
    void angle() {
        Vector2f v1 = new Vector2f(0, 1);
        Vector2f v2 = new Vector2f(1, 0);
        assertEquals (90.0f, v1.angle(v2));
    }

    /**
     * Tests the normalize function of the vector
     * Checks length, X, and Y
     */
    @Test
    void normalize() {
        Vector2f v2 = v.normalize();
        assertEquals (1.0, v2.length());
        assertEquals (3.0f / 5.0f, v2.getX());
        assertEquals (4.0f / 5.0f, v2.getY());
    }

    /**
     * Tests the rotate function of the vector by rotating by 180
     */
    @Test
    void rotate() {
        Vector2f v2 = v.rotate(180);
        assertEquals (-3.0f, Math.round(v2.getX()));
        assertEquals (-4.0f, Math.round(v2.getY()));
    }

    /**
     * Tests the translate function of the vector
     */
    @Test
    void translate() {
        v = v.translate(1, 2);
        assertEquals (4.0f, v.getX());
        assertEquals (6.0f, v.getY());
    }

    /**
     * Tests the negate functionality of the vector
     */
    @Test
    void negate() {
        v = v.negate();
        assertEquals (-3.0f, v.getX());
        assertEquals (-4.0f, v.getY());
    }

    /**
     * Tests the add function of the vector
     * Takes in a float amt
     */
    @Test
    void add() {
        v = v.add(1);
        assertEquals (4.0f, v.getX());
        assertEquals (5.0f, v.getY());
    }

    /**
     * Tests the add function of the vector
     * Takes in a vector
     */
    @Test
    void add1() {
        Vector2f v2 = new Vector2f(1, 2);
        v = v.add(v2);
        assertEquals (4.0f, v.getX());
        assertEquals (6.0f, v.getY());
    }

    /**
     * Tests the subtraction of the vector by a float amt
     */
    @Test
    void sub() {
        v = v.sub(1);
        assertEquals (2.0f, v.getX());
        assertEquals (3.0f, v.getY());
    }

    /**
     * Tests the subtraction between two vectors
     */
    @Test
    void sub1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.sub(v2);
        assertEquals (2.0f, v.getX());
        assertEquals (2.0f, v.getY());
    }

    /**
     * Tests the multiplication of the vector by a float amt
     */
    @Test
    void mul() {
        v = v.mul(1);
        assertEquals (3.0f, v.getX());
        assertEquals (4.0f, v.getY());
    }

    /**
     * Tests the multiplication between two vectors
     */
    @Test
    void mul1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.mul(v2);
        assertEquals (3.0f, v.getX());
        assertEquals (8.0f, v.getY());
    }

    /**
     * Tests the division of the vector by a float amt
     */
    @Test
    void div() {
        v = v.div(1);
        assertEquals (3.0f, v.getX());
        assertEquals (4.0f, v.getY());
    }

    /**
     * Tests the division between two vectors
     */
    @Test
    void div1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.div(v2);
        assertEquals (3.0f, v.getX());
        assertEquals (2.0f, v.getY());
    }

    /**
     * Test the abs function
     */
    @Test
    void abs() {
        v = new Vector2f(-1,-1);
        v = v.abs();

        assertEquals (1.0f, v.getX());
        assertEquals (1.0f, v.getY());
    }

    /**
     * Tests the equals function
     */
    @Test
    void equals() {
        Vector2f v2 = new Vector2f(3,4);
        assert v.equals(v2);
    }

    /**
     * Tests the getY function
     */
    @Test
    void getX() {
        assertEquals (3, v.getX());
    }

    /**
     * Tests the getX function
     */
    @Test
    void getY() {
        assertEquals (4, v.getY());
    }

}