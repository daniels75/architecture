package com.javasolutions.concurrency.threadsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounterExample {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        final CounterWithReentrantLock counter = new CounterWithReentrantLock();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        msg("Final count is : " + counter.getCount());
    }

    static class CounterWithReentrantLock {

        int count;
        private final ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count = count + 1;
            } finally {
                lock.unlock();
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
