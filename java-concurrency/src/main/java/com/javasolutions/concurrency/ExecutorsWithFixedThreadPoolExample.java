package com.javasolutions.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsWithFixedThreadPoolExample {
    public static void main(String[] args) {
        msg("Current thread : " + Thread.currentThread().getName());

        msg("Creating Executor Service with a thread pool with size 2");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable firstTask = () -> {
            msg("First task: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable secondTask = () -> {
            msg("Second task: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable thirdTask = () -> {
            msg("Third task: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };


        msg("Submitting the tasks for execution...");
        executorService.submit(firstTask);
        executorService.submit(secondTask);
        executorService.submit(thirdTask);

        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
