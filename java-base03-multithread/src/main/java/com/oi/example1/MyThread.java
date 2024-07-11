package com.oi.example1;

/**
 * 创建线程方式一：继承Thread类
 *
 * @author supanpan
 * @date 2024/07/11
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }


    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
