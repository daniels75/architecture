package com.javasolutions.concurrency;

import java.time.LocalDateTime;

public class ThreadJoinExample {

    public static void main(String[] args) {

        Thread firstThread = new Thread(() -> {
            msg("Start " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            msg("Stop " + Thread.currentThread().getName());
        });

        Thread secondThread = new Thread(() -> {
            msg("Start " + Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            msg("Stop " + Thread.currentThread().getName());
        });

        msg("Starting first thread");
        firstThread.start();

        msg("Waiting for first thread to complete");
        try {
            firstThread.join(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        msg("Starting second thread");
        secondThread.start();
    }

    private static void msg(String message) {
        System.out.println(LocalDateTime.now() + " " + message);
    }

}
