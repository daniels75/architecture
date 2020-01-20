package com.javasolutions.concurrency;

import java.time.LocalDateTime;

public class ThreadJoinExample2 extends Thread {
    private int processingCount;

    ThreadJoinExample2(int processingCount) {
        this.processingCount = processingCount;
        msg("Thread created");
    }

    public static void main(String[] args) throws InterruptedException {
        msg("Current thread: " + Thread.currentThread().getName());
        Thread simpleThread = new ThreadJoinExample2(2);
        simpleThread.start();
        msg("Invoking join");
        // main thread is "waiting forever" for simpleThread
        simpleThread.join();
        msg("Returned from join - again thread: " + Thread.currentThread().getName());
    }

    private static void msg(String message) {
        System.out.println(LocalDateTime.now() + " " + message);
    }

    @Override
    public void run() {
        msg("Thread " + this.getName() + " started");
        while (processingCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                msg("Thread " + this.getName() + " interrupted");
            }
            processingCount--;
        }
        msg("Thread " + this.getName() + " ended");
    }
}
