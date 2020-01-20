package com.javasolutions.concurrency.callable;

import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () ->  {
            TimeUnit.SECONDS.sleep(1);
            return "just simple callable";
        };

        Future<String> future = executorService.submit(callable);
        int i =0;
        while (!future.isDone()){
            TimeUnit.MILLISECONDS.sleep(200);
            msg("still in progress" );
            if (i == 2) {
                future.cancel(true);
            }
            i++;
        }

        if (future.isCancelled()) {
            msg("Future task has been cancelled");
        } else {
            msg(future.get());
        }

        executorService.shutdown();

    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
