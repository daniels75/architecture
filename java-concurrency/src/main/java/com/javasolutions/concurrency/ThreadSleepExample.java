package com.javasolutions.concurrency;

import com.google.common.collect.Lists;

import java.util.List;

public class ThreadSleepExample {
    public static void main(String[] args) throws Exception {
        msg("Current thread: " + Thread.currentThread().getName());

        final List<String> messages = Lists.newArrayList("message1", "message2", "message3");
        final Thread thread = new Thread(createRunnable(messages));
        thread.start();

        Thread.sleep(500);
        msg("Message from current thread: " + Thread.currentThread().getName());
    }

    private static Runnable createRunnable(List<String> messages) {
        return () -> {
                for(String message : messages) {
                    msg("Message: " + message + " Thread name: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                }
            };
    }

    private static void msg(String message) {
        System.out.println(message);
    }

}
