package fr.mercury.investbridge.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    /*
     *  FIELDS
     */

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /*
     *  METHODS
     */

    public static void runAsync(Runnable runnable) {

        scheduler.execute(runnable);
    }

    public static void runAsync(Runnable runnable, long firstDelay, long delay, TimeUnit timeUnit) {

        scheduler.scheduleAtFixedRate(runnable, firstDelay, delay, timeUnit);
    }

    public static void runLater(Runnable runnable, long delay, TimeUnit timeUnit) {

        scheduler.schedule(runnable, delay, timeUnit);
    }
}
