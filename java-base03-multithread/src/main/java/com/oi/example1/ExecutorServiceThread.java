package com.oi.example1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程方式五：ExecutorService
 * @author supanpan
 * @date 2024/07/11
 */
public class ExecutorServiceThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(() -> {
            System.out.println("Thread is running");
        });
        executor.shutdown();
    }
}
