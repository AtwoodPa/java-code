package com.oi.example1;

/**
 * 创建线程方式三：匿名内部类
 *
 * @author supanpan
 * @date 2024/07/11
 */
public class AnonymousInnerClassThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        });
        thread.start();
    }
}
