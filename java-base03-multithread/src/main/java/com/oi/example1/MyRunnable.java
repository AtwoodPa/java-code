package com.oi.example1;

/**
 * 创建线程方式二：实现Runnable接口
 *
 * @author supanpan
 * @date 2024/07/11
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        thread1.start();
    }
}
