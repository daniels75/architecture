package com.javasolutions.concurrency;

import java.util.concurrent.Executor;

public class ExecutorExample {
    public static void main(String[] args) {
        Executor executor = new SimpleExecutor();
        System.out.println("Current thread name: " + Thread.currentThread().getName());

        executor.execute(() -> msg("Executed from executor in the same thread"));
        System.out.println("-----------------------------------------------------------------");
        Executor taskExecutor1 = new ThreadPerTaskExecutor();
        taskExecutor1.execute(()->msg("Task executed in the another thread than the caller's thread."));

    }

    private static class SimpleExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable command) {
            final Thread taskExecutor = new Thread(command);
            msg("ThreadPerTaskExecutor, thread name: " + taskExecutor.getName());
            msg(Thread.currentThread().getName());
            taskExecutor.start();
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
