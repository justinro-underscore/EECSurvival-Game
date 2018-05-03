package com.base.game.utilities;

/**
 * Adapted from rpg opengl tutorial series by TheBennyBox
 */
public class Delay {
    private int duration;
    private long endTime;
    private long startTime;
    private boolean hasStarted;

    /**
     * Create a delay object
     * @param time_ms the time to delay
     */
    public Delay(int time_ms) {
        duration = time_ms;
        hasStarted = false;
    }

    /**
     * If the delay is over, it is true
     * @return true if the delay is over
     */
    public boolean isOver() {
        if (!hasStarted)
            return false;

        return (Time.getTime() > endTime);
    }

    /**
     * If the delay has started
     * @return if the delay has started, return true
     */
    public boolean isStarted() {
        return hasStarted;
    }

    /**
     * Start the delay
     */
    public void start() {
        hasStarted = true;
        startTime = Time.getTime();

        endTime = duration + startTime;
    }

    /**
     * Ends the delay
     */
    public void end() {
        if(hasStarted)
            endTime = Time.getTime();
    }

    /**
     * Stop the delay
     */
    public void stop() {
        hasStarted = false;
    }

    /**
     * Restart the delay
     */
    public void restart() {
        hasStarted = true;
        endTime = 0;
        startTime = 0;
    }

    /**
     * Restart the delay with a new duration
     * @param time_ms the new delay duration
     */
    public void restart(int time_ms) {
        duration = time_ms;
        restart();
    }

    /**
     * Get the current time the delay has taken
     * @return the amount of time the delay has taken
     */
    public long getCurrTime() {
        return Time.getTime() - startTime;
    }
}

