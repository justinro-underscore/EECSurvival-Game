package com.test.engine;

import com.base.engine.Vector2f;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assert (v.length() == 5);
    }

    /**
     * Tests the dot product of the vector
     */
    @Test
    void dot() {
        Vector2f v2 = new Vector2f(0, 0);
        assert (v.dot(v2) == 0);
    }

    /**
     * Tests the angle between two vectors
     */
    @Test
    void angle() {
        Vector2f v1 = new Vector2f(0, 1);
        Vector2f v2 = new Vector2f(1, 0);
        assert (v1.angle(v2) == 90.0f);
    }

    /**
     * Tests the normalize function of the vector
     * Checks length, X, and Y
     */
    @Test
    void normalize() {
        Vector2f v2 = v.normalize();
        assert (v2.length() == 1.0);
        assert (v2.getX() == 3.0f / 5.0f);
        assert (v2.getY() == 4.0f / 5.0f);
    }

    /**
     * Tests the rotate function of the vector by rotating by 180
     */
    @Test
    void rotate() {
        Vector2f v2 = v.rotate(180);
        assert (Math.round(v2.getX()) == -3.0f);
        assert (Math.round(v2.getY()) == -4.0f);
    }

    /**
     * Tests the translate function of the vector
     */
    @Test
    void translate() {
        v = v.translate(1, 2);
        assert (v.getX() == 4.0f);
        assert (v.getY() == 6.0f);
    }

    /**
     * Tests the negate functionality of the vector
     */
    @Test
    void negate() {
        v = v.negate();
        assert (v.getX() == -3.0f);
        assert (v.getY() == -4.0f);
    }

    /**
     * Tests the add function of the vector
     * Takes in a float amt
     */
    @Test
    void add() {
        v = v.add(1);
        assert (v.getX() == 4.0f);
        assert (v.getY() == 5.0f);
    }

    /**
     * Tests the add function of the vector
     * Takes in a vector
     */
    @Test
    void add1() {
        Vector2f v2 = new Vector2f(1, 2);
        v = v.add(v2);
        assert (v.getX() == 4.0f);
        assert (v.getY() == 6.0f);
    }

    /**
     * Tests the subtraction of the vector by a float amt
     */
    @Test
    void sub() {
        v = v.sub(1);
        assert (v.getX() == 2.0f);
        assert (v.getY() == 3.0f);
    }

    /**
     * Tests the subtraction between two vectors
     */
    @Test
    void sub1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.sub(v2);
        assert (v.getX() == 2.0f);
        assert (v.getY() == 2.0f);
    }

    /**
     * Tests the multiplication of the vector by a float amt
     */
    @Test
    void mul() {
        v = v.mul(1);
        assert (v.getX() == 3.0f);
        assert (v.getY() == 4.0f);
    }

    /**
     * Tests the multiplication between two vectors
     */
    @Test
    void mul1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.mul(v2);
        assert (v.getX() == 3.0f);
        assert (v.getY() == 8.0f);
    }

    /**
     * Tests the division of the vector by a float amt
     */
    @Test
    void div() {
        v = v.div(1);
        assert (v.getX() == 3.0f);
        assert (v.getY() == 4.0f);
    }

    /**
     * Tests the division between two vectors
     */
    @Test
    void div1() {
        Vector2f v2 = new Vector2f(1,2);
        v = v.div(v2);
        assert (v.getX() == 3.0f);
        assert (v.getY() == 2.0f);
    }

    /**
     * Test the abs function
     */
    @Test
    void abs() {
        v = new Vector2f(-1,-1);
        v = v.abs();
        assert (v.getX() == 1.0f);
        assert (v.getY() == 1.0f);
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
        assert (v.getX() == 3);
    }

    /**
     * Tests the getX function
     */
    @Test
    void getY() {
        assert (v.getY() == 4);
    }

}