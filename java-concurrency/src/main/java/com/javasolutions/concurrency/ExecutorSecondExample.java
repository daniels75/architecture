package com.javasolutions.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSecondExample {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Callable<Integer>> listOfCallable = Arrays.asList(
                () -> {
                    return 1;},
                () -> 2,
                () -> 3);


        // java7
        /*
        List<Callable<Integer>> listOfCallable2 = Arrays.asList(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 1;
                    }
                },
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 2;
                    }
                },
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 3;
                    }
                }
        );
        */

        try {

            List<Future<Integer>> futures = executor.invokeAll(listOfCallable);

            int sum = futures.stream().map(f -> {
                try {
                    System.out.println(f.get() + " " + f.isDone());
                    return f.get().intValue();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }).mapToInt(Integer::intValue).sum();

            System.out.println(sum);

        } catch (InterruptedException e) {// thread was interrupted
            e.printStackTrace();
        } finally {

            // shut down the executor manually
            executor.shutdown();

        }

    }
}
