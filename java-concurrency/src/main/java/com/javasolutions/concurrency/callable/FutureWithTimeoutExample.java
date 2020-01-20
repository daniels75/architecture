package com.javasolutions.concurrency.callable;

import java.util.concurrent.*;

public class FutureWithTimeoutExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(3000);
            return "Task from Callable";
        });

        try {
            msg("retrieving a result: " + future.get(2, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            msg("no results, the time of the response elapsed");
        } finally {
            executorService.shutdown();
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
