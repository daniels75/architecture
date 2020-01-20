package com.javasolutions.concurrency.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(1000);
            return "Executing task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(500);
            return "Executing task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(2000);
            return "Executing task3";
        };

        msg("result: " + executorService.invokeAny(Arrays.asList(task1, task2, task3)));

        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
