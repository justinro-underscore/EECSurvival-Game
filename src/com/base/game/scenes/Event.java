package com.base.game.scenes;

import java.util.concurrent.Callable;

public class Event {
    private String type;
    private String action;
    private boolean isOver;
    private Callable<Boolean> runnable;

    public Event(String type, String action, Callable<Boolean> callback) {
        this.type = type;
        this.action = action;

        isOver = false;
        runnable = callback;
    }

    private int value() {
        String code = type.toLowerCase() + action.toLowerCase();
        return code.hashCode();
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

    @Override
    public int hashCode() {
        return value();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return (this.type.equals(event.type) && this.action.equals(event.action));
        }

        return false;
    }
}
