package com.base.game.utilities;

/**
 * Adapted from rpg opengl tutorial series by TheBennyBox
 */
public class Delay {
    private int duration;
    private long endTime;
    private long startTime;
    private boolean hasStarted;

    public Delay(int time_ms) {
        duration = time_ms;
        hasStarted = false;
    }

    public boolean isOver() {
        if (!hasStarted)
            return false;

        return (Time.getTime() > endTime);
    }

    public boolean isStarted() {
        return hasStarted;
    }

    public void start() {
        hasStarted = true;
        startTime = Time.getTime();
        endTime = duration + startTime;
    }

    public void stop() {
        hasStarted = false;
    }

    public void restart() {
        hasStarted = true;
        endTime = 0;
        startTime = 0;
    }

    public void restart(int time_ms) {
        duration = time_ms;
        restart();
    }

    public long getCurrTime() {
        return Time.getTime() - startTime;
    }
}

