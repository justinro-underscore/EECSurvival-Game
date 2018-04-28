package com.test.engine;

import com.base.engine.Vector2f;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Vector2fTest {
    private Vector2f v;

    @BeforeEach
    void setUp() {
        v = new Vector2f(1,1);
    }

    @Test
    void length() {
        assert (v.length() != 0);
    }

    @Test
    void dot() {

    }

    @Test
    void angle() {
    }

    @Test
    void normalize() {
    }

    @Test
    void rotate() {
    }

    @Test
    void translate() {
    }

    @Test
    void negate() {
    }

    @Test
    void add() {
    }

    @Test
    void add1() {
    }

    @Test
    void sub() {
    }

    @Test
    void sub1() {
    }

    @Test
    void mul() {
    }

    @Test
    void mul1() {
    }

    @Test
    void div() {
    }

    @Test
    void div1() {
    }

    @Test
    void abs() {
    }

    @Test
    void equals() {
        Vector2f v2 = new Vector2f(1,1);
        assert v.equals(v2);
    }

    @Test
    void getX() {
        assert (v.getX() == 1);
    }

    @Test
    void getY() {
        assert (v.getY() == 1);
    }

}