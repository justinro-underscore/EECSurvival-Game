package com.base.engine;

public class Event {
    private String type;
    private String action;

    private boolean isTenured;

    public Event(String type, String action, boolean isTenured) {
        this.type = type;
        this.action = action;

        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    private int value() {
        String code = type + action + isTenured;
        return code.hashCode();
    }

    @Override
    public int hashCode() {
        return value();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return (this.type.equals(event.type) && this.action.equals(event.action) && this.isTenured == event.isTenured);
        }

        return false;
    }
}
