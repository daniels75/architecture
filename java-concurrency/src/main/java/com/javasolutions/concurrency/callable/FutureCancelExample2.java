package com.javasolutions.concurrency.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Task from Callable";
        });

        while(!future.isDone()) {
            msg("task in progress...");
            Thread.sleep(200);
            
            long nanoTime = System.nanoTime();
            double elapsedTimeInSec = (nanoTime - startTime)/1000000000.0;

            if(elapsedTimeInSec > 1) {
                future.cancel(true);
            }
        }

        if (future.isCancelled()) {
            msg("No result since task has been cancelled");
        } else {
            msg("Task completed! Retrieving the result: " + future.get());
        }

        executorService.shutdown();
    }


    private static void msg(String message) {
        System.out.println(message);
    }

}
