package com.javasolutions.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsPeriodicExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> msg("Executing, time: " + LocalDateTime.now());

        System.out.println("scheduling task to be executed every 3 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0,3, TimeUnit.SECONDS);
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
