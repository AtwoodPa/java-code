package com.oi.example2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author supanpan
 * @date 2024/07/12
 */
public class BlockQueueCodeDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        try {
            // 阻塞直到有元素
            String element = queue.take();
            Thread.sleep(1000);
            // 阻塞直到有空间
            System.out.println(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
