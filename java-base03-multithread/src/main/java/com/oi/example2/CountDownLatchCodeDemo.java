package com.oi.example2;

import java.util.concurrent.CountDownLatch;

/**
 * @author supanpan
 * @date 2024/07/12
 */
public class CountDownLatchCodeDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            new Thread(() -> {
                System.out.println("线程1开始执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown(); // 计数器减一
            }).start();
            latch.await(); // 阻塞直到计数器归零
            System.out.println("计数器归零，继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
