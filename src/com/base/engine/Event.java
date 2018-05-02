package com.base.engine;

public class Event implements Comparable<Event> {
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
        String code = type.toLowerCase() + action.toLowerCase() + isTenured;
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

    @Override
    public int compareTo(Event o) {
        return value();
    }
}
