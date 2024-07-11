package com.oi.example1;

class MyThreads extends Thread {
    public void run() {
        try {
            // 进入 RUNNING 状态
            System.out.println("Thread is running");

            // 进入 TIMED_WAITING 状态
            Thread.sleep(1000);

            synchronized (this) {
                // 进入 BLOCKED 状态
                this.wait();
            }

            // 再次进入 RUNNING 状态
            System.out.println("Thread is running again");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyThreads t1 = new MyThreads();

        // 线程处于 NEW 状态
        System.out.println("Thread state: " + t1.getState());

        t1.start();
        // 线程处于 RUNNABLE 状态
        System.out.println("Thread state: " + t1.getState());

        try {
            // 主线程休眠一段时间，让子线程进入 TIMED_WAITING 状态
            Thread.sleep(500);
            // 线程处于 TIMED_WAITING 状态
            System.out.println("Thread state: " + t1.getState());

            synchronized (t1) {
                t1.notify();
            }

            // 再次检查线程状态
            Thread.sleep(500);
            // 线程应该处于 RUNNABLE 状态
            System.out.println("Thread state: " + t1.getState());

            t1.join();
            // 线程处于 TERMINATED 状态
            System.out.println("Thread state: " + t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
