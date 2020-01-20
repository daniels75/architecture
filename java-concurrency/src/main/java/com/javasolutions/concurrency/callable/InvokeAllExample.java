package com.javasolutions.concurrency.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
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

        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);

        List<Future<String>> futures = executorService.invokeAll(taskList);

        for(Future<String> future: futures) {
            // The result is printed only after all the futures are complete. (i.e. after 5 seconds)
            msg(future.get());
        }

        executorService.shutdown();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
