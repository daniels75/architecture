package com.javasolutions.concurrency.threadsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        final Counter counter = new Counter();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        msg("Final count is : " + counter.getCount());
    }

    static class Counter {

        volatile int  count = 0;
        public void increment() {
            synchronized(this) {
                count = count + 1;
            }
        }

        public int getCount() {
            return count;
        }

    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
