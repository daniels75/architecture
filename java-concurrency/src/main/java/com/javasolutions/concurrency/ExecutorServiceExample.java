package com.javasolutions.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        msg("Current thread name: " + Thread.currentThread().getName());
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.submit(() ->
            msg("1st - Thread called from executor service name: "
                    + Thread.currentThread().getName()));

        executorService.submit(() -> msg("2nd - Thread called from executor service name: "
                + Thread.currentThread().getName()));
        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
