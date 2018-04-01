package com.base.engine;

public class GameVector
{
    public float x;
    public float y;

    public GameVector(float x, float y)
    {
        this.x = x;
        this.y = y;
        normalize();
    }

    private void normalize()
    {
        double mag = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        x *= mag;
        y *= mag;
    }
}
