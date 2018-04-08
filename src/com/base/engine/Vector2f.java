package com.base.engine;

public class Vector2f
{
    public float x;
    public float y;

    /**
     * Takes in an x pos and y pos
     * @param x - x pos in px
     * @param y - y pos in px
     */
    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns length of vector
     * @return float representing length of vector
     */
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    /**
     * Dots a vector with current vector
     * @param vector vector to be dotted with current vector (this)
     * @return new vector that is result of dot product
     */
    public float dot(Vector2f vector) {
        return x * vector.getX() + y * vector.getY();
    }

    /**
     * Returns angle between two vectors
     * @param vector vector to be used in calculation with current vector (this)
     * @return float representing angle between two vectors
     */
    public float angle(Vector2f vector) {
        return (float) Math.acos(dot(vector)/(length() * vector.length()));
    }

    /**
     * Returns new vector2f representing the normalized version of current vector
     * @return Vector2f representing normalized vector
     */
    public Vector2f normalize() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    /**
     * Rotates vector by specified angle
     * @param angle float to rotate vector by
     * @return new rotated Vector2f
     */
    public Vector2f rotate(float angle) {
        float sinAngle = (float)Math.sin(-angle);
        float cosAngle = (float)Math.cos(-angle);

        return new Vector2f(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle);
    }

    /**
     * Translated vector by passed in x and y pos
     * @param x amt to shift vector by
     * @param y amt to shift vector by
     * @return new translated Vector2f
     */
    public Vector2f translate(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    /**
     * Negates the vector
     * @return new negated version of the vector
     */
    public Vector2f negate() {
        return new Vector2f(-x, -y);
    }

    /**
     * Returns two vectors added together
     * @param vector vector to add
     * @return new Vector2f
     */
    public Vector2f add(Vector2f vector) {
        return new Vector2f(x + vector.getX(), y + vector.getY());
    }

    /**
     * Returns a new vector after scalar addition
     * @param amt float amt to add to vector
     * @return new Vector2f
     */
    public Vector2f add(float amt) {
        return new Vector2f(x + amt, y + amt);
    }

    /**
     * Returns two vectors subtracted
     * @param vector vector to subtract
     * @return new Vector2f
     */
    public Vector2f sub(Vector2f vector) {
        return new Vector2f(x - vector.getX(), y - vector.getY());
    }

    /**
     * Returns a new vector after scalar subtraction
     * @param amt float amt to subtract from vector
     * @return new Vector2f
     */
    public Vector2f sub(float amt) {
        return new Vector2f(x - amt, y - amt);
    }

    /**
     * Returns two vectors multiplied together
     * @param vector vector to multiply
     * @return new Vector2f
     */
    public Vector2f mul(Vector2f vector) {
        return new Vector2f(x * vector.getX(), y * vector.getY());
    }

    /**
     * Returns a new vector after scalar multiplication
     * @param amt float amt to multiply on vector
     * @return new Vector2f
     */
    public Vector2f mul(float amt) {
        return new Vector2f(x * amt, y * amt);
    }

    /**
     * Returns two vectors divided
     * @param vector vector to divide
     * @return new Vector2f
     */
    public Vector2f div(Vector2f vector) {
        return new Vector2f(x / vector.getX(), y / vector.getY());
    }

    /**
     * Returns a new vector after scalar division
     * @param amt float amt to divide from vector
     * @return new Vector2f
     */
    public Vector2f div(float amt) {
        return new Vector2f(x / amt, y / amt);
    }

    /**
     * Returns abs valued version of vector
     * @return new Vector2f with the abs value of both x and y values 
     */
    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    /**
     * Returns a bool if two vectors are equal
     * @param vector vector to check against
     * @return bool representing is the two vectors are equal
     */
    public boolean equals(Vector2f vector) {
        return x == vector.getX() && y == vector.getY();
    }

    /**
     * Returns a string representation of the vector
     * @return string of Vector2f
     */
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    /**
     * Returns x pos of vector
     * @return float x pos of vector
     */
    public float getX() {
        return x;
    }

    /**
     * Returns y pos of vector
     * @return float y pos of vector
     */
    public float getY() {
        return y;
    }
}
