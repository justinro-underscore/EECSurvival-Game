package com.base.game.scenes;

import java.util.concurrent.Callable;

public class Event {
    private String action;
    private boolean isOver;
    private Callable<Boolean> runnable;

    /**
     * Create an event
     * @param action event's action
     * @param callback callback for the event
     */
    public Event(String action, Callable<Boolean> callback) {
        this.action = action;

        isOver = false;
        runnable = callback;
    }

    /**
     * Execute the event
     */
    public void exec() {
        try {
            isOver = runnable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Is the event over
     * @return true if it is over
     */
    public boolean isOver() {
        return isOver;
    }
}
