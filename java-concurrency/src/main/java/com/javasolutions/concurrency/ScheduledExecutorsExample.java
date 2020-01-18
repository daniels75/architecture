package com.javasolutions.concurrency;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () ->  msg("Executing task at:  " + LocalDateTime.now());


        msg("Submitting task at: " + LocalDateTime.now()  + " to be executed after 3 seconds.");
        scheduledExecutorService.schedule(task, 3, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
