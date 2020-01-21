package com.javasolutions.concurrency.threadsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMethodsExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

        executorService.submit(() -> {
            msg("started -> First Thread");
            int value = lockMethodsCounter.incrementAndGet();
            msg("IncrementCount (First Thread): " + value + "\n");
        });

        executorService.submit(() -> {
            msg("started -> Second Thread");
            int value = lockMethodsCounter.incrementAndGet();
            msg("IncrementCount (Second Thread): " + value + "\n");
        });

        executorService.shutdown();
    }

    static class ReentrantLockMethodsCounter {
        private final ReentrantLock lock = new ReentrantLock();

        private int count = 0;

        public int incrementAndGet() {
            // Check if the lock is currently acquired by any thread
            msg("isLocked: " + lock.isLocked());

            // Check if the lock is acquired by the current thread itself.
            msg("isHeldByCurrentThread: " + lock.isHeldByCurrentThread());

            // Try to acquire the lock
            boolean isAcquired = lock.tryLock();

            // with that lock.tryLock(3, TimeUnit.SECONDS); value count could be 2
            /*
            try {
                isAcquired = lock.tryLock(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             */


            msg("Lock Acquired: " + isAcquired + "\n");

            if(isAcquired) {
                try {
                    Thread.sleep(2000);
                    count = count + 1;
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                } finally {
                    lock.unlock();
                }
            }
            return count;
        }
    }
    private static void msg(String message) {
        System.out.println(message);
    }
}
