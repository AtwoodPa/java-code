package com.oi.example3;

/**
 * @author supanpan
 * @date 2024/07/13
 */
public class ThreadLocalCodeDemo {
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    public static void main(String[] args) {
        THREAD_LOCAL.set("123");
        System.out.println(THREAD_LOCAL.get());
    }
}
