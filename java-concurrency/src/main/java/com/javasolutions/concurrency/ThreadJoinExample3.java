package com.javasolutions.concurrency;

import java.time.LocalDateTime;

public class ThreadJoinExample3 extends Thread {
    private int processingCount;

    ThreadJoinExample3(int processingCount) {
        this.processingCount = processingCount;
        msg(" Thread created");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread simpleThread = new ThreadJoinExample3(2);
        simpleThread.start();
        msg(" Invoking join");
        // main thread is waiting only 500ms for simpleThread
        simpleThread.join(500);
        msg("| Returned from join");
    }

    private static void msg(String message) {
        System.out.println(LocalDateTime.now() + " " + message);
    }

    @Override
    public void run() {
        msg("- Thread " + this.getName() + " started");
        while (processingCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                msg("Thread " + this.getName() + " interrupted");
            }
            processingCount--;
        }
        msg("- Thread " + this.getName() + " ended");
    }
}
