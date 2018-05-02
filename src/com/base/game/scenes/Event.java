package com.base.game.scenes;

import java.util.concurrent.Callable;

public class Event {
    private String action;
    private boolean isOver;
    private Callable<Boolean> runnable;

    public Event(String action, Callable<Boolean> callback) {
        this.action = action;

        isOver = false;
        runnable = callback;
    }

    public void exec() {
        try {
            isOver = runnable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isOver() {
        return isOver;
    }
}
