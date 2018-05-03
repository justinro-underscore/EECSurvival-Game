package com.base.engine;

import java.util.*;

/**
 * Manages all the events running in the game
 */
public class EventQueue {
    private static HashMap<Event, Runnable> events;
    private static Queue<Runnable> runnables;
    private static List<Runnable> tenureRunnables;

    /**
     * Creates a new event list and tenured events list
     */
    public static void init() {
        events = new HashMap<>();
        runnables = new PriorityQueue<>(new Comparator<Runnable>() {
            @Override
            public int compare(Runnable o1, Runnable o2) {
                return 0;
            }
        });
        tenureRunnables = new ArrayList<>();
    }

    /**
     * Calls current runnables and tenured events
     */
    public static void update() {
        while (!runnables.isEmpty()) {
            runnables.poll().run();
        }

        for (Runnable r : tenureRunnables) {
            r.run();
        }
    }

    /**
     * Invoke an event's callback
     * @param event event to invoke callback for
     */
    public static void invokeCallback(Event event) {
        if (events.containsKey(event)) {
            runnables.add(events.get(event));
        }
    }

    /**
     * Registers a callback for a specific event
     * @param event event to register callback for
     * @param callback runnable to register
     */
    public static void registerCallback(Event event, Runnable callback) {
        if (events.containsKey(event)) {
            deleteCallback(event);
        }

        if (event.isTenured()) {
            tenureRunnables.add(callback);
        }

        events.put(event, callback);
    }

    /**
     * Deletes a callback for a specific event
     * @param event event to delete callback from
     */
    private static void deleteCallback(Event event) {
        if (event.isTenured()) {
            tenureRunnables.remove(events.get(event));
        }

        events.remove(event);
    }
}
