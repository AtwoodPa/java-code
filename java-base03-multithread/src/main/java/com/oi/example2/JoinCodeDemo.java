package com.oi.example2;

import java.util.Map;

/**
 * 线程的join方法，子线程先执行，主线程再执行
 *
 * @author supanpan
 * @date 2024/07/12
 */
public class JoinCodeDemo {
    public static void main(String[] args) {
        // 显示本代码中的所有线程
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            System.out.println("线程：" + entry.getKey().getName());
        }
        Thread thread = new Thread(() -> {
            try {
                // 子线程执行一些任务，例如休眠2秒钟模拟任务执行
                System.out.println("子线程开始执行");
                Thread.sleep(2000);
                System.out.println("子线程执行完毕");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setName("子线程");
        // 启动子线程
        thread.start();

        // 主线程等待子线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程继续执行");
    }
}
