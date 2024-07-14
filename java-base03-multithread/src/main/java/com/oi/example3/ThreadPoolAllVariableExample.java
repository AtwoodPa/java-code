package com.oi.example3;

import java.util.concurrent.*;

/**
 * * 线程池所有变量示例
 *
 * corePoolSize：核心线程数，始终保持活跃的线程数。
 * maximumPoolSize：最大线程数，线程池能容纳的最大线程数。
 * keepAliveTime：线程在终止前保持空闲的最长时间。
 * unit：keepAliveTime的时间单位。
 * workQueue：保存等待执行任务的阻塞队列。
 * threadFactory：用于创建新线程的工厂。
 * handler：当任务无法提交时的处理策略。
 *
 * @author supanpan
 * @date 2024/07/14
 */
public class ThreadPoolAllVariableExample {

    public static void main(String[] args) {
        int corePoolSize = 5;// 核心线程数
        int maximumPoolSize = 10;// 最大线程数
        int keepAliveTime = 60;// 线程空闲时间
        TimeUnit unit = TimeUnit.SECONDS;// 时间单位
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);// 任务队列（阻塞队列）
        ThreadFactory threadFactory = Executors.defaultThreadFactory(); // 线程工厂
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy(); // 拒绝策略

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);

        for (int i = 0; i < 200; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " is executing task");
            });
        }

        executor.shutdown();
    }
}