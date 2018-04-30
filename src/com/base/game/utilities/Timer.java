package com.base.game.utilities;

public class Timer
{
    private int duration;
    private long endTime;
    private boolean repeated;

    private boolean paused;
    private long startPause;

    private Runnable func;

    /**
     * Create a Timer object
     * @param time_ms the time to delay
     * @param func
     */
    public Timer(int time_ms, boolean repeat, Runnable func)
    {
        duration = time_ms;
        endTime = Time.getTime() + duration;
        repeated = repeat;
        paused = false;
        this.func = func;
    }

    /**
     * Checks if the Timer is expired
     */
    public void update()
    {
        if(!paused && Time.getTime() > endTime)
        {
            func.run();
            // TODO Delete
        }
    }

    /**
     * Pauses the timer
     */
    public void pause()
    {
        paused = true;
        startPause = Time.getTime();
    }

    /**
     * Resumes the timer
     */
    public void resume()
    {
        endTime += (Time.getTime() - startPause);
        paused = false;
    }
}
