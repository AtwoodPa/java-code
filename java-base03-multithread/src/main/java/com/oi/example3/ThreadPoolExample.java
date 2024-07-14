package com.oi.example3;

import java.util.concurrent.*;

/**
 * 线程池示例
 *
 * @author supanpan
 * @date 2024/07/13
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,  // corePoolSize
                4,  // maximumPoolSize
                60, // keepAliveTime
                TimeUnit.SECONDS, // unit
                new LinkedBlockingQueue<Runnable>(10), // workQueue
                Executors.defaultThreadFactory(), // threadFactory
                new ThreadPoolExecutor.AbortPolicy() // handler
        );

        // 提交任务
        for (int i = 0; i < 15; i++) {
            int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // 拒绝策略 拒绝执行任务（默认的策略）
        RejectedExecutionHandler handler1 = new ThreadPoolExecutor.AbortPolicy();
        handler1.rejectedExecution(null, executor);

        // 拒绝策略 调用者执行任务
        RejectedExecutionHandler handler2 = new ThreadPoolExecutor.CallerRunsPolicy();
        handler2.rejectedExecution(null, executor);

        // 拒绝策略 丢弃任务
        RejectedExecutionHandler handler3 = new ThreadPoolExecutor.DiscardPolicy();
        handler3.rejectedExecution(null, executor);

        // 拒绝策略 丢弃任务队列中的最近的任务
        RejectedExecutionHandler handler4 = new ThreadPoolExecutor.DiscardOldestPolicy();
        handler4.rejectedExecution(null, executor);



    }
}
