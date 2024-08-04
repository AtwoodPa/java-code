package org.javaboy.thread;

/**
 *
 * @author supanpan
 * @date 2024/08/04
 */
public class PrintTaskRandom implements Runnable{
    private char toPrint;
    private int count;
    private static final Object lock = new Object();  // 共享的锁对象
    private static int currentIndex = 0; // 跟踪当前应打印的字符索引
    private static int numThreads; // 线程总数

    public PrintTaskRandom(char toPrint, int count, int numThreads) {
        this.toPrint = toPrint;
        this.count = count;
        PrintTaskRandom.numThreads = numThreads;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                // 等待直到当前索引是我们应该打印的字符
                while (currentIndex % numThreads != (toPrint - 'a')) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // 打印字符
                System.out.print(toPrint);
                // 更新索引并通知其他线程
                currentIndex++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        String sequence = "abcde"; // 任意长度的字符串
        int length = sequence.length();

        Thread[] threads = new Thread[length];
        for (int i = 0; i < length; i++) {
            threads[i] = new Thread(new PrintTaskRandom(sequence.charAt(i), 3, length));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // 换行
    }
}
