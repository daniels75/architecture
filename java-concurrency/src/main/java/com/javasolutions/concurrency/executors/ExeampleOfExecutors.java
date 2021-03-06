package com.javasolutions.concurrency.executors;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class ExeampleOfExecutors {

    public static void main(String[] args) {
        // usingSingleThreadExecutor();
        usingCachedThreadPool();
        //usingFixedThreadPool();
        //usingScheduledThreadPool();
        //usingSingleTreadScheduledExecutor();
        //usingWorkStealingThreadPool();
    }

    public static void usingSingleThreadExecutor() {
        msg("=== SingleThreadExecutor ===");
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> msg(threadName() + "Task1"));
        singleThreadExecutor.execute(() -> msg(threadName() + "Task2"));
        singleThreadExecutor.shutdown();
        try {
            singleThreadExecutor.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("\n\n");
    }

    public static void usingCachedThreadPool() {
        msg("=== CachedThreadPool ===");
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Future<UUID>> uuids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Future<UUID> submittedUUID = cachedThreadPool.submit(() -> {
                UUID randomUUID = UUID.randomUUID();
                msg(threadName() + "UUID " + randomUUID);
                return randomUUID;
            });
            uuids.add(submittedUUID);
        }
        printTaskValues(cachedThreadPool, uuids);

    }

    public static void usingFixedThreadPool() {
        msg("=== FixedThreadPool ===");
        final ExecutorService fixedPool = Executors.newFixedThreadPool(4);
        List<Future<UUID>> uuids = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<UUID> submitted = fixedPool.submit(() -> {
                UUID randomUUID = UUID.randomUUID();
                msg(threadName() + "UUID " + randomUUID);
                return randomUUID;
            });
            uuids.add(submitted);
        }
        printTaskValues(fixedPool, uuids);
    }

    public static void usingScheduledThreadPool() {
        msg("=== ScheduledThreadPool ===");
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        scheduledThreadPool.scheduleAtFixedRate(() -> msg("1) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(() -> msg("2) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(() -> msg("3) Print every 2s delay"), 0, 2,
                TimeUnit.SECONDS);

        try {
            scheduledThreadPool.awaitTermination(6, TimeUnit.SECONDS);
            scheduledThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("\n\n");
    }

    public static void usingSingleTreadScheduledExecutor() {
        msg("=== SingleThreadScheduledThreadPool ===");
        final ScheduledExecutorService singleThreadScheduler = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduler.scheduleAtFixedRate(() -> msg("1) Print every 2s"), 0, 2, TimeUnit.SECONDS);
        singleThreadScheduler.scheduleWithFixedDelay(() -> msg("2) Print every 2s delay"), 0, 2,
                TimeUnit.SECONDS);

        try {
            singleThreadScheduler.awaitTermination(6, TimeUnit.SECONDS);
            singleThreadScheduler.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("\n\n");

    }

    public static void usingWorkStealingThreadPool() {
        msg("=== WorkStealingThreadPool ===");
        final ExecutorService workStealingPool = Executors.newWorkStealingPool();

        workStealingPool.execute(() -> msg("Prints normally"));

        Callable<UUID> generatesUUID = UUID::randomUUID;
        List<Callable<UUID>>severalUUIDsTasks = new LinkedList<Callable<UUID>>();
        for (int i = 0; i < 20; i++) {
            severalUUIDsTasks.add(generatesUUID);
        }

        try {
            List<Future<UUID>> futureUUIDs = workStealingPool.invokeAll(severalUUIDsTasks);
            for (Future<UUID> future : futureUUIDs) {
                if (future.isDone()) {
                    UUID uuid = future.get();
                    msg("New UUID :" + uuid);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            workStealingPool.awaitTermination(6, TimeUnit.SECONDS);
            workStealingPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("\n\n");
    }

    private static void printTaskValues(ExecutorService cachedThreadPool, List<Future<UUID>> uuids) {
        cachedThreadPool.execute(() -> uuids.forEach((f) -> {
            try {
                msg(threadName() + "Result " + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));

        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("\n\n");
    }

    private static void msg(String message) {
        System.out.println(message);
    }
    public static String threadName() {
        return " [Tread: " + Thread.currentThread().getName()+ "] ";
    }


}
