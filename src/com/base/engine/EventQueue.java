package com.base.engine;

import java.util.*;

public class EventQueue {
    // TODO: change Integer to Event -> encapsulate any event
    private static HashMap<Event, Runnable> events;
    private static Queue<Runnable> runnables;
    private static List<Runnable> tenureRunnables;

    public static void init() {
        events = new HashMap<>();
        runnables = new PriorityQueue<>();
        tenureRunnables = new ArrayList<>();
    }

    public static void update() {
        if (!runnables.isEmpty()) {
            runnables.poll().run();
        }

        for (Runnable r : tenureRunnables) {
            r.run();
        }
    }

    public static void invokeCallback(Event event) {
        if (events.containsKey(event)) {
            runnables.add(events.get(event));
        }
    }

    public static void registerCallback(Event event, Runnable callback) {
        if (events.containsKey(event)) {
            deleteCallback(event);
        }

        if (event.isTenured()) {
            tenureRunnables.add(callback);
        }

        events.put(event, callback);
    }

    private static void deleteCallback(Event event) {
        if (event.isTenured()) {
            tenureRunnables.remove(events.get(event));
        }

        events.remove(event);
    }
}
