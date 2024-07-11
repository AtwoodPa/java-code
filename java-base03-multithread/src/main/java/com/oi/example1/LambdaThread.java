package com.oi.example1;

/**
 * 创建线程方式四：使用Lambda表达式创建线程
 * @author supanpan
 * @date 2024/07/11
 */
public class LambdaThread {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("LambdaThread")).start();
    }
}
