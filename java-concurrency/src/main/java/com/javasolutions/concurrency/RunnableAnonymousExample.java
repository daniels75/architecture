package com.javasolutions.concurrency;

public class RunnableAnonymousExample {
    public static void main(String[] args) {
        msg("Current thread: " + Thread.currentThread().getName());

        Thread thread = new Thread(() -> msg("Another thread: " + Thread.currentThread().getName()));
        thread.start();
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
