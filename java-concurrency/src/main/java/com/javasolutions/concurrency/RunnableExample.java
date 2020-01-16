package com.javasolutions.concurrency;

public class RunnableExample implements Runnable {

    public static void main(String[] args) {
        System.out.println("Current thread name: " + Thread.currentThread().getName());

        Thread thread1 = new Thread(new RunnableExample());
        thread1.start();

        Thread thread2 = new Thread(new RunnableExample());
        thread2.start();
    }

    @Override
    public void run() {
        System.out.println("Runnable - thread name: " + Thread.currentThread().getName());
    }
}
