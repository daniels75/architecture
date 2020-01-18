package com.javasolutions.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample2 {
    public static void main(String[] args) {
        msg("Current thread name: " + Thread.currentThread().getName());
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg("1st - Thread called from executor service name: "
                    + Thread.currentThread().getName());
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg("2nd - Thread called from executor service name: "
                    + Thread.currentThread().getName());
        });
        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
