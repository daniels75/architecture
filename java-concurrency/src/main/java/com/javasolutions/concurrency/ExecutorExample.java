package com.javasolutions.concurrency;

import java.util.concurrent.Executor;

public class ExecutorExample {
    public static void main(String[] args) {
        Executor executor = new SimpleExecutor();
        System.out.println("CurrentTread: " + Thread.currentThread());
    }

    private static class SimpleExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
