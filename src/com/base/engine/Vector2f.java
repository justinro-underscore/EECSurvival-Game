package com.base.engine;

/**
 * Vector class (takes two params and creates a vector)
 */
public class Vector2f
{
    public float x;
    public float y;

    /**
     * create a vector
     * @param x the x length
     * @param y the y length
     */
    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * get the length of the vector
     * @return the length of the vector
     */
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    /**
     * get the dot product
     * @param vector the passed in vector
     * @return the dot product
     */
    public float dot(Vector2f vector) {
        return x * vector.getX() + y * vector.getY();
    }

    /**
     * Gets the angle between two vector
     * @param vector the other vector to find angle between
     * @return angle between the two vectors
     */
    public float angle(Vector2f vector) {
        return (float) Math.toDegrees(Math.acos(dot(vector)/(length() * vector.length())));
    }

    /**
     * normalize the vector
     * @return the normalized vector
     */
    public Vector2f normalize() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    /**
     * Rotates the vector by an angle (in degrees)
     * @param angle amt to rotate vector by
     * @return new rotated vector
     */
    public Vector2f rotate(float angle) {
        angle = (float) Math.toRadians(angle);
        float sinAngle = (float)Math.sin(-angle);
        float cosAngle = (float)Math.cos(-angle);

        return new Vector2f(x * cosAngle - y * sinAngle, x * sinAngle + y * cosAngle);
    }

    /**
     * Translates the vector by x and y
     * @param x amt to move vector by in the x dir
     * @param y amt to mouse vector by in the y dir
     * @return new translated vector
     */
    public Vector2f translate(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    /**
     * Negates the vector
     * @return Returns the negated vector
     */
    public Vector2f negate() {
        return new Vector2f(-x, -y);
    }

    /**
     * Adds two vectors together
     * @param vector Vector to add to
     * @return new vector
     */
    public Vector2f add(Vector2f vector) {
        return new Vector2f(x + vector.getX(), y + vector.getY());
    }

    /**
     * Add a scalar amt to the vector
     * @param amt amt to add to vector
     * @return new vector
     */
    public Vector2f add(float amt) {
        return new Vector2f(x + amt, y + amt);
    }

    /**
     * Subtracts two vectors
     * @param vector vector to subtract
     * @return new vector
     */
    public Vector2f sub(Vector2f vector) {
        return new Vector2f(x - vector.getX(), y - vector.getY());
    }

    /**
     * Subtract the vector by a scalar amt
     * @param amt amt to subtract from vector
     * @return new vector
     */
    public Vector2f sub(float amt) {
        return new Vector2f(x - amt, y - amt);
    }

    /**
     * Multiplies two vectors together
     * @param vector vector to multiply with
     * @return new vector
     */
    public Vector2f mul(Vector2f vector) {
        return new Vector2f(x * vector.getX(), y * vector.getY());
    }

    /**
     * Multiple vector by scalar amt
     * @param amt amt to multiply vector by
     * @return new vector
     */
    public Vector2f mul(float amt) {
        return new Vector2f(x * amt, y * amt);
    }

    /**
     * Divide vector by another vector
     * @param vector vector to divide by
     * @return new Vector
     */
    public Vector2f div(Vector2f vector) {
        return new Vector2f(x / vector.getX(), y / vector.getY());
    }

    /**
     * Divide vector by scalar amt
     * @param amt amt to divide by
     * @return new Vector
     */
    public Vector2f div(float amt) {
        return new Vector2f(x / amt, y / amt);
    }

    /**
     * Takes the abs value of the vector
     * @return new vector
     */
    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    /**
     * Checks if the two vectors are equal
     * @param vector vector to compare
     * @return true if vectors are equal
     */
    public boolean equals(Vector2f vector) {
        return x == vector.getX() && y == vector.getY();
    }

    /**
     * stringify a vector
     * @return the stringfied vector
     */
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    /**
     * get x position
     * @return the x position
     */
    public float getX() {
        return x;
    }

    /**
     * get y position
     * @return the y position
     */
    public float getY() {
        return y;
    }
}
