package com.javasolutions.concurrency.callable;

import java.util.concurrent.*;

public class FutureAndCallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            // Perform some computation
            msg("Start callable");
            Thread.sleep(2000);
            return "Simple value from callable";
        };

        msg("Submitting Callable");
        Future<String> future = executorService.submit(callable);

        // This line executes immediately
        msg("Do something else while callable is getting executed");

        while(!future.isDone()) {
            msg("Task is still not done...");
            Thread.sleep(500);
        }

        msg("Retrieve the result of the future");
        // Future.get() blocks until the result is available
        msg("Result: " + future.get());
        msg("------------------------------------");

        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
