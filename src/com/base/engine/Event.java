package com.base.engine;

/**
 * Events are an easy way to schedule events to occur based on invocations or have events run every tick
 */
public class Event implements Comparable<Event> {
    private String type;
    private String action;

    private boolean isTenured;

    /**
     * Creates an event based on the type of event, action, and if the event runs every tick
     * @param type Type of event (eg. Player, Keyboard...)
     * @param action Type of action (eg. death, pressed...)
     * @param isTenured True if event will be run every tick, else event will only be run on manual invocation
     */
    public Event(String type, String action, boolean isTenured) {
        this.type = type;
        this.action = action;

        this.isTenured = isTenured;
    }

    /**
     * Returns true if event is tenured
     * @return isTenured property of event
     */
    public boolean isTenured() {
        return isTenured;
    }

    /**
     * Returns the value of an event so hashmaps can identify different events with the same parameters as the same event
     * @return
     */
    private int value() {
        String code = type.toLowerCase() + action.toLowerCase() + isTenured;
        return code.hashCode();
    }

    /**
     * Returns the hash code of the event based on event value
     * @return hash code of event
     */
    @Override
    public int hashCode() {
        return value();
    }

    /**
     * Checks if two events are equal
     * @param obj event to compare to
     * @return true if events are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return (this.type.equals(event.type) && this.action.equals(event.action) && this.isTenured == event.isTenured);
        }

        return false;
    }

    /**
     * Compares the hashcode of events for ordering lists and hashmaps
     * @param o event to compare to
     * @return hashcode of current event
     */
    @Override
    public int compareTo(Event o) {
        return value();
    }
}
