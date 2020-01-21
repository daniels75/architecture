package com.javasolutions.concurrency.threadsync;

public class MemoryInconsitencyExample {
    private static boolean sayHello;
    // private volatile static boolean sayHello;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while(!sayHello) {
            }

            msg(getThreadMsg() + "Hey!");

            while(sayHello) {
            }

            msg(getThreadMsg() + "Bye!");
        });

        thread.start();

        Thread.sleep(1000);
        msg(getThreadMsg() + "    Say Hey...");
        sayHello = true;

        Thread.sleep(1000);
        msg(getThreadMsg() + "    Say Bye...");
        sayHello = false;
    }

    public static String getThreadMsg() {
        return "[Tread: " + Thread.currentThread().getName()+ "] ";
    }

    private static void msg(String message) {
        System.out.println(message);
    }
}
