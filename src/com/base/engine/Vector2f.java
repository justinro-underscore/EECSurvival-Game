package com.base.engine;

public class Vector2f
{
    public float x;
    public float y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f vector) {
        return x * vector.getX() + y * vector.getY();
    }

    public float angle(Vector2f vector) {
        return (float) Math.acos(dot(vector)/(length() * vector.length()));
    }

    public Vector2f normalize() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public Vector2f rotate(float angle) {
        float sinAngle = (float)Math.sin(-angle);
        float cosAngle = (float)Math.cos(-angle);

        return new Vector2f(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle);
    }

    public Vector2f translate(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }
    
    public Vector2f negate() {
        return new Vector2f(-x, -y);
    }

    public Vector2f add(Vector2f vector) {
        return new Vector2f(x + vector.getX(), y + vector.getY());
    }

    public Vector2f add(float amt) {
        return new Vector2f(x + amt, y + amt);
    }

    public Vector2f sub(Vector2f vector) {
        return new Vector2f(x - vector.getX(), y - vector.getY());
    }

    public Vector2f sub(float amt) {
        return new Vector2f(x - amt, y - amt);
    }

    public Vector2f mul(Vector2f vector) {
        return new Vector2f(x * vector.getX(), y * vector.getY());
    }

    public Vector2f mul(float amt) {
        return new Vector2f(x * amt, y * amt);
    }

    public Vector2f div(Vector2f vector) {
        return new Vector2f(x / vector.getX(), y / vector.getY());
    }

    public Vector2f div(float amt) {
        return new Vector2f(x / amt, y / amt);
    }

    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public boolean equals(Vector2f vector) {
        return x == vector.getX() && y == vector.getY();
    }

    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
