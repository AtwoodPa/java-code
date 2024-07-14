package com.oi.example3;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略
 *
 * @author supanpan
 * @date 2024/07/13
 */
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 自定义处理逻辑，比如记录日志或重新尝试提交任务
        System.out.println("Task " + r.toString() + " rejected from " + executor.toString());
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new CustomRejectedExecutionHandler() // 使用自定义拒绝策略
        );

        for (int i = 0; i < 20; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println(Thread.currentThread().getName() + " is executing task");
                }
            });
        }

        executor.shutdown();
    }
}
